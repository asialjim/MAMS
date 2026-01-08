/*
 *    Copyright 2014-2025 <a href="mailto:asialjim@qq.com">Asial Jim</a>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.asialjim.microapplet.mams.user.service;

import com.asialjim.microapplet.common.cons.Headers;
import com.asialjim.microapplet.common.context.Res;
import com.asialjim.microapplet.common.context.Result;
import com.asialjim.microapplet.common.security.MamsSession;
import com.asialjim.microapplet.common.utils.MamsTokenUtil;
import com.asialjim.microapplet.commons.security.Role;
import com.asialjim.microapplet.hermes.event.EventBus;
import com.asialjim.microapplet.mams.app.api.ChlAppApi;
import com.asialjim.microapplet.mams.app.cons.ChannelAppType;
import com.asialjim.microapplet.mams.app.cons.ChannelType;
import com.asialjim.microapplet.mams.app.context.AppRs;
import com.asialjim.microapplet.mams.app.context.ChlRs;
import com.asialjim.microapplet.mams.app.vo.ChlAppVo;
import com.asialjim.microapplet.mams.user.api.ChlUserApi;
import com.asialjim.microapplet.mams.user.event.MamsSessionContinue;
import com.asialjim.microapplet.mams.user.infrastructure.config.JwtConfigProperty;
import com.asialjim.microapplet.mams.user.infrastructure.repository.SessionRepository;
import com.asialjim.microapplet.mams.user.service.login.ChlLoginStrategy;
import com.asialjim.microapplet.mams.user.vo.ChlUserVo;
import com.asialjim.microapplet.mams.user.vo.LoginReq;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;

/**
 * 用户认证服务
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/9/22, &nbsp;&nbsp; <em>version:1.0</em>
 */
@Slf4j
@Component
public class AuthService implements ApplicationRunner, MessageListener, InitializingBean {
    @Resource
    private RedisMessageListenerContainer redisMessageListenerContainer;
    @Resource
    private List<ChlLoginStrategy> chlLoginStrategies;
    @Resource
    private JwtConfigProperty jwtConfigProperty;
    @Resource
    private SessionRepository sessionRepository;
    @Resource
    private ChlUserApi chlUserApi;
    @Resource
    private ChlAppApi chlAppApi;

    /**
     * 用户登录
     *
     * @param appid    {@link String appid}
     * @param chl      {@link String chl}
     * @param chlAppid {@link String chlAppid}
     * @param req      {@link LoginReq req}
     * @since 2025/9/23
     */
    public ResponseEntity<Result<String>> login(String appid, String chl, String chlAppid, String chlAppType, LoginReq req) {
        log.info("\r\n登录({}, {}, {},{}),参数: {}", appid, chl, chlAppid, chlAppType, req);

        ChlAppVo chlAppVo = null;
        if (StringUtils.isAllBlank(chlAppid, chlAppType)) {
            AppRs.ChlAppNotSet.thr();
        } else if (StringUtils.isNotBlank(chlAppid)) {
            chlAppVo = this.chlAppApi.queryByAppidAndChlAndChlAppid(appid, chl, chlAppid);
        } else {
            chlAppVo = this.chlAppApi.queryByAppidAndChlAndChlAppType(appid, chl, chlAppType);
        }

        if (Objects.isNull(chlAppVo)) {
            String username = req.getUsername();
            // 超管
            if (StringUtils.equalsIgnoreCase(username, ChannelAppType.ROOT.getCode()))
                chlAppVo = this.chlAppApi.queryByAppidAndChlAndChlAppType(appid, chl, ChannelAppType.ROOT.getCode());
        }

        if (Objects.isNull(chlAppVo)) throw AppRs.NoSuchChlApp.ex();

        if (log.isDebugEnabled()) log.debug("登录渠道应用:{}", chlAppVo);
        ChannelType channelType = ChlAppVo.channelType(chlAppVo);
        if (log.isDebugEnabled()) log.debug("登录渠道:{}", channelType);
        MamsSession session = strategy(channelType).login(chlAppVo, req);


        String userid = session.getUserid();
        List<ChlUserVo> chlUserVos = this.chlUserApi.queryByUserid(userid);
        // 添加渠道用户角色表
        Optional.ofNullable(chlUserVos)
                .ifPresent(items -> {
                    for (ChlUserVo item : items) {          //便利当前用户每一个渠道用户信息
                        if (Objects.isNull(item))
                            continue;
                        Long roleBit = item.getRoleBit();   // 获取渠道用户角色表
                        if (Objects.isNull(roleBit))
                            continue;
                        session.addRole(roleBit);           // 用户角色做并集
                    }
                });

        session.addRole(Role.Authenticated.getBit());// 添加登录用户角色表

        final String sessionId = UUID.randomUUID().toString().replaceAll("-", StringUtils.EMPTY);

        if (log.isDebugEnabled()) log.debug("创建会话:{}", sessionId);
        String jwtToken = MamsTokenUtil.create();

        if (log.isDebugEnabled()) log.debug("创建令牌:{}", jwtToken);

        session.setId(sessionId);
        session.expireAfter(jwtConfigProperty.jwtTimeout());
        session.setToken(jwtToken);
        this.sessionRepository.setCache(session);

        if (log.isDebugEnabled()) log.debug("登录结果:{}", session);
        final ResponseCookie tokenCookie = ResponseCookie.from(Headers.USER_TOKEN, jwtToken).path("/").maxAge(Duration.ofHours(2)).httpOnly(true).secure(true).sameSite("None").build();
        //.sameSite("Lax").build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, tokenCookie.toString()).body(Res.OK.result(jwtToken));
    }


    /**
     * 用户认证
     *
     * @param token {@link String token}
     * @return {@link MamsSession }
     * @since 2025/9/23
     */
    public MamsSession auth(String token) {
        boolean verify = MamsTokenUtil.verify(token);
        if (!verify)
            Res.UserAuthFailure401Thr.thr(Collections.singletonList("非法令牌"));

        final MamsSession session = this.sessionRepository.getCache(token);
        if (Objects.isNull(session))
            return new MamsSession().setRoleBit(Role.TOURIST_BIT).expireAfter(jwtConfigProperty.jwtTimeout());

        if (session.isExpired())
            Res.UserAuthFailure401Thr.thr(Collections.singletonList("用户未登录或登录已过期"));
        // 发送令牌续期事件
        MamsSessionContinue mamsSessionContinue = new MamsSessionContinue().setSession(session);
        EventBus.push(mamsSessionContinue);
        return session;
    }


    // 初始化订阅
    @Override
    public void run(ApplicationArguments args) {
    }


    @PreDestroy
    public void destroy() {
        try {
            this.redisMessageListenerContainer.destroy();
        } catch (Exception ignored) {
        }
    }

    /**
     * 策略
     *
     * @param channelType 渠道类型
     * @return {@link ChlLoginStrategy}
     */
    private ChlLoginStrategy strategy(ChannelType channelType) {
        return this.chlLoginStrategies.stream()
                .filter(item -> item.support(channelType))
                .findAny()
                .orElseThrow(ChlRs.ChlUnSupportEx::ex);
    }

    /**
     * 注销
     *
     * @param token 令牌
     */
    public void logout(String token) {
        this.sessionRepository.deleteCache(token);
    }

    @Override
    public void onMessage(@SuppressWarnings("NullableProblems") Message message,
                          byte[] channel) {
        if (ArrayUtils.isEmpty(channel))
            return;
        //noinspection ConstantValue
        if (Objects.isNull(message))
            return;

        assert channel != null;
        String key = new String(channel, StandardCharsets.UTF_8);
        if (!Headers.CURRENT_SESSION.equals(key)) {
            return;
        }

        byte[] body = message.getBody();
        String token = new String(body, StandardCharsets.UTF_8);
        if (log.isDebugEnabled())
            log.info("Receive Gateway Token {} continue...", token);
        auth(token);
    }

    @Override
    public void afterPropertiesSet() {
        this.redisMessageListenerContainer.addMessageListener(this, new ChannelTopic(Headers.CURRENT_SESSION));
    }
}
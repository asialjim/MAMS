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

import com.asialjim.microapplet.common.security.MamsSession;
import com.asialjim.microapplet.commons.security.Role;
import com.asialjim.microapplet.hermes.annotation.OnEvent;
import com.asialjim.microapplet.mams.user.api.ChlUserApi;
import com.asialjim.microapplet.mams.user.api.IdCardUserApi;
import com.asialjim.microapplet.mams.user.event.MamsSessionContinue;
import com.asialjim.microapplet.mams.user.infrastructure.config.JwtConfigProperty;
import com.asialjim.microapplet.mams.user.infrastructure.repository.SessionRepository;
import com.asialjim.microapplet.mams.user.vo.ChlUserVo;
import com.asialjim.microapplet.mams.user.vo.IdCardUserVo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 用户会话服务
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/12/2, &nbsp;&nbsp; <em>version:1.0</em>
 */
@Slf4j
@Service
public class UserSessionService {
    @Resource
    private SessionRepository sessionRepository;
    @Resource
    private JwtConfigProperty jwtConfigProperty;
    @Resource
    private IdCardUserApi idCardUserApi;
    @Resource
    private ChlUserApi chlUserApi;

    @OnEvent(jvmOnly = true)
    public void onSessionContinue(MamsSessionContinue sessionContinue) {
        MamsSession session = Optional.ofNullable(sessionContinue)
                .map(MamsSessionContinue::getSession)
                .orElse(null);
        log.info("Hermes 监听器 onMamsSessionAuth 收到用户会话保持事件：{}", sessionContinue);
        if (Objects.isNull(session))
            return;

        session.expireAfter(jwtConfigProperty.jwtTimeout());

        long bit = 0;
        // 添加游客角色
        bit |= Role.TOURIST_BIT;
        log.info("添加游客角色结果：{}", BigInteger.valueOf(bit).toString(2));
        // 添加登录角色
        bit |= Role.AUTHENTICATED_BIT;
        log.info("添加登录角色结果：{}", BigInteger.valueOf(bit).toString(2));

        String userid = session.getUserid();
        List<ChlUserVo> chlUserVos = this.chlUserApi.queryByUserid(userid);

        // 添加渠道用户角色表
        if (CollectionUtils.isNotEmpty(chlUserVos)) {
            for (ChlUserVo chlUserVo : chlUserVos) {
                if (Objects.isNull(chlUserVo))
                    continue;
                Long roleBit = chlUserVo.getRoleBit();
                if (Objects.isNull(roleBit))
                    continue;
                bit |= roleBit;
                log.info("添加\t{}\t\t角色结果：{}", roleBit, BigInteger.valueOf(bit).toString(2));
            }
        }

        // 判定证件用户角色
        List<IdCardUserVo> idCardUserVos = this.idCardUserApi.queryByUserid(userid);
        if (CollectionUtils.isNotEmpty(idCardUserVos)) {
            bit |= Role.ID_CARD_USER_BIT;
            log.info("添加证件角色结果：{}", BigInteger.valueOf(bit).toString(2));
        }
        session.setRoleBit(bit);
        MamsSession mamsSession = sessionRepository.setCache(session);
        log.info("监听器：onMamsSessionAuth 用户会话事件保持处理结束：{}", mamsSession);
    }

}
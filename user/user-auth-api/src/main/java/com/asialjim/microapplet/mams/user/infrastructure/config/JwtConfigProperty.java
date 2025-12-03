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

package com.asialjim.microapplet.mams.user.infrastructure.config;

import com.asialjim.microapplet.common.context.Res;
import com.asialjim.microapplet.common.security.MamsSession;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serial;
import java.io.Serializable;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.asialjim.microapplet.common.cons.Headers.*;

/**
 * JWT 令牌参数
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/9/23, &nbsp;&nbsp; <em>version:1.0</em>
 */
@Data
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "user.auth.jwt")
public class JwtConfigProperty implements Serializable {
    @Serial
    private static final long serialVersionUID = -4326680656968076810L;
    private String secret;
    private String issuer;
    private Long timeout;
    private String timeunit;

    public String getIssuer() {
        return Optional.ofNullable(this.issuer)
                .filter(StringUtils::isNotBlank)
                .orElse("MicroApplet");
    }

    public Duration jwtTimeout() {
        if (Objects.isNull(timeout))
            return Duration.ofMinutes(10);
        TimeUnit timeUnit;
        if (StringUtils.isBlank(timeunit))
            timeUnit = TimeUnit.MINUTES;
        else
            timeUnit = Optional.of(this.timeunit).map(TimeUnit::valueOf).orElse(TimeUnit.MINUTES);
        return Duration.ofSeconds(timeUnit.toSeconds(timeout));
    }


    public String jwt(String sessionId, String token, String appid, String chlCode, String chlAppid) {
        String jwtSecret = getSecret();
        final Date issueAt = new Date(System.currentTimeMillis());

        return JWT.create()
                .withClaim(SessionId, sessionId)
                .withClaim(USER_TOKEN, token)
                .withClaim(APP_ID, appid)
                .withClaim(APP_CHL, chlCode)
                .withClaim(APP_CHL_APPID, chlAppid)
                .withIssuedAt(issueAt)
                .withIssuer(getIssuer())
                .sign(Algorithm.HMAC256(jwtSecret));
    }

    public DecodedJWT verify(String jwtToken) {
        Algorithm algorithm = Algorithm.HMAC256(getSecret());
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(jwtToken);
        } catch (JWTVerificationException e) {
            throw Res.UserAuthFailure401Thr.ex();
        }
    }


    public void verify(String jwtToken, MamsSession session) {
        log.info("认证进入");
        if (Objects.isNull(session))
            Res.UserAuthFailure401Thr.thr(Collections.singletonList("用户未登录或登录已过期"));

        DecodedJWT jwt = verify(jwtToken);
        log.info("认证:{}",jwt);

        if (!StringUtils.equals(session.getToken(), jwtToken))
            Res.UserAuthFailure401Thr.thr(Collections.singletonList("令牌不匹配"));

        String sessionId = body(SessionId, jwt);
        log.info("认证sessionId:{}",sessionId);
        if (!StringUtils.equals(sessionId, session.getId()))
            Res.UserAuthFailure401Thr.thr(Collections.singletonList("会话不匹配"));

        /*try {
            boolean b = PasswordStorage.verifyPassword(sessionId, body(USER_TOKEN, jwt));
            log.info("认证sessionId:{} 由后台生成",sessionId);
            if (!b)
                Res.UserAuthFailure401Thr.thr(Collections.singletonList("非法令牌"));
        } catch (Throwable e) {
            Res.UserAuthFailure401Thr.thr(Collections.singletonList("非法令牌"));
        }*/

        String appid = body(APP_ID, jwt);
        log.info("认证appid:{} ",appid);
        if (!StringUtils.equals(appid, session.getAppid()))
            Res.UserAuthFailure401Thr.thr(Arrays.asList("应用不匹配", "会话应用:" + session.getAppid(), "访问应用:" + appid));

        String chl = body(APP_CHL, jwt);
        log.info("认证chl:{} ",chl);
        if (!StringUtils.equals(chl, session.getChl()))
            Res.UserAuthFailure401Thr.thr(Arrays.asList("渠道不匹配", "会话渠道:" + session.getChl(), "访问渠道:" + chl));

        String chlAppid = body(APP_CHL_APPID, jwt);
        log.info("认证chlAppid:{} ",chlAppid);
        if (!StringUtils.equals(chlAppid, session.getChlAppid()))
            Res.UserAuthFailure401Thr.thr(Arrays.asList("渠道应用不匹配", "会话渠道应用:" + session.getChlAppid(), "访问渠道应用:" + chlAppid));

        String issuer = jwt.getIssuer();
        log.info("认证issuer:{} ",issuer);
        if (!StringUtils.equals(issuer, getIssuer()))
            Res.UserAuthFailure401Thr.thr(Collections.singletonList("令牌签发非法"));
    }

    private String body(String key, DecodedJWT jwt) {
        if (StringUtils.isBlank(key))
            return StringUtils.EMPTY;
        return Optional.ofNullable(jwt).map(item -> item.getClaim(key)).map(Claim::asString).orElse(StringUtils.EMPTY);
    }
}
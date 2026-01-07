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
import com.asialjim.microapplet.common.security.MamsSessionAttribute;
import com.asialjim.microapplet.hermes.annotation.OnEvent;
import com.asialjim.microapplet.mams.user.event.MamsSessionContinue;
import com.asialjim.microapplet.mams.user.infrastructure.repository.SessionRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
    private MamsSessionAttribute mamsSessionAttribute;
    @Resource
    private SessionRepository sessionRepository;

    @OnEvent
    @SuppressWarnings("unused")
    public void onMamsSessionAuth(MamsSessionContinue sessionContinue) {
        MamsSession session = sessionContinue.getSession();
        log.info("Hermes 监听器 onMamsSessionAuth 收到用户会话保持事件：{}", sessionContinue);
        if (Objects.isNull(session)) {
            return;
        }
        MamsSession mamsSession = sessionRepository.setCache(session);
        log.info("监听器：onMamsSessionAuth 用户会话事件保持处理结束：{}", mamsSession);
    }

    public MamsSession currentSession() {
        return this.mamsSessionAttribute.currentSession();
    }
}
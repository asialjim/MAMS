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

package com.asialjim.microapplet.mams.user.web;

import com.asialjim.microapplet.common.security.MamsSession;
import com.asialjim.microapplet.mams.user.api.UserSessionApi;
import com.asialjim.microapplet.mams.user.service.UserSessionService;
import com.asialjim.microapplet.mams.user.vo.ChlUserVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 用户会话控制器
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/9/19, &nbsp;&nbsp; <em>version:1.0</em>
 */
@RestController
@RequestMapping("/session")
public class UserSessionController {
    @Resource
    private UserSessionApi userSessionApi;
    @Resource
    private UserSessionService userSessionService;


    /**
     * 登录
     *
     * @param vo 签证官
     * @return {@link MamsSession}
     */
    @PostMapping("/login")
    public MamsSession login(@RequestBody ChlUserVo vo) {
        return this.userSessionApi.login(vo);
    }

    /**
     * 获取当前用户会话信息
     *
     * @return {@link MamsSession }
     * @since 2025/11/27
     */
    @GetMapping
    public MamsSession current() {
        return this.userSessionService.currentSession();
    }
}
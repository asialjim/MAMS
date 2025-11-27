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

import com.asialjim.microapplet.mams.user.api.IdCardUserApi;
import com.asialjim.microapplet.mams.user.vo.UserIdCardAuthenticateReq;
import com.asialjim.microapplet.mams.user.vo.UserIdCardSensitiveVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 证件用户API接口
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/10/13, &nbsp;&nbsp; <em>version:1.0</em>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/id-card")
public class IdCardUserController {
    private final IdCardUserApi idCardUserApi;


    /**
     * 获取用户实名状态
     *
     * @param idType {@link String idType}
     * @return {@link UserIdCardSensitiveVo }
     * @since 2025/11/27
     */
    @GetMapping("/status")
    public UserIdCardSensitiveVo status(@RequestParam(required = false) String idType) {
        return this.idCardUserApi.status(idType);
    }

    /**
     * 用户实名认证
     *
     * @param req {@link UserIdCardAuthenticateReq req}
     * @return {@link UserIdCardSensitiveVo }
     * @since 2025/11/27
     */
    @PostMapping("/authenticate")
    public UserIdCardSensitiveVo authenticate(@RequestBody UserIdCardAuthenticateReq req) {
        return this.idCardUserApi.authenticate(req);
    }
}
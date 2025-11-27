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

package com.asialjim.microapplet.mams.user.api;

import com.asialjim.microapplet.common.security.MamsSession;
import com.asialjim.microapplet.mams.user.vo.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户相关API
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/9/30, &nbsp;&nbsp; <em>version:1.0</em>
 */
public interface UserApi {
    String path = "/user";

    @GetMapping("/{id}")
    UserVo queryByUserid(@PathVariable("id") String userid);

    @GetMapping("/brief")
    UserBriefVo brief();

    @PostMapping("/phone")
    String userPhone(@RequestBody MamsSession session);

    @PutMapping("/nickname")
    String updateNickname(@RequestBody UpdateNicknameReq req);

    @PutMapping("/avatar")
    String updateAvatar(@RequestBody UpdateAvatarReq req);

    @PostMapping("/avatar")
    String uploadAvatar(@RequestPart("avatar") MultipartFile avatar);
}
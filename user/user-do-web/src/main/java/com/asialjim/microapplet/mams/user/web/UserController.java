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

import com.asialjim.microapplet.common.concurrent.ConcurrentRunner;
import com.asialjim.microapplet.common.security.MamsSession;
import com.asialjim.microapplet.mams.user.api.UserApi;
import com.asialjim.microapplet.mams.user.service.UserService;
import com.asialjim.microapplet.mams.user.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户会话控制器
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/9/19, &nbsp;&nbsp; <em>version:1.0</em>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(UserApi.path)
public class UserController implements UserApi {
    private final UserService userService;

    /**
     * 根据用户编号，查询主用户信息
     *
     * @param userid {@link String userid}
     * @return {@link UserVo }
     * @since 2025/10/24
     */
    @Override
    public UserVo queryByUserid(@PathVariable("id") String userid) {
        return this.userService.queryByUserid(userid);
    }

    /**
     * 用户简介信息
     * @since 2025/11/26
     */
    @Override
    public UserBriefVo brief() {
        return this.userService.brief();
    }

    /**
     * 获取指定会话用户手机号
     *
     * @param session {@link MamsSession session}
     * @return {@link String }
     * @since 2025/10/24
     */
    @Override
    public String userPhone(@RequestBody MamsSession session) {
        return this.userService.userPhone(session);
    }

    /**
     * 更新当前用户昵称
     *
     * @param req {@link UpdateNicknameReq req}
     * @return {@link String }
     * @since 2025/10/24
     */
    @Override
    public String updateNickname(@RequestBody UpdateNicknameReq req) {
        return this.userService.updateNickname(req);
    }

    /**
     * 更新当前用户头像
     *
     * @param req {@link UpdateAvatarReq req}
     * @return {@link String }
     * @since 2025/10/24
     */
    @Override
    public String updateAvatar(@RequestBody UpdateAvatarReq req) {
        return this.userService.updateAvatar(req);
    }

    /**
     * 上传当前用户头像并更新
     *
     * @param avatar {@link MultipartFile avatar}
     * @return {@link String }
     * @since 2025/10/24
     */
    @Override
    public String uploadAvatar(@RequestPart("avatar") MultipartFile avatar) {
        return this.userService.uploadAvatar(avatar);
    }
}
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

package com.asialjim.microapplet.mams.wx.common.web;

import com.asialjim.microapplet.hermes.annotation.OnEvent;
import com.asialjim.microapplet.mams.wx.common.api.WeChatUserApi;
import com.asialjim.microapplet.mams.wx.common.event.WeChatUserNicknameUpdated;
import com.asialjim.microapplet.mams.wx.common.vo.UpdateAvatarRequest;
import com.asialjim.microapplet.mams.wx.common.vo.UpdateNicknameRequest;
import com.asialjim.microapplet.wechat.user.WeChatUserRepository;
import com.asialjim.microapplet.wechat.user.WeChatUserVo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信用户服务
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/10/20, &nbsp;&nbsp; <em>version:1.0</em>
 */
@Slf4j
@RestController
@RequestMapping(WeChatUserApi.path)
public class WeChatUserController implements WeChatUserApi {
    @Resource
    private WeChatUserRepository weChatUserRepository;


    @Override
    public WeChatUserVo queryByOpenid(@PathVariable("id") String id) {
        return this.weChatUserRepository.queryByOpenid(id);
    }

    @Override
    public WeChatUserVo updateAvatarByOpenid(@PathVariable("id") String id, @RequestBody UpdateAvatarRequest req) {
        return this.weChatUserRepository.updateAvatarByOpenid(id,req.getAvatar());
    }

    @Override
    public WeChatUserVo updateNicknameByOpenid(@PathVariable("id") String id, @RequestBody UpdateNicknameRequest req) {
        return this.weChatUserRepository.updateNicknameByOpenid(id,req.getNickname());
    }

    @OnEvent
    public void onWeChatUserNicknameUpdated(WeChatUserNicknameUpdated event){
        log.info("微信用户昵称更新事件进入：{}",event);
        this.weChatUserRepository.updateNicknameByOpenid(event.getOpenid(),event.getNickname());
    }
}
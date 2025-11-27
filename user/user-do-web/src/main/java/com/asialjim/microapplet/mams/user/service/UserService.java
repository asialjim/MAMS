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

import com.asialjim.microapplet.common.concurrent.ConcurrentRunner;
import com.asialjim.microapplet.common.security.MamsSession;
import com.asialjim.microapplet.common.security.MamsSessionAttribute;
import com.asialjim.microapplet.mams.app.cons.ChannelAppType;
import com.asialjim.microapplet.mams.app.cons.ChannelType;
import com.asialjim.microapplet.mams.user.infrastructure.datasource.po.ChlUserPo;
import com.asialjim.microapplet.mams.user.infrastructure.datasource.po.UserPo;
import com.asialjim.microapplet.mams.user.infrastructure.datasource.repository.ChlUserRepository;
import com.asialjim.microapplet.mams.user.infrastructure.datasource.repository.UserRepository;
import com.asialjim.microapplet.mams.user.vo.*;
import com.asialjim.microapplet.mams.wx.common.api.WeChatUserApi;
import com.asialjim.microapplet.mams.wx.common.vo.UpdateAvatarRequest;
import com.asialjim.microapplet.mams.wx.common.vo.UpdateNicknameRequest;
import com.asialjim.microapplet.wechat.user.WeChatUserVo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.Optional;

/**
 * 用户服务
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/10/16, &nbsp;&nbsp; <em>version:1.0</em>
 */
@Slf4j
@Service
public class UserService {
    @Resource
    private MamsSessionAttribute mamsSessionAttribute;
    @Resource
    private ChlUserRepository chlUserRepository;
    @Resource
    private UserRepository userRepository;
    @Resource
    private WeChatUserApi weChatUserApi;

    public String updateNickname(UpdateNicknameReq req) {
        //noinspection DuplicatedCode
        Boolean notBlank = Optional.ofNullable(req).map(UpdateNicknameReq::getNickname).map(StringUtils::isNotBlank).orElse(false);
        if (Boolean.FALSE.equals(notBlank))
            return StringUtils.EMPTY;

        MamsSession mamsSession = this.mamsSessionAttribute.currentLoginSession();
        ChannelType channelType = ChannelType.codeOf(mamsSession.getChl());
        // TODO 重构
        //noinspection SwitchStatementWithTooFewBranches
        switch (channelType) {
            case WeChat -> {
                WeChatUserVo weChatUserVo = this.weChatUserApi.updateNicknameByOpenid(mamsSession.getChlUserid(), new UpdateNicknameRequest().setNickname(req.getNickname()));
                return Optional.ofNullable(weChatUserVo).map(WeChatUserVo::getNickname).orElse(StringUtils.EMPTY);
            }
            default -> {

            }
        }
        return req.getNickname();
    }

    public String uploadAvatar(MultipartFile data) {
        try {
            byte[] byteArray = IOUtils.toByteArray(data.getInputStream());
            String avatar = Base64.getEncoder().encodeToString(byteArray);
            return updateAvatar(new UpdateAvatarReq().setAvatar(avatar));
        } catch (Throwable ignored) {

            MamsSession mamsSession = this.mamsSessionAttribute.currentLoginSession();
            return avatar(mamsSession);
        }
    }

    public String updateAvatar(UpdateAvatarReq req) {
        //noinspection DuplicatedCode
        Boolean notBlank = Optional.ofNullable(req).map(UpdateAvatarReq::getAvatar).map(StringUtils::isNotBlank).orElse(false);
        if (Boolean.FALSE.equals(notBlank))
            return StringUtils.EMPTY;

        MamsSession mamsSession = this.mamsSessionAttribute.currentLoginSession();
        ChannelType channelType = ChannelType.codeOf(mamsSession.getChl());
        // TODO 重构
        //noinspection SwitchStatementWithTooFewBranches
        switch (channelType) {
            case WeChat -> {
                WeChatUserVo weChatUserVo = this.weChatUserApi.updateAvatarByOpenid(mamsSession.getChlUserid(), new UpdateAvatarRequest().setAvatar(req.getAvatar()));
                return Optional.ofNullable(weChatUserVo).map(WeChatUserVo::getAvatar).orElse(StringUtils.EMPTY);
            }
            default -> {

            }
        }
        return req.getAvatar();
    }


    private String avatar(MamsSession mamsSession) {
        ChannelType channelType = ChannelType.codeOf(mamsSession.getChl());
        // TODO 重构
        //noinspection SwitchStatementWithTooFewBranches
        switch (channelType) {
            case WeChat -> {
                WeChatUserVo weChatUserVo = weChatUserApi.queryByOpenid(mamsSession.getChlUserid());
                return Optional.ofNullable(weChatUserVo).map(WeChatUserVo::getAvatar).orElse(StringUtils.EMPTY);
            }
            default -> {

            }
        }
        return StringUtils.EMPTY;
    }



    private String nickname(MamsSession mamsSession) {
        log.info("mamsSession...");
        ChannelType channelType = ChannelType.codeOf(mamsSession.getChl());
        log.info("channelType: {}", channelType);
        // TODO 重构
        //noinspection SwitchStatementWithTooFewBranches
        switch (channelType) {
            case WeChat -> {
                log.info("openid: {}", mamsSession.getChlUnionid());
                WeChatUserVo weChatUserVo = weChatUserApi.queryByOpenid(mamsSession.getChlUserid());
                log.info("weChatUserVo: {}", weChatUserVo);
                return Optional.ofNullable(weChatUserVo).map(WeChatUserVo::getNickname).orElse(StringUtils.EMPTY);
            }
            default -> {

            }
        }
        UserPo userPo = this.userRepository.queryById(mamsSession.getUserid());
        return Optional.ofNullable(userPo).map(UserPo::getNickname).orElse(StringUtils.EMPTY);
    }


    public String userPhone(MamsSession mamsSession) {
        String userid = mamsSession.getUserid();
        String chl = mamsSession.getChl();
        String chlAppid = mamsSession.getChlAppid();
        ChannelType channelType = ChannelType.codeOf(chl);
        String chlAppType = StringUtils.EMPTY;
        // TODO 重构
        switch (channelType) {
            case WeChat -> chlAppType = ChannelAppType.WeChatPhone.getCode();
            case Mobile -> chlAppType = ChannelAppType.Phone.getCode();
        }
        log.info("获取用户：{}  {} 渠道 {} 应用下的 {} 手机号", userid, chl, chlAppid, chlAppType);
        if (StringUtils.isBlank(chlAppType))
            return StringUtils.EMPTY;

        ChlUserPo chlUserPo = this.chlUserRepository.queryByUserIdAndChlAndChlAppidAndChlAppType(userid, chl, chlAppid, chlAppType);
        log.info("获取用户：{}  {} 渠道 {} 应用下的 {} 渠道用户信息：{}", userid, chl, chlAppid, chlAppType, chlUserPo);

        return Optional.ofNullable(chlUserPo)
                .map(ChlUserPo::getChlUserid)
                .orElse(StringUtils.EMPTY);
    }



    public UserVo queryByUserid(String userid) {
        UserPo userPo = this.userRepository.queryById(userid);
        return UserPo.toVo(userPo);
    }

    public UserBriefVo brief() {
        UserBriefVo vo = new UserBriefVo();
        MamsSession mamsSession = this.mamsSessionAttribute.currentLoginSession();
        ConcurrentRunner.runAllTask(
                () -> vo.setPhone(userPhone(mamsSession)),
                () -> vo.setNickname(nickname(mamsSession)),
                () -> vo.setAvatar(avatar(mamsSession))
        );
        return vo;
    }
}
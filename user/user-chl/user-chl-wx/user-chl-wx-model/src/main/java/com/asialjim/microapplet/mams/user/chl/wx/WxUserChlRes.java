/*
 *  Copyright 2014-2025 <a href="mailto:asialjim@qq.com">Asial Jim</a>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.asialjim.microapplet.mams.user.chl.wx;

import com.asialjim.microapplet.common.context.ResCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 微信渠道用户响应结果代码
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/3/12, &nbsp;&nbsp; <em>version:1.0</em>
 */
@Getter
@AllArgsConstructor
public enum WxUserChlRes implements ResCode {
    CurrentUserLoginChannelErr(false,"CHL:WX:03","当前用户未从微信渠道登录"),
    WeChatAppTypeNotSupport(false, "CHL:WX:02", "不支持该类型微信公众平台应用登录"),
    WeChatPlatformAppNotSupport(false, "CHL:WX:01", "不支持该微信公众平台应用"),
    WeChatChannel(false, "CHL:WX", "微信渠道业务代码");
    private final boolean success;
    private final String code;
    private final String msg;
}

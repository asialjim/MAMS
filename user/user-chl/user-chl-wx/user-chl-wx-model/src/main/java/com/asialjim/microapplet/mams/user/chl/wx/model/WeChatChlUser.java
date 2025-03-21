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

package com.asialjim.microapplet.mams.user.chl.wx.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 微信渠道登录用户
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/2/24, &nbsp;&nbsp; <em>version:1.0</em>
 */
@Data
public class WeChatChlUser implements Serializable {
    private static final long serialVersionUID = -2939564810854146058L;

    /**
     * 微信公众平台用户编号
     */
    private String openid;

    /**
     * 所属公众平台应用用户的联合id
     */
    private String unionId;

    /**
     * 用户所属微信公众平台应用
     */
    private String appid;

    /**
     * 微信公众平台应用类型:
     * <ul>
     *     <li>1. 公众号</li>
     *     <li>2. 小程序</li>
     * </ul>
     */
    private String appType;

    /**
     * 用户编号
     * @see User#getId()
     */
    private String userid;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
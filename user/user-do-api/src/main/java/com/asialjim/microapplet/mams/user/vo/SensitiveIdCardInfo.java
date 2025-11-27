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

package com.asialjim.microapplet.mams.user.vo;

import com.asialjim.microapplet.sensitive.annotation.Sensitive;
import com.asialjim.microapplet.sensitive.handler.SensitiveType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * 脱敏证件信息
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/10/15, &nbsp;&nbsp; <em>version:1.0</em>
 */
@Data
@Accessors(chain = true)
public class SensitiveIdCardInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = -6577548289048054797L;

    /**
     * 证件类型
     */
    private String type;
    /**
     * 证件名称
     */
    @Sensitive(SensitiveType.ChineseName)
    private String name;
    /**
     * 证件号
     */
    @Sensitive(SensitiveType.ChineseCitizenIdCard)
    private String number;

    /**
     * 证件性别
     */
    private String gender;
    /**
     * 证件国籍
     */
    private String nationality;
    /**
     * 证件生日
     */
    private LocalDate birthday;
    /**
     * 证件地址
     */
    private String address;
    /**
     * 证件发证机关
     */
    private String issue;
    /**
     * 证件签发日期
     */
    private LocalDate issueDate;
    /**
     * 证件有效期至
     */
    private LocalDate issueExpires;

}
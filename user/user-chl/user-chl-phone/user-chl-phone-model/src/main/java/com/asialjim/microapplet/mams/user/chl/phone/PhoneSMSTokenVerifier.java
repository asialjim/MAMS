/*
 * Copyright 2014-2025 <a href="mailto:asialjim@qq.com">Asial Jim</a>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.asialjim.microapplet.mams.user.chl.phone;

/**
 * 手机短信验证码处理器
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/3/26, &nbsp;&nbsp; <em>version:1.0</em>
 */
public interface PhoneSMSTokenVerifier {

    /**
     * 生成验证码
     *
     * @param phone    {@link String phone}
     * @param business {@link String business}
     * @return {@link String }
     * @since 2025/3/26
     */
    String generate(String phone, String business);

    /**
     * 校验验证码
     *
     * @param phone    {@link String phone}
     * @param business {@link String business}
     * @param code     {@link String code}
     * @since 2025/3/26
     */
    void verifier(String phone, String business, String code);
}
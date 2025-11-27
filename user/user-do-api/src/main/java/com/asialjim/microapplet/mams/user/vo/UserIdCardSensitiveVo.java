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

import com.asialjim.microapplet.common.human.IdCardType;
import com.asialjim.microapplet.common.utils.SensitiveUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

/**
 * 用户证件脱敏信息
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/10/15, &nbsp;&nbsp; <em>version:1.0</em>
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class UserIdCardSensitiveVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 2203637435238757238L;

    public UserIdCardSensitiveVo(IdCardUserVo idCard){
        this.realNameInfo = new SensitiveIdCardInfo();
        if (Objects.isNull(idCard))
            return;
        this.realNameInfo.setType(idCard.getIdType());
        this.realNameInfo.setName(idCard.getName());
        this.realNameInfo.setNumber(idCard.getIdNo());
        this.realNameInfo.setGender(idCard.getGender());
        this.realNameInfo.setNationality(idCard.getNationality());
        this.realNameInfo.setBirthday(idCard.getBirthday());
        this.realNameInfo.setAddress(idCard.getAddress());
        this.realNameInfo.setIssue(idCard.getIssue());
        this.realNameInfo.setIssueDate(idCard.getIssueDate());
        this.realNameInfo.setIssueExpires(idCard.getIssueExpires());
    }


    /**
     * 是否实名认证
     */
    private Boolean isVerified;

    /**
     * 实名信息
     */
    private SensitiveIdCardInfo realNameInfo;

    public Boolean getIsVerified(){
        return Optional.ofNullable(this.realNameInfo).map(SensitiveIdCardInfo::getNumber)
                .map(StringUtils::isNotBlank)
                .orElse(false);
    }
}
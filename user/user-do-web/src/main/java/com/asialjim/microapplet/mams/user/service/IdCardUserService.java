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

import com.asialjim.microapplet.common.context.Res;
import com.asialjim.microapplet.common.human.IdCardType;
import com.asialjim.microapplet.common.human.IdCardTypeRs;
import com.asialjim.microapplet.common.security.MamsSession;
import com.asialjim.microapplet.common.security.MamsSessionAttribute;
import com.asialjim.microapplet.mams.user.infrastructure.datasource.repository.IdCardUserRepository;
import com.asialjim.microapplet.mams.user.vo.IdCardUserVo;
import com.asialjim.microapplet.mams.user.vo.UserIdCardAuthenticateReq;
import com.asialjim.microapplet.mams.user.vo.UserIdCardSensitiveVo;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 用户证件信息服务
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/10/15, &nbsp;&nbsp; <em>version:1.0</em>
 */
@Slf4j
@Service
public class IdCardUserService {
    @Resource
    private IdCardUserRepository idCardUserRepository;
    @Resource
    private MamsSessionAttribute mamsSessionAttribute;

    public UserIdCardSensitiveVo current(String idType) {
        IdCardType idCardType = StringUtils.isNotBlank(idType) ? IdCardType.codeOf(idType) : IdCardType.ResidentIdentityCard;
        String userid = mamsSessionAttribute.currentUserid();
        if (StringUtils.isBlank(userid))
            return new UserIdCardSensitiveVo();

        IdCardUserVo idCardUserVo = this.idCardUserRepository.queryByUseridAndIdType(userid, idCardType.getCode());
        return new UserIdCardSensitiveVo(idCardUserVo);
    }

    /**
     * 用户实名认证
     *
     * @param req {@link UserIdCardAuthenticateReq req}
     * @return {@link UserIdCardSensitiveVo }
     * @since 2025/10/16
     */
    public UserIdCardSensitiveVo authenticate(UserIdCardAuthenticateReq req) {
        MamsSession mamsSession = this.mamsSessionAttribute.currentLoginSession();
        IdCardType idCardType = IdCardType.codeOfOpt(req.getIdType()).orElseThrow(IdCardTypeRs.UnSupport::ex);
        String name = req.getName();
        String number = req.getNumber();
        String verifyChl = req.getVerifyChl();
        JsonNode verifyParam = req.getVerifyParam();
        IdCardUserVo exist = this.idCardUserRepository.queryByUseridAndIdTypeAndIdNo(mamsSession.getUserid(), idCardType.getCode(), number);
        // 实名去
        if (Objects.isNull(exist)) {
            IdCardUserVo idCardUser = new IdCardUserVo();
            idCardUser.setAppid(mamsSession.getAppid());
            idCardUser.setUserid(mamsSession.getUserid());
            idCardUser.setIdType(idCardType.getCode());
            idCardUser.setIdNo(number);
            idCardUser.setName(name);
            idCardUser = this.idCardUserRepository.save(idCardUser);
            log.info("实名结果：{}", idCardUser);
        } else {
            exist.setName(name);
            exist.setIdNo(number);
            exist.setIdType(idCardType.getCode());
            this.idCardUserRepository.updateById(exist);
        }

        // 真人认证
        if (StringUtils.isNotBlank(verifyChl) && Objects.nonNull(verifyParam) && verifyParam.isObject()) {
            // TODO
            // TODO 当前 ，verifyChl 已有：wx.startFacialRecognitionVerify",
            log.info("将进行真人认证");
        }

        return this.current(req.getIdType());
    }

    public List<String> queryUseridByNameOfIdNoForAppid(String name, String idNo, String appid) {
        if (StringUtils.isAllBlank(name, idNo))
            Res.ParameterEmptyEx.thr(Collections.singletonList("姓名、证件号不可都为空"));
        if (StringUtils.isNotBlank(appid))
            Res.ParameterEmptyEx.thr(Collections.singletonList("应用编号不可都为空"));
        return this.idCardUserRepository.queryUseridByNameOfIdNoForAppid(name, idNo, appid);
    }
}
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

package com.asialjim.microapplet.mams.user.infrastructure.datasource.repository;

import com.asialjim.microapplet.mams.user.infrastructure.datasource.po.IdCardUserPo;
import com.asialjim.microapplet.mams.user.infrastructure.datasource.service.IdCardUserMapperService;
import com.asialjim.microapplet.mams.user.vo.IdCardUserVo;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryChain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 证件用户表数仓
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/9/22, &nbsp;&nbsp; <em>version:1.0</em>
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class IdCardUserRepository {
    private final IdCardUserMapperService idCardUserMapperService;


    public List<IdCardUserVo> queryByUserid(String userid) {
        List<IdCardUserPo> pos = this.idCardUserMapperService.queryByUserid(userid);
        if (CollectionUtils.isEmpty(pos))
            return Collections.emptyList();
        return pos.stream()
                .map(IdCardUserPo::toVo)
                .filter(Objects::nonNull)
                .toList();
    }

    public IdCardUserVo queryByUseridAndIdType(String userid, String idType) {
        IdCardUserPo po = this.idCardUserMapperService.queryByUseridAndIdType(userid, idType);
        return IdCardUserPo.toVo(po);
    }

    public IdCardUserVo queryByUseridAndIdTypeAndIdNo(String userid, String idType, String idNo) {
        IdCardUserPo po = this.idCardUserMapperService.queryByUseridAndIdTypeAndIdNo(userid, idType, idNo);
        return IdCardUserPo.toVo(po);
    }

    public IdCardUserVo save(IdCardUserVo vo) {
        IdCardUserPo po = IdCardUserPo.fromVo(vo);
        boolean save = this.idCardUserMapperService.save(po);
        log.info("新增证件用户：{} 结果：{}", po, save);
        return IdCardUserPo.toVo(po);
    }

    public List<String> queryUseridByNameOfIdNoForAppid(String name, String idNo, String appid) {
        QueryChain<IdCardUserPo> chain = this.idCardUserMapperService.queryChain();
        if (StringUtils.isNotBlank(name))
            chain.where(IdCardUserPo::getName).eq(name);
        if (StringUtils.isNotBlank(idNo))
            chain.where(IdCardUserPo::getIdNo).eq(idNo);
        if (StringUtils.isNotBlank(appid))
            chain.where(IdCardUserPo::getAppid).eq(appid);
        //noinspection unchecked
        return chain.select(IdCardUserPo::getUserid)
                .pageAs(Page.of(1, 1000), String.class)
                .getRecords();
    }

    public void updateById(IdCardUserVo exist) {
        if (Objects.nonNull(exist)) {
            boolean b = this.idCardUserMapperService.updateById(IdCardUserPo.fromVo(exist));
            log.info("更新用户实名信息：{} 结果： {}", exist, b);
        }
    }
}
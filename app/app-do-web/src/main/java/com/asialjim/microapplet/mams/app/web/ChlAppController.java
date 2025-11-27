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

package com.asialjim.microapplet.mams.app.web;

import com.asialjim.microapplet.mams.app.api.ChlAppApi;
import com.asialjim.microapplet.mams.app.cons.ChannelAppType;
import com.asialjim.microapplet.mams.app.cons.ChannelType;
import com.asialjim.microapplet.mams.app.infrastructure.datasource.repository.ChlAppRepository;
import com.asialjim.microapplet.mams.app.vo.ChlAppVo;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * 渠道 APP 服务
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/9/18, &nbsp;&nbsp; <em>version:1.0</em>
 */
@RestController
@RequestMapping(ChlAppApi.path)
public class ChlAppController implements ChlAppApi {
    @Resource
    private ChlAppRepository chlAppRepository;


    /**
     * 按id查询
     *
     * @param id id
     * @return {@link ChlAppVo}
     */
    @Override
    public ChlAppVo queryById(@PathVariable("id") String id) {
        return this.chlAppRepository.queryById(id);
    }

    /**
     * 按appid查询
     *
     * @param appid appid
     * @return {@link List<ChlAppVo>}
     */
    @Override
    public List<ChlAppVo> queryByAppid(@PathVariable("id") String appid) {
        return this.chlAppRepository.queryByAppid(appid);
    }

    /**
     * 按appid和CHL查询
     *
     * @param appid appid
     * @param chl   的背影
     * @return {@link List<ChlAppVo>}
     */
    @Override
    public List<ChlAppVo> queryByAppidAndChl(@PathVariable("id") String appid, @PathVariable("chl") String chl) {
        return this.chlAppRepository.queryByAppidAndChl(appid, chl);
    }

    /**
     * 按appid、CHL和CHL appid查询
     *
     * @param appid    appid
     * @param chl      的背影
     * @param chlAppid 的背影appid
     * @return {@link ChlAppVo}
     */
    @Override
    public ChlAppVo queryByAppidAndChlAndChlAppid(@PathVariable("id") String appid, @PathVariable("chl") String chl, @PathVariable("chlAppid") String chlAppid) {
        return this.chlAppRepository.queryByAppidAndChlAndChlAppid(appid, chl, chlAppid);
    }

    @Override
    public ChlAppVo queryByAppidAndChlAndChlAppType(@PathVariable("id") String appid,
                                             @PathVariable("chl") String chl,
                                             @PathVariable("chlAppType") String chlAppType) {
        return this.chlAppRepository.queryByAppidAndChlAndChlAppType(appid, chl, chlAppType);
    }

    @Override
    public ChlAppVo queryRootByAppid(@PathVariable("id")String appid) {
        List<ChlAppVo> vos = this.chlAppRepository.queryByAppidAndChl(appid, ChannelType.PC.getCode());
        return vos.stream()
                .filter(Objects::nonNull)
                .filter(item -> StringUtils.equals(item.getChlAppId(), "root"))
                .filter(item -> StringUtils.equals(item.getChlAppType(), ChannelAppType.ROOT.getCode()))
                .findAny()
                .orElseGet(() -> {
                    ChlAppVo vo = new ChlAppVo();
                    vo.setId("root");
                    vo.setAppid(appid);
                    vo.setOrgId("root");
                    vo.setChlType(ChannelType.PC.getCode());
                    vo.setChlAppId("root");
                    vo.setChlAppType(ChannelAppType.ROOT.getCode());
                    vo.setChlAppName("超管应用");
                    vo.setDeleted(false);
                    vo.setCreateTime(LocalDateTime.now());
                    vo.setUpdateTime(LocalDateTime.now());
                    return chlAppRepository.create(vo);
                });
    }

    /**
     * 按组织id查询
     *
     * @param orgId org id
     * @return {@link List<ChlAppVo>}
     */
    @Override
    public List<ChlAppVo> queryByOrgId(@PathVariable("id") String orgId) {
        return this.chlAppRepository.queryByOrgId(orgId);
    }

    /**
     * 按CHL和CHL appid查询
     *
     * @param chl   的背影
     * @param appid appid
     * @return {@link ChlAppVo}
     */
    @Override
    public ChlAppVo queryByChlAndChlAppid(@PathVariable("chl") String chl, @PathVariable("appid") String appid) {
        return this.chlAppRepository.queryByChlAndChlAppid(chl, appid);
    }

    /**
     * 按CHL和CHL索引查询
     *
     * @param chl   的背影
     * @param appid appid
     * @return {@link ChlAppVo}
     */
    @Override
    public ChlAppVo queryByChlAndChlIndex(@PathVariable("chl") String chl, @PathVariable("index") String appid) {
        return this.chlAppRepository.queryByChlAndChlIndex(chl, appid);
    }

    /**
     * 按CHL查询
     *
     * @param code 代码
     * @return {@link List<ChlAppVo>}
     */
    @Override
    public List<ChlAppVo> queryByChl(@PathVariable("chl") String code) {
        return this.chlAppRepository.queryByChl(code);
    }
}
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

package com.asialjim.microapplet.mams.user.infrastructure.datasource.service.impl;

import com.asialjim.microapplet.mams.user.infrastructure.cache.UserCache;
import com.asialjim.microapplet.mams.user.infrastructure.datasource.mapper.IdCardUserBaseMapper;
import com.asialjim.microapplet.mams.user.infrastructure.datasource.po.IdCardUserPo;
import com.asialjim.microapplet.mams.user.infrastructure.datasource.service.IdCardUserMapperService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 证件用户表持久化服务
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/9/22, &nbsp;&nbsp; <em>version:1.0</em>
 */
@Repository
public class IdCardUserMapperServiceImpl extends ServiceImpl<IdCardUserBaseMapper, IdCardUserPo> implements IdCardUserMapperService {

    @Override
    @Cacheable(value = UserCache.Name.idCardUserPosOf, key = "#userid")
    public List<IdCardUserPo> queryByUserid(String userid) {
        return queryChain().where(IdCardUserPo::getUserid).eq(userid).list();
    }

    @Override
    @Cacheable(value = UserCache.Name.idCardUserPoOf, key = "#userid + ':' + #idType")
    public IdCardUserPo queryByUseridAndIdType(String userid, String idType) {
        return queryChain().where(IdCardUserPo::getUserid).eq(userid).where(IdCardUserPo::getIdType).eq(idType).one();
    }

    @Override
    @Cacheable(value = UserCache.Name.idCardUserPoOf, key = "#userid + ':' + #idType + ':' + #idNo")
    public IdCardUserPo queryByUseridAndIdTypeAndIdNo(String userid, String idType, String idNo) {
        return queryChain()
                .where(IdCardUserPo::getUserid).eq(userid)
                .where(IdCardUserPo::getIdType).eq(idType)
                .where(IdCardUserPo::getIdNo).eq(idNo)
                .one();
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = UserCache.Name.idCardUserPosOf, key = "#po.userid"),
                    @CacheEvict(value = UserCache.Name.idCardUserPosOf, key = "#po.userid + ':' + #po.idType"),
                    @CacheEvict(value = UserCache.Name.idCardUserPosOf, key = "#po.userid + ':' + #po.idType + ':' + #po.idNo")
            }
    )
    public boolean updateById(IdCardUserPo po) {
        return super.updateById(po);
    }
}
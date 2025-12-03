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
 */tations under the License.
 */

package com.asialjim.microapplet.mams.user.infrastructure.repository;

import com.asialjim.microapplet.common.security.MamsSession;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * 会话存储
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/9/23, &nbsp;&nbsp; <em>version:1.0</em>
 */
@Component
public class SessionRepository {

    /**
     * 设置缓存
     *
     * @param baseUser 基本用户
     * @return {@link MamsSession}
     */
    @CachePut(value = SessionCacheName.Name.userSessionByToken,key = "#baseUser.token")
    public MamsSession setCache(final MamsSession baseUser) {
        return baseUser;
    }


    /**
     * 获取缓存
     *
     * @param token 令牌
     * @return {@link MamsSession}
     */
    @Cacheable(value = SessionCacheName.Name.userSessionByToken,key = "#token")
    public MamsSession getCache(@SuppressWarnings("unused") final String token){
        return null;
    }

    /**
     * 删除缓存
     *
     * @param token 令牌
     */
    @CacheEvict(value = SessionCacheName.Name.userSessionByToken,key = "#token")
    public void deleteCache(@SuppressWarnings("unused") final String token){

    }
}
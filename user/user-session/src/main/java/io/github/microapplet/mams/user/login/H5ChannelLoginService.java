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

package io.github.microapplet.mams.user.login;

import io.github.microapplet.mams.user.model.User;
import io.github.microapplet.mams.user.parameter.LoginParameter;
import io.github.microapplet.mams.user.repository.UserRepository;
import io.github.microapplet.mams.user.res.UserResCode;
import io.github.microapplet.mams.user.session.SessionUser;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

/**
 * H5 渠道用户登录服务
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/3/11, &nbsp;&nbsp; <em>version:1.0</em>
 */
@Component
public class H5ChannelLoginService implements ChannelLoginService{
    @Resource private UserRepository userRepository;
    @Override
    public String chlCode() {
        return "H5";
    }

    @Override
    public SessionUser login(LoginParameter loginParameter) {
        String appid = loginParameter.getAppid();
        String username = loginParameter.getUsername();
        String password = loginParameter.getUserCode();

        User user = this.userRepository.queryByAppidUsernameAndPassword(appid,username, password);
        if (Objects.isNull(user))
            UserResCode.UserNameOrPasswordErr.throwBiz();
        SessionUser sessionUser = new SessionUser();
        sessionUser.setId("");// TODO
        sessionUser.setAuthorization("");// TODO
        sessionUser.setAppid(appid);
        sessionUser.setUserid(user.getId());
        sessionUser.setChlCode(loginParameter.getChlCode());
        sessionUser.setChlAppid(loginParameter.getChlAppid());
        sessionUser.setChlAppType(loginParameter.getChlAppType());
        sessionUser.setNickname(user.getNickname());
        sessionUser.setUsername(user.getUserName());
        sessionUser.setUserCode(password);

        return sessionUser;
    }
}
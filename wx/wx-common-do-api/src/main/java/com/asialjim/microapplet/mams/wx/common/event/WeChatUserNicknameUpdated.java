package com.asialjim.microapplet.mams.wx.common.event;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * 微信用户昵称更新事件
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2026/1/7, &nbsp;&nbsp; <em>version:1.0</em>
 */
@Data
@Accessors(chain = true)
public class WeChatUserNicknameUpdated implements Serializable {

    @Serial
    private static final long serialVersionUID = -4168460972368498740L;

    private String openid;
    private String nickname;
}
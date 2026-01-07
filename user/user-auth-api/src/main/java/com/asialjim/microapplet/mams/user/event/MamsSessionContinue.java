package com.asialjim.microapplet.mams.user.event;

import com.asialjim.microapplet.common.security.MamsSession;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户会话保持事件
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2026/1/7, &nbsp;&nbsp; <em>version:1.0</em>
 */
@Data
@Accessors(chain = true)
public class MamsSessionContinue implements Serializable {

    @Serial
    private static final long serialVersionUID = 1903094050581907703L;
    private MamsSession session;
}
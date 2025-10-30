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

package com.asialjim.microapplet.mams.wx.mp.service.msg.callback.handler;

import com.asialjim.microapplet.common.concurrent.ConcurrentRunner;
import com.asialjim.microapplet.wechat.official.service.msg.CallbackMsgHandler;
import com.asialjim.microapplet.wechat.official.service.msg.WeChatOfficialMsgCallbackEvent;
import com.asialjim.microapplet.wechat.official.service.msg.reply.WxMpXmlOutMessage;
import com.asialjim.microapplet.wechat.official.remoting.ai.WeChatAIVoiceRemoting;
import com.asialjim.microapplet.wechat.official.remoting.ai.meta.WeChatQueryRecoResultForTextRes;
import com.asialjim.microapplet.wechat.remoting.message.customer.WeChatCustomerMessageRemoting;
import com.asialjim.microapplet.wechat.remoting.message.customer.meta.WeChatCustomerTextMessage;
import com.asialjim.microapplet.wechat.remoting.message.customer.meta.item.Text;
import com.asialjim.microapplet.wechat.remoting.context.BaseWeChatApiRes;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
//import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 自然语言处理器
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/9/25, &nbsp;&nbsp; <em>version:1.0</em>
 */
@Slf4j
@Component
public class NaturalLanguageCallbackMsgHandler implements CallbackMsgHandler {
    @Resource
    private WeChatAIVoiceRemoting weChatAIVoiceRemoting;

//    @Resource
//    private ToolCallbackProvider loadbalancedMcpAsyncToolCallbacks;
//    @Resource
//    private ChatClient.Builder chatClientBuilder;

    @Resource
    private WeChatCustomerMessageRemoting weChatCustomerMessageRemoting;

    @Override
    public boolean support(String msgType) {
        // natural language
        return StringUtils.equalsAny(msgType, "text", "voice");
    }

    @Override
    public WxMpXmlOutMessage handle(WeChatOfficialMsgCallbackEvent event) {
        String msgType = event.getMsgType();
        String content;
        if (StringUtils.equalsAny(msgType, "voice")) {
            String mediaId = event.nodeTextValue("MediaId");
            //  将语音 识别为自然语言
            WeChatQueryRecoResultForTextRes textRes = this.weChatAIVoiceRemoting.queryRecoResultForText(event.getAppid(), mediaId);
            content =
                    Optional.ofNullable(textRes)
                            .map(WeChatQueryRecoResultForTextRes::getResult)
                            .orElse(StringUtils.EMPTY);
        } else {
            content = event.nodeTextValue("Content");
        }
        log.info("用户发送文本信息:{}", content);

        WxMpXmlOutMessage out = new WxMpXmlOutMessage();
        out.setMsgType("text");
        out.setContent("你好");
        ConcurrentRunner.runAllTask(new Runnable() {
            @Override
            public void run() {
                WeChatCustomerTextMessage msg = new WeChatCustomerTextMessage();
                msg.setTouser(event.getOpenid());
                msg.setText(Text.builder().content("Are you OK?").build());
                BaseWeChatApiRes apiRes = weChatCustomerMessageRemoting.sendCustomerMsg(event.getAppid(), msg);
                log.info("\r\n发送客服消息：{}\r\n结果：{}", msg, apiRes);
            }
        });

        return out;

        // todo 发送给 AI
        // todo 同时发送给坐席
        // todo 如果坐席接管、则不再发送给 AI
    /*    ConcurrentRunner.runAllTask(() -> {
            if (StringUtils.isBlank(content))
                return;
            ChatClient client = chatClientBuilder.defaultToolCallbacks(loadbalancedMcpAsyncToolCallbacks).build();

            // 用户标识

            String queryBody = "微信公众号[" + event.getAppid() + "]" +
                    "用户[" + event.getOpenid() + "]" +
                    "发生公众号交互,交互时间戳[" + event.nodeTextValue("CreateTime") + "]" +
                    ",交互编号[" + event.nodeTextValue("MsgId") + "]" +
                    "消息类型[" + event.getMsgType() + "]" +
                    "消息内容：[" + content + "]";
            String res = client.prompt(queryBody).call().content();
            WeChatCustomerTextMessage msg = new WeChatCustomerTextMessage();
            msg.setTouser(event.getOpenid());
            msg.setText(Text.builder().content(res).build());
            BaseWeChatApiRes apiRes = this.weChatPaCustomerMessageRemoting.sendCustomerMsg(event.getAppid(), msg);
            log.info("\r\n发送客服消息：{}\r\n结果：{}", msg, apiRes);
        });*/
        //return null;
    }
}
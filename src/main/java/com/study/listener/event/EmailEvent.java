package com.study.listener.event;

import org.springframework.context.ApplicationEvent;

/**
 * ClassName: EmailEvent
 * Description: 邮件通知事件
 * Author: luohx
 * Date: 2022/1/12 下午3:29
 * History:
 * <author>          <time>          <version>          <desc>
 * luohx            修改时间           1.0           邮件通知事件
 */
public class EmailEvent extends ApplicationEvent {
    public EmailEvent(Object source) {
        super(source);
    }

    private String email;

    private String content;

    public EmailEvent(Object source, String email, String content) {
        super(source);
        this.email = email;
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

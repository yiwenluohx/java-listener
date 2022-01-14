package com.study.listener.listener;

import com.study.listener.event.EmailEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * ClassName: EmailListener
 * Description: 邮件发送监听
 * @Author: luohx
 * Date: 2022/1/12 下午3:32
 * History:
 * <author>          <time>          <version>          <desc>
 * luohx            修改时间           1.0           邮件发送监听
 */
@Component
public class EmailListener implements ApplicationListener<EmailEvent> {

    @Override
    public void onApplicationEvent(EmailEvent emailEvent) {
        System.out.println("邮件地址：" + emailEvent.getEmail());
        System.out.println("邮件内容：" + emailEvent.getContent());
    }
}

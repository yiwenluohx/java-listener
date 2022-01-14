package com.study.listener.service.impl;

import com.study.listener.event.EmailEvent;
import com.study.listener.service.IListenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

/**
 * ClassName: ListenerServiceImpl
 * Description: 具体监听器实现
 *
 * @Author: luohx
 * Date: 2022/1/12 上午10:43
 * History:
 * <author>          <time>          <version>          <desc>
 * luohx            修改时间           1.0           具体监听器实现
 */
@Service
public class ListenerServiceImpl implements IListenerService {

    @Autowired
    private WebApplicationContext webapplicationcontext;

    @Override
    public void springListener() {
        EmailEvent emailEvent = new EmailEvent("object", "172572575@qq.com", "###listener");
        webapplicationcontext.publishEvent(emailEvent);
    }
}

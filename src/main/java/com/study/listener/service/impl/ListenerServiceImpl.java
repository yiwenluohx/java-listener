package com.study.listener.service.impl;

import com.study.listener.event.EmailEvent;
import com.study.listener.service.IListenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

//    @Autowired
//    private WebApplicationContext webapplicationcontext; //实现了ApplicationContext

    @Autowired
    private ApplicationContext context;

    @Override
    public void springListener() {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(2);
        executorService.schedule(() -> {
            EmailEvent emailEvent = new EmailEvent("object", "815268151@qq.com", "###我的listener");
            context.publishEvent(emailEvent);
        }, 3, TimeUnit.SECONDS);
    }
}

package com.study.listener.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * ClassName: MyListener
 * Description: 实现我自己的监听器
 *
 * @Author: luohx
 * Date: 2022/1/12 上午10:46
 * History:
 * <author>          <time>          <version>          <desc>
 * luohx            修改时间           1.0           实现我自己的监听器
 */
@Component
public class MyListener implements ApplicationListener<ContextRefreshedEvent>  {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("容器中初始化Bean数量:" + event.getApplicationContext().getBeanDefinitionCount());
    }

//    @Override
//    public void onApplicationEvent(ApplicationEvent event) {
//        System.out.println("收到事件：" + event.toString());
//    }
}

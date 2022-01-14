package com.study.listener.event;

import org.springframework.context.ApplicationEvent;

/**
 * ClassName: MyApplicationEvent
 * Description: 我的事件
 *
 * @Author: luohx
 * Date: 2022/1/12 下午2:03
 * History:
 * <author>          <time>          <version>          <desc>
 * luohx            修改时间           1.0           我的事件
 */
public class MyApplicationEvent extends ApplicationEvent {
    public MyApplicationEvent(Object source) {
        super(source);
    }
}

package com.study.listener.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName: DaemonThreadFactory
 * Description: 守护线程工厂
 *
 * @Author: luohx
 * Date: 2022/1/18 下午3:50
 * History:
 * <author>          <time>          <version>          <desc>
 * luohx            修改时间           1.0           守护线程工厂
 */
public class DaemonThreadFactory implements ThreadFactory {

    private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    public DaemonThreadFactory(final String prefix) {
        namePrefix = prefix + "-" + POOL_NUMBER.getAndIncrement() + "-thread-";
    }

    @Override
    public Thread newThread(final Runnable runnable) {
        Thread t = new Thread(runnable, namePrefix + threadNumber.getAndIncrement());
        //设置线程为守护线程，主线程退出，子线程也随之退出
        t.setDaemon(true);
        return t;
    }
}

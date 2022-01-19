package com.study.listener.threadpool;

import java.util.concurrent.Callable;

/**
 * ClassName: CheckCallable
 * Description: 实现Callable创建线程
 * Author: luohx
 * Date: 2022/1/19 下午2:22
 * History:
 * <author>          <time>          <version>          <desc>
 * luohx            修改时间           1.0           实现Callable创建线程
 */
public class CheckCallable<V> implements Callable<V> {
    private Callable<V> callable;

    private CheckThreadLocalInfo threadLocalInfo;

    public CheckCallable(Callable<V> callable) {
        this.callable = callable;
        this.threadLocalInfo = new CheckThreadLocalInfo();
    }

    @Override
    public V call() throws Exception {
        try {
            threadLocalInfo.setThreadLocalInfo();
            return callable.call();
        } finally {
            threadLocalInfo.close();
        }
    }
}

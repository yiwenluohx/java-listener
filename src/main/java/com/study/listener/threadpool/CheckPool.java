package com.study.listener.threadpool;

/**
 * ClassName: CheckPool
 * Description: 线程池
 * @Author: luohx
 * Date: 2022/1/18 下午2:13
 * History:
 * <author>          <time>          <version>          <desc>
 * luohx            修改时间           1.0           线程池
 */
public interface CheckPool {
    int SCHEDULED_THREAD_POOL = 1;
    int THREAD_POOL = 0;

    int getPoolType();
}

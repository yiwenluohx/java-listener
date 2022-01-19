package com.study.listener.threadpool;

import com.study.listener.utils.CurrentContextUtil;
import com.zaxxer.hikari.util.UtilityElf;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

/**
 * ClassName: CheckScheduledThreadPool
 * Description:
 * @Author: luohx
 * Date: 2022/1/18 下午8:12
 * History:
 * <author>          <time>          <version>          <desc>
 * luohx            修改时间           1.0
 */
public class CheckScheduledThreadPool extends ScheduledThreadPoolExecutor implements CheckPool {

    private String poolName;

    public static void putPool(ThreadPoolEnum threadPoolEnums) {
        if (threadPoolEnums.getPoolType() != CheckPool.SCHEDULED_THREAD_POOL) {
            return;
        }
        CheckScheduledThreadPool threadPool = newThreadPool(threadPoolEnums);
        CurrentContextUtil.poolMap.put(threadPoolEnums.getPoolName(), CheckExecutorService.wrap((threadPool)));
    }

    public static CheckScheduledThreadPool newThreadPool(ThreadPoolEnum threadPoolEnum) {
        CheckScheduledThreadPool scheduledThreadPool = new CheckScheduledThreadPool(threadPoolEnum.getPoolType(), new UtilityElf.DefaultThreadFactory(threadPoolEnum.getPoolName(), false));
        scheduledThreadPool.poolName = threadPoolEnum.getPoolName();
        return scheduledThreadPool;
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
    }


    @Override
    public void execute(Runnable command) {
        super.execute(command);
    }


    private CheckScheduledThreadPool(int corePoolSize, ThreadFactory threadFactory) {
        super(corePoolSize, threadFactory);
    }

    @Override
    public int getPoolType() {
        return CheckPool.SCHEDULED_THREAD_POOL;
    }
}

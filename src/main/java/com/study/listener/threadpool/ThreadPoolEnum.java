package com.study.listener.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: ThreadPoolEnum
 * Description: 线程池枚举
 *
 * @Author: luohx
 * Date: 2022/1/18 下午3:11
 * History:
 * <author>          <time>          <version>          <desc>
 * luohx            修改时间           1.0              线程池枚举
 */
public enum ThreadPoolEnum {

    /**
     * 参数 先保持 与 RDFAAsyncConfig 一致
     */
    SPRING_ASYNC(CheckThreadPool.THREAD_POOL, "RDFA-QA-spring-async",
            3,
            20,
            60,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(200),
            "spring异步线程池"),

    /**
     * 调度
     */
    scheduled_task_result(CheckThreadPool.SCHEDULED_THREAD_POOL, 1, "RDFA-QA-scheduled-task-result",
            "推送仓配结果"),
    ;

    private int poolType;
    private String poolName;
    private int corePoolSize;
    private int maximumPoolSize;
    private long keepAliveTime;
    private TimeUnit unit;
    private BlockingQueue<Runnable> workQueue;
    private String desc;

    /**
     * 传统线程池
     */
    ThreadPoolEnum(int poolType, String poolName, int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, String desc) {
        this.poolType = poolType;
        this.poolName = poolName;
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
        this.unit = unit;
        this.workQueue = workQueue;
        this.desc = desc;
    }

    /**
     * 调度线程池
     */
    ThreadPoolEnum(int poolType, int corePoolSize, String poolName, String desc) {
        this.poolType = poolType;
        this.poolName = poolName;
        this.corePoolSize = corePoolSize;
        this.desc = desc;
    }

    public String getPoolName() {
        return poolName;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public long getKeepAliveTime() {
        return keepAliveTime;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public BlockingQueue<Runnable> getWorkQueue() {
        return workQueue;
    }

    public String getDesc() {
        return desc;
    }

    public int getPoolType() {
        return poolType;
    }

    public void setPoolType(int poolType) {
        this.poolType = poolType;
    }
}

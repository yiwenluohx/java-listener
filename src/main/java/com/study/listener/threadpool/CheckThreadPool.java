package com.study.listener.threadpool;

import com.study.listener.utils.CurrentContextUtil;
import com.zaxxer.hikari.util.UtilityElf;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.*;

/**
 * ClassName: CheckThreadPool
 * Description: 自定义线程池
 *
 * @Author: luohx
 * Date: 2022/1/18 下午2:48
 * History:
 * <author>          <time>          <version>          <desc>
 * luohx            修改时间           1.0           自定义线程池
 */
@Slf4j
public class CheckThreadPool extends ThreadPoolExecutor implements CheckPool, Runnable {

    /**
     * 线程池名称
     */
    private String poolName;

    /**
     * 已使用线程警戒阈值
     */
    private static final double ALARM_PERCENT = 0.90;

    /**
     * 初始化一个普通线程池
     *
     * @param threadPoolEnums
     * @return
     */
    public static CheckThreadPool newThreadPool(ThreadPoolEnum threadPoolEnums) {
        return new CheckThreadPool(threadPoolEnums.getPoolName(), threadPoolEnums.getCorePoolSize(), threadPoolEnums.getMaximumPoolSize(), threadPoolEnums.getKeepAliveTime(), threadPoolEnums.getUnit(), threadPoolEnums.getWorkQueue(), new UtilityElf.DefaultThreadFactory(threadPoolEnums.getPoolName(), false));
    }

    public static void putPool(ThreadPoolEnum threadPoolEnum) {
        if (threadPoolEnum.getPoolType() != CheckThreadPool.THREAD_POOL) {
            return;
        }
        CheckThreadPool threadPool = newThreadPool(threadPoolEnum);
        CurrentContextUtil.poolMap.put(threadPoolEnum.getPoolName(), CheckExecutorService.wrap(threadPool));
    }

    public CheckThreadPool(String poolName, int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        //拒绝策略是主线层来处理
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, new CallerRunsPolicy());
        this.poolName = poolName;
        //打印日志 每分钟检测一次
        /**
         * scheduleWithFixedDelay参数解析
         *
         * command - 要执行的任务
         * initialdelay - 首次执行的延迟时间
         * delay - 一次执行终止和下一次执行开始之间的延迟
         * unit - initialdelay 和 delay 参数的时间单位
         */
        Executors.newSingleThreadScheduledExecutor(new DaemonThreadFactory("poll-monitor")).scheduleWithFixedDelay(this, 1, 60, TimeUnit.SECONDS);
    }

    @Override
    public void run() {
        final int activeCount = this.getActiveCount();
        // 总计线程数
        final int poolSize = this.getCorePoolSize();
        double used = (double) activeCount / poolSize;
        final int usedNum = (int) (used * 100);
        log.debug("线程池执行状态:[{}/{}]:{}%", activeCount, poolSize, usedNum);
        if (used >= ALARM_PERCENT) {
            log.error("超出警戒值！poolName:{}, 当前已使用量:{}%", poolName, usedNum);
        }
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

    @Override
    public int getPoolType() {
        return CheckThreadPool.THREAD_POOL;
    }
}

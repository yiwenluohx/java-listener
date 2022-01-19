package com.study.listener.threadpool;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * ClassName: CheckExecutorService
 * Description:
 *
 * @Author: luohx
 * Date: 2022/1/19 上午10:36
 * History:
 * <author>          <time>          <version>          <desc>
 * luohx            修改时间           1.0
 */
public class CheckExecutorService implements ExecutorService, ScheduledExecutorService {

    private final ExecutorService impl;
    private final ScheduledExecutorService scheduledImpl;

    public CheckExecutorService(ExecutorService impl, ScheduledExecutorService scheduledImpl) {
        this.impl = impl;
        this.scheduledImpl = scheduledImpl;
    }

    public static ExecutorService wrap(ExecutorService executorService) {
        return new CheckExecutorService(executorService, null);
    }

    public static ExecutorService wrap(ScheduledExecutorService scheduledExecutorService) {
        return new CheckExecutorService(null, scheduledExecutorService);
    }


    @Override
    public void shutdown() {
        impl.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return impl.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return impl.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return impl.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return impl.awaitTermination(timeout, unit);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return impl.submit(new CheckCallable<>(task));
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return impl.submit(new CheckRunnable(task), result);
    }

    @Override
    public Future<?> submit(Runnable task) {
        return impl.submit(task);
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return impl.invokeAll(tasks.stream()
                .map(CheckCallable::new)
                .collect(Collectors.toList()));
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        return impl.invokeAll(tasks.stream()
                .map(CheckCallable::new)
                .collect(Collectors.toList()), timeout, unit);
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return impl.invokeAny(tasks.stream()
                .map(CheckCallable::new)
                .collect(Collectors.toList()));
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return impl.invokeAny(tasks.stream()
                .map(CheckCallable::new)
                .collect(Collectors.toList()), timeout, unit);
    }

    @Override
    public void execute(Runnable command) {
        impl.execute(new CheckRunnable(command));
    }

    public ExecutorService getImpl() {
        return impl;
    }


    @Override
    public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        return scheduledImpl.schedule(new CheckRunnable(command), delay, unit);
    }

    @Override
    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
        return scheduledImpl.schedule(new CheckCallable<>(callable), delay, unit);
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        return scheduledImpl.scheduleAtFixedRate(new CheckRunnable(command), initialDelay, period, unit);
    }

    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        return scheduledImpl.scheduleAtFixedRate(new CheckRunnable(command), initialDelay, delay, unit);
    }
}

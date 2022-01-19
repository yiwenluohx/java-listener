package com.study.listener.threadpool;

import java.io.IOException;

/**
 * ClassName: CheckRunnable
 * Description: 通过实现Runnable创建线程
 * @Author: luohx
 * Date: 2022/1/19 下午1:57
 * History:
 * <author>          <time>          <version>          <desc>
 * luohx            修改时间           1.0           通过实现Runnable创建线程
 */
public class CheckRunnable implements Runnable {

    private Runnable runnable;

    private CheckThreadLocalInfo  threadLocalInfo;

    public CheckRunnable(Runnable runnable) {
        this.runnable = runnable;
        this.threadLocalInfo = new CheckThreadLocalInfo();
    }

    @Override
    public void run() {
        try {
            threadLocalInfo.setThreadLocalInfo();
            runnable.run();
        } finally {
            try {
                threadLocalInfo.close();
            } catch (IOException e) {
                //
            }
        }
    }
}
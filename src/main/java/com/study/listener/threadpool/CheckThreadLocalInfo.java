package com.study.listener.threadpool;

import com.study.listener.utils.CurrentContextUtil;

import java.io.Closeable;
import java.io.IOException;

/**
 * ClassName: CheckThreadLocalInfo
 * Description: 创建ThreadLocal相关信息
 * @Author: luohx
 * Date: 2022/1/19 下午2:03
 * History:
 * <author>          <time>          <version>          <desc>
 * luohx            修改时间           1.0           创建ThreadLocal相关信息
 */
public class CheckThreadLocalInfo implements Closeable {
    private CurrentContextUtil.ContextUser userInfo;

    public CheckThreadLocalInfo() {
        userInfo = CurrentContextUtil.getContextUser();
    }

    public CurrentContextUtil.ContextUser getUserInfo() {
        return userInfo;
    }



    public void setThreadLocalInfo() {
        CurrentContextUtil.setContextUser(getUserInfo());
    }

    @Override
    public void close() throws IOException {
        userInfo = null;
        CurrentContextUtil.clearAllThreadLocal();
    }
}

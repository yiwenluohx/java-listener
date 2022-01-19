package com.study.listener.utils;

import com.google.common.collect.Maps;
import com.study.listener.threadpool.CheckScheduledThreadPool;
import com.study.listener.threadpool.CheckThreadPool;
import com.study.listener.threadpool.ThreadPoolEnum;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

/**
 * ClassName: CurrentContextUtil
 * Description: 线程池工具类
 *
 * @Author: luohx
 * Date: 2022/1/18 下午5:22
 * History:
 * <author>          <time>          <version>          <desc>
 * luohx            修改时间           1.0           线程池工具类
 */
public class CurrentContextUtil {

    //依靠线程池传递了 CurrentContext
    private static ThreadLocal<ContextUser> USER_THREAD_LOCAL = new ThreadLocal<>();

    public static void clear() {
        USER_THREAD_LOCAL.remove();
    }

    public static ContextUser getContextUser() {
        return USER_THREAD_LOCAL.get();
    }

    public static Long getEid() {
        return Optional.ofNullable(getContextUser()).map(it -> it.getEid()).orElse(null);
    }

    public static Long getUserId() {
        return Optional.ofNullable(getContextUser()).map(it -> it.getUserId()).orElse(null);
    }

    public static void setContextUser(ContextUser contextUser) {
        USER_THREAD_LOCAL.set(contextUser);
    }

    public static void setContextUser(Long eid, Long userId) {
        ContextUser contextUser = new ContextUser();
        contextUser.setEid(eid);
        contextUser.setUserId(userId);
        USER_THREAD_LOCAL.set(contextUser);
    }

    public static void setContextUser(Long eid, Long userId, String userName) {
        ContextUser contextUser = new ContextUser();
        contextUser.setEid(eid);
        contextUser.setUserId(userId);
        contextUser.setUserName(userName);
        USER_THREAD_LOCAL.set(contextUser);
    }

    public static HashMap<String, ExecutorService> poolMap = Maps.newHashMap();

    public static ExecutorService getThreadPool(ThreadPoolEnum pool) {
        if (poolMap.get(pool.getPoolName()) == null) {
            synchronized (poolMap) {
                if (poolMap.get(pool.getPoolName()) == null) {
                    if (pool.getPoolType() == CheckThreadPool.THREAD_POOL) {
                        CheckThreadPool.putPool(pool);
                    }else if(pool.getPoolType() == CheckThreadPool.SCHEDULED_THREAD_POOL){
                        CheckScheduledThreadPool.putPool(pool);
                    }
                }
            }
        }
        return poolMap.get(pool.getPoolName());
    }

    public static void clearAllThreadLocal() {
        clear();
    }

    public static class ContextUser {

        /**
         * 公司ID
         */
        private Long eid;
        /**
         * 用户ID
         */
        private Long userId;
        /**
         * 用户姓名 富勒使用
         */
        private String userName;

        public Long getEid() {
            return eid;
        }

        public void setEid(Long eid) {
            this.eid = eid;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}

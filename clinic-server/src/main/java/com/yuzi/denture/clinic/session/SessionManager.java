package com.yuzi.denture.clinic.session;


import com.yuzi.denture.clinic.domain.ClinicUser;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by liyou on 2018/10/24.
 */
public class SessionManager {

    private final ConcurrentHashMap<String, CacheObject> Session;
    private final long expiredTime;
    private final ScheduledExecutorService validationService;

    private static class SingletonHolder {
        private static SessionManager INSTANCE = new SessionManager();
    }

    public static SessionManager Instance(){
        return SingletonHolder.INSTANCE;
    }

    public SessionManager() {
        this(30 * 60 * 1000L);
    }

    public SessionManager(Long expiredTime) {
        this.expiredTime = expiredTime;
        this.Session = new ConcurrentHashMap<>();
        /**
         * 在本地完成expired清理动作
         */
        this.validationService = Executors.newSingleThreadScheduledExecutor();
        this.validationService.scheduleAtFixedRate(cleanJob, this.expiredTime,
                this.expiredTime, TimeUnit.MILLISECONDS);
    }


    public void cacheUser(String token, ClinicUser user) {
        Session.put(token, new CacheObject(user));
    }

    public ClinicUser user(HttpServletRequest request) {
        String token = request.getHeader(Cst.TokenKey);
        CacheObject object = Session.get(token);
        return  object.value==null? null : (ClinicUser) object.value;
    }

    private Runnable cleanJob = new Runnable() {
        @Override
        public void run() {
            /**
             * 清理过期验证码
             */
            Iterator<String> iter = Session.keySet().iterator();
            while (iter.hasNext()){
                String key = iter.next();
                // 由于并发缘故，{@link WechatDataCache#getCode(String)}
                // 在get/exists中的操作，可能已经把该{@param key}对应的Code, 清理掉了
                CacheObject code = Session.get(key);
                if(code != null && code.isValidation(expiredTime)){
                    iter.remove();
                }
            }
        }
    };

    private static class CacheObject {
        final Date createdTime;
        final Object value;

        public CacheObject(Object value) {
            this(new Date(), value);
        }

        public CacheObject(Date createdTime, Object value) {
            this.createdTime = createdTime;
            this.value = value;
        }

        public boolean isValidation(long expiredTime) {
            long duration = System.currentTimeMillis() - createdTime.getTime();
            return duration < expiredTime;
        }
    }
}

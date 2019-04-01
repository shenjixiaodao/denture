package com.yuzi.denture.biz.util;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
    private static AtomicInteger sequence = new AtomicInteger(1000);

    private static class Timestamp {

        private static ThreadLocal<DateFormat> DATA_FORMAT = new ThreadLocal<DateFormat>() {
            @Override
            public DateFormat get() {
                return super.get();
            }

            @Override
            protected DateFormat initialValue() {
                DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                df.setTimeZone(TimeZone.getTimeZone("GMT+08"));
                df.setLenient(false);
                return df;
            }

            @Override
            public void remove() {
                super.remove();
            }

            @Override
            public void set(DateFormat value) {
                super.set(value);
            }

        };

        private static synchronized String currentTime() {
            return DATA_FORMAT.get().format(System.currentTimeMillis());
        }
    }

    /**
     * @param id {@link com.yuzi.denture.domain.Factory#id} or {@link com.yuzi.denture.domain.Clinic#id}
     */
    public static String generate(Long id) {
        String institutionId = id + "";
        if(institutionId.length() < 6) {
            institutionId = String.format("%06d", id);
        } else if(institutionId.length() > 6) {
            institutionId = institutionId.substring(0,6);
        }
        StringBuilder appender = new StringBuilder(30);
        String timestamp = Timestamp.currentTime();
        int sequence = nextSeq();
        appender.append(institutionId).append(timestamp).append(sequence);
        return appender.toString();
    }

    private static int nextSeq() {
        for (;;) {
            int current = sequence.get();
            int next = (current > 90000) ? 10000 : current + 1;
            if (sequence.compareAndSet(current, next))
                return next;
        }
    }
}

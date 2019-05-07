package com.yuzi.denture.domain.util;

import com.yuzi.denture.domain.exception.CodeException;
import com.yuzi.denture.domain.response.ResponseCode;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

    public static Date parseDate(String joinDate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return df.parse(joinDate);
        } catch (ParseException e) {
            throw new CodeException(ResponseCode.DateFormat_Error);
        }
    }

    public static Date parseMonthDate(String joinDate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        try {
            return df.parse(joinDate);
        } catch (ParseException e) {
            throw new CodeException(ResponseCode.DateFormat_Error);
        }
    }

    public static String dateToLongFormat(Date date) {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(date);
    }

    public static String getFullId(Long factorId, String shortId) {
        if(StringUtils.isEmpty(shortId) || factorId == null)
            throw new RuntimeException("请求数据错误");
        return String.format("%06d", factorId) + shortId;
    }
}

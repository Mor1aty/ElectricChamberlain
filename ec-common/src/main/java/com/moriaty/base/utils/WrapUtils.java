package com.moriaty.base.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.moriaty.base.wrap.WrapParams;
import com.moriaty.login.storage.Token;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import java.util.Enumeration;


/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2019/11/15 11:15
 * @Description TODO
 * 封装处理工具类
 */
public class WrapUtils {

    private WrapUtils() {

    }

    /**
     * request 参数写入 WrapParams
     *
     * @param request
     * @return WrapParams
     * @throws IOException
     */
    public static WrapParams paramToWrapParams(HttpServletRequest request) throws IOException {
        JSONObject jsonObject = new JSONObject();
        ServletInputStream is = request.getInputStream();
        String paramString = ValueUtils.convertStreamToString(is);

        if (ValueUtils.valNotEmpty(paramString)) {
            jsonObject = JSON.parseObject(paramString);
        } else {
//            Collection<Part> parts = request.getParts();
//            if (ValueUtils.valNotEmpty(parts)) {
//                Iterator<Part> iterator = parts.iterator();
//                while (iterator.hasNext()) {
//                    Part part = iterator.next();
//                    jsonObject.put(part.getName(), part);
//                }
//            }
            Enumeration<String> enu = request.getParameterNames();
            while (enu.hasMoreElements()) {
                String tempEnu = enu.nextElement();
                jsonObject.put(tempEnu, request.getParameter(tempEnu));
            }
        }
        Enumeration<String> attrEnu = request.getAttributeNames();
        while (attrEnu.hasMoreElements()) {
            String tempEnu = attrEnu.nextElement();
            jsonObject.put(tempEnu, request.getAttribute(tempEnu));
        }

        return new WrapParams(jsonObject);
    }

}

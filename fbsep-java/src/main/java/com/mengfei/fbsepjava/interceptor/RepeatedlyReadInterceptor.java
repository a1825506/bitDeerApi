package com.mengfei.fbsepjava.interceptor;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mengfei.fbsepjava.config.RepeatedlyReadRequestWrapper;
import com.mengfei.fbsepjava.exception.TokenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/09/21
 */
public class RepeatedlyReadInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(RepeatedlyReadInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 对来自后台的请求统一进行日志处理
         */
        RepeatedlyReadRequestWrapper requestWrapper;
        if (request instanceof RepeatedlyReadRequestWrapper) {
            // 签名处理过程 start....

//            String timestamp = request.getHeader("timestamp");
//            long timestampDate = Long.valueOf(timestamp) + 1000*60*100;
//            long currDate = System.currentTimeMillis();
//            // 请求过期
//            if (timestampDate < currDate) {
//                response.setStatus(403);
//                return false;
//            }
//
//            String appkey = request.getHeader("appkey");
//
//            if (appkey == null) {
////                response.setStatus(403);
//                String message = String.format("appkey Error", "appkey");
//                throw new TokenException(message);
//
//            }
//
//
//            requestWrapper = (RepeatedlyReadRequestWrapper) request;
//            JSONObject jsonObject = JSON.parseObject(getBodyString(requestWrapper));
//
//            String signature = jsonObject.getString("sign");
//
//            Map map = new HashMap();
//
//            for(Map.Entry<String, Object> entry : jsonObject.entrySet()) {
//                if(!"sign".equals(entry.getKey())){
//                    map.put(entry.getKey(), entry.getValue());
//                }
//
//            }
//
//
//            Set setKey = map.keySet();
//            Object[] keys = setKey.toArray();
//            // 将请求参数升序排序
//            Arrays.sort(keys);
//
//            StringBuilder strBuilder = new StringBuilder();
//            for (Object str : keys) {
////                strBuilder.append(str.toString());
//                strBuilder.append(map.get(str.toString()));
//            }
//
//            strBuilder.append(appkey);
//            strBuilder.append(timestamp);
//
//
//
//            String newSignature = DigestUtils.md5DigestAsHex(strBuilder.toString().getBytes()).toUpperCase();
//
//            if (!signature.toUpperCase().equals(newSignature)) {
//                response.setStatus(403);
//                return false;
//            }
//
//            logger.info("MD5校验 {}", newSignature);
//
//            logger.info("请求Body: {} ", getBodyString(requestWrapper));
            // 签名处理过程 end....
        }
        // 默认记录后台接口请求日志记录
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        logger.info(String.format("请求参数, url: %s, method: %s, uri: %s, params: %s ", url, method, uri, queryString));
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        RepeatedlyReadRequestWrapper requestWrapper;
        if (request instanceof RepeatedlyReadRequestWrapper) {
            // 测试再次获取Body start....
            requestWrapper = (RepeatedlyReadRequestWrapper) request;
            logger.info("请求Body: {} ", getBodyString(requestWrapper));
            // 测试再次获取Body end....
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 获取请求Body
     *
     * @param request
     *
     * @return
     */
    public static String getBodyString(final ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = cloneInputStream(request.getInputStream());
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /**
     * Description: 复制输入流</br>
     *
     * @param inputStream
     *
     * @return</br>
     */
    public static InputStream cloneInputStream(ServletInputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buffer)) > -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            byteArrayOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return byteArrayInputStream;
    }
}

package com.ex.api.interceptor;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * filter and add header,generate sign for validate
 *
 * @create 2023/9/21 10:43
 */
public abstract class AbstractOkHttpInterceptor implements Interceptor {

    protected static final String appKey = "enter your appkey";
    protected static final String secretKey = "enter your secretKey";
    protected static final String encry = "HmacSHA256";
    protected static final String contentType = "application/json";
    protected static final String window = "6000";

    protected static final String HEADER_ALG = "validate-algorithms";
    protected static final String HEADER_APPKEY = "validate-appkey";
    protected static final String HEADER_WIND = "validate-recvwindow";
    protected static final String HEADER_TIME = "validate-timestamp";
    protected static final String HEADER_SIGN = "validate-signature";

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request oldRequest = chain.request();
        Request.Builder requestBuilder = oldRequest.newBuilder();

        requestBuilder.method(oldRequest.method(), oldRequest.body());

        Headers headers = oldRequest.headers();
        if (headers != null) {
            Set<String> names = headers.names();
            for (String name : names) {
                String value = headers.get(name);

                requestBuilder.header(name, value);
            }
        }
        Long time = 1725382088656L;
        String path = chain.request().url().uri().getPath();
        String jsonBody = getJsonBody(oldRequest);
        String query = getQuery(oldRequest);
        String signature = generateSign(String.valueOf(time), window, oldRequest.method().toUpperCase(), path, query, jsonBody);

        System.out.println(signature);

        configHeader(requestBuilder, String.valueOf(time), signature);

        Request newRequest = requestBuilder.build();
        return chain.proceed(newRequest);
    }

    private String getJsonBody(Request request) throws IOException {
        if (request.body() != null && request.body().contentLength() > 0) {
            Buffer buffer = new Buffer();
            request.body().writeTo(buffer);
            return buffer.readUtf8();
        }
        return null;
    }

    private String getQuery(Request request) {
        Set<String> set = request.url().queryParameterNames();
        TreeMap<String, String> map = new TreeMap<>();
        for (String key : set) {
            map.put(key, request.url().queryParameter(key));
        }
        return map.entrySet().stream()
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining("&"));
    }

    private void configHeader(Request.Builder requestBuilder, String time, String sign) {
        requestBuilder
                .header("Content-Type", contentType)
                .header(HEADER_ALG, encry)
                .header(HEADER_APPKEY, appKey)
                .header(HEADER_WIND, window)
                .header(HEADER_TIME, time)
                .header(HEADER_SIGN, sign);
    }


    protected abstract String generateSign(String timestamp, String window, String method, String uri, String query, String jsonBody);
}

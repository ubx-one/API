package com.ex.api.client.future;

import com.ex.api.client.HttpProxyProperties;
import com.ex.api.client.ExOkHttpClientBuilder;
import com.ex.api.interceptor.FutureOkHttpInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


/**
 * U-BASE future
 * @create 2023/9/20 12:18
 */
public class FutureUApiClientImpl extends AbstractFutureApiClient {

    private final static String API_URL = "https://api.ubitex.com/";

    private final FutureApiService service;

    public FutureUApiClientImpl(HttpProxyProperties proxyProperties){
        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(API_URL)
                        .client(ExOkHttpClientBuilder.build(proxyProperties,new FutureOkHttpInterceptor()))
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();
        service = retrofit.create(FutureApiService.class);
    }

    @Override
    FutureApiService getService() {
        return service;
    }


}

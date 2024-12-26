package com.ex.api.client.spot;

import com.ex.api.client.HttpProxyProperties;
import com.ex.api.client.ExOkHttpClientBuilder;
import com.ex.api.dto.CommonResponse;

import com.ex.api.dto.spot.SpotPostOrderRequest;
import com.ex.api.interceptor.SpotOkHttpInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


/**
 * @create 2023/9/20 12:18
 */
public class SpotApiClientImpl implements SpotApiClient {

    private final static String API_URL = "https://api.ubitex.com";

    private final SpotApiService service;

    public SpotApiClientImpl(HttpProxyProperties proxyProperties){
        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(API_URL)
                        .client(ExOkHttpClientBuilder.build(proxyProperties,new SpotOkHttpInterceptor()))
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();
        service = retrofit.create(SpotApiService.class);
    }

    @Override
    public CommonResponse getPublicTime() {
        return executeSync(service.getPublicTime());
    }

    @Override
    public CommonResponse postOrder(SpotPostOrderRequest request) {
        return executeSync(service.postOrder(request));
    }

    @Override
    public CommonResponse getOrder(Long id) {
        return executeSync(service.getOrder(id));
    }

    @Override
    public CommonResponse queryOrder(Long orderId) {
        return executeSync(service.queryOrder(orderId));
    }

    @Override
    public CommonResponse delOrder(Long id) {
        return executeSync(service.delOrder(id));
    }


    public CommonResponse executeSync(Call<CommonResponse> call) {
        try {
            Response<CommonResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            }else {
                System.err.println(String.format("failed to call interface:%s,%s",response.code(),response.toString()));
                return CommonResponse.failure(response.toString());
            }
        }catch (Exception e){
            System.err.println("call interface exception:"+e);
            throw new RuntimeException(e);
        }
    }
}

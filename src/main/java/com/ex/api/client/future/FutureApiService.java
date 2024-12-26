package com.ex.api.client.future;

import com.ex.api.dto.CommonResponse;
import com.ex.api.dto.future.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

/**
 * @create 2023/9/20 14:28
 */
public interface FutureApiService {

    @POST("/v1/future-u/trade/v2/order/create")
    Call<CommonResponse> postOrder(@Body FuturePostOrderRequest request);

    @POST("/v1/future-u/trade/v2/order/create-batch")
    Call<CommonResponse> batchOrder(@Body List<FuturePostOrderRequest> list);

    @GET("/v1/future-u/trade/order/list-history")
    Call<CommonResponse> orderListHistory(@QueryMap Map<String, String> params);

    @GET("/v1/future-u/trade/order/trade-list")
    Call<CommonResponse> orderTradeList(@QueryMap Map<String, String> params);

    @GET("/v1/future-u/trade/order/detail")
    Call<CommonResponse> orderDetail(@Query("orderId") Long orderId);

    @GET("/v1/future-u/trade/order/list")
    Call<CommonResponse> orderList(@QueryMap Map<String, String> params);

    @POST("/v1/future-u/trade/v2/order/cancel")
    Call<CommonResponse> orderCancel(@Body FutureOrderCancelRequest request);

    @POST("/v1/future-u/trade/v2/order/cancel-batch")
    Call<CommonResponse> cancelOrderBatch(@Body CancelOrderBatchRequest request);

    @POST("/v1/future-u/trade/v2/order/cancel-all")
    Call<CommonResponse> allCancel(@Body FutureOrderCancelAllRequest request);


    @POST("/v1/future-u/trade/v2/entrust/create-plan")
    Call<CommonResponse> entrustCreatePlan(@Body CreatePlanRequest request);

    @DELETE("/v1/future-u/trade/v2/entrust/cancel-plan")
    Call<CommonResponse> entrustCancelPlan(@Query("entrustId") Long entrustId);

    @POST("/v1/future-u/trade/v2/entrust/cancel-all-plan")
    Call<CommonResponse> entrustCancelAllPlan(@Query("symbol") String symbol);

    @GET("/v1/future-u/user/account/info")
    Call<CommonResponse> accountInfo();

    @GET("/v1/future-u/user/balance/detail")
    Call<CommonResponse> balanceDetail(@Query("coin") String coin);

    @GET("/v1/future-u/trade/position/list")
    Call<CommonResponse> getPosition(@QueryMap Map<String, String> params);
}

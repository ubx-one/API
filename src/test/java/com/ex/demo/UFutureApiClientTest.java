package com.ex.demo;

import com.ex.api.dto.future.CreatePlanRequest;
import com.google.gson.Gson;
import com.ex.api.client.future.FutureApiClient;
import com.ex.api.client.future.FutureUApiClientImpl;
import com.ex.api.dto.CommonResponse;
import com.ex.api.dto.future.FuturePostOrderRequest;
import lombok.Data;
import org.junit.Test;

import java.util.*;

/**
 * USDT BASE Future
 * @create 2023/9/20 16:15
 */
public class UFutureApiClientTest {
    Gson gson = new Gson();
    FutureApiClient client = new FutureUApiClientImpl(null);

    @Test
    public void postOrder(){
        FuturePostOrderRequest request = FuturePostOrderRequest.builder().symbol("btc_usdt")
                .origQty("100")
                .orderType("LIMIT")
                .timeInForce("GTC")
                .price("56911.9")
                .clientOrderId(System.currentTimeMillis()+"1234567123456789012345678901234567890")
                .orderSide("BUY")
                .positionSide("LONG")
                .build();
        CommonResponse commonResponse = client.postOrder(request);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void batchOrder(){
        FuturePostOrderRequest request = FuturePostOrderRequest.builder().symbol("btc_usdt")
                .origQty("10")
                .orderType("LIMIT")
                .timeInForce("GTC")
                .price("60572")
                .orderSide("SELL")
                .clientOrderId(System.currentTimeMillis()+"")
                .positionSide("SHORT")
                .build();
        FuturePostOrderRequest request2 = FuturePostOrderRequest.builder().symbol("btc_usdt")
                .origQty("11")
                .orderType("LIMIT")
                .timeInForce("GTC")
                .price("60172")
                .orderSide("BUY")
                .clientOrderId(System.currentTimeMillis()+"")
                .positionSide("LONG")
                .build();
        List<FuturePostOrderRequest> requestList = new ArrayList<>();
        requestList.add(request);
        requestList.add(request2);
        CommonResponse commonResponse = client.batchOrder(requestList);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void orderListHistory() {
        Map<String, String> params = new HashMap<>();
        CommonResponse commonResponse = client.orderListHistory(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void orderTradeList() {
        Map<String, String> params = new HashMap<>();
        CommonResponse commonResponse = client.orderTradeList(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void orderDetail() {
        CommonResponse commonResponse = client.orderDetail(403744814222537792L);
        System.out.println("result:"+commonResponse);
    }
    @Test
    public void orderList() {
        Map<String, String> params = new HashMap<>();
        CommonResponse commonResponse = client.orderList(params);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void orderCancel() {
        CommonResponse commonResponse = client.orderCancel(403744814222537792L);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void cancelOrderBatch() {
        List<String> orderIdList = new ArrayList<>();
        orderIdList.add("1719234880469");
        orderIdList.add("1719234818483");
        CommonResponse commonResponse = client.cancelOrderBatch(Collections.emptyList(), orderIdList);
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void allCancel() {
        CommonResponse commonResponse = client.allCancel(null);
        System.out.println("result:"+commonResponse);
    }


    @Test
    public void entrustCreatePlan() {
        CreatePlanRequest request = new CreatePlanRequest();
        request.setSymbol("btc_usdt");
        request.setPrice("50000");
        request.setStopPrice("51000");
        request.setOrderSide("BUY");
        request.setEntrustType("TAKE_PROFIT");
        request.setOrigQty(2000);
        request.setPositionSide("LONG");
        request.setTimeInForce("GTC");
        request.setTriggerPriceType("INDEX_PRICE");

        CommonResponse commonResponse = client.entrustCreatePlan(request);
        System.out.println("result:"+commonResponse);
    }
    @Test
    public void entrustCancelPlan() {
        CommonResponse commonResponse = client.entrustCancelPlan(403745109316990016L);
        System.out.println("result:"+commonResponse);
    }
    @Test
    public void entrustCancelAllPlan() {
        CommonResponse commonResponse = client.entrustCancelAllPlan("btc_usdt");
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void accountInfo() {
        CommonResponse commonResponse = client.accountInfo();
        System.out.println("result:"+commonResponse);
    }

    @Test
    public void balanceDetail() {
        CommonResponse commonResponse = client.balanceDetail("usdt");
        System.out.println("result:"+commonResponse);
    }

}

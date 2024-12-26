package com.ex.demo;

import com.ex.api.client.future.FutureApiClient;
import com.ex.api.client.future.FutureUApiClientImpl;
import com.ex.api.dto.CommonResponse;
import com.ex.api.dto.future.FuturePostOrderRequest;
import com.ex.api.dto.future.MergePositionDTO;
import com.google.gson.Gson;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

/**
 * USDT BASE Future
 *
 * @create 2023/9/20 16:15
 */
public class PositionApiClientTest {
    FutureApiClient client = new FutureUApiClientImpl(null);


    @Test
    public void getPosition() {
        Map<String, String> params = new HashMap<>();
        params.put("symbol", "btc_usdt");
        CommonResponse commonResponse = client.getPosition(params);
        System.out.println("result:" + commonResponse);
    }

}

package com.ex.demo;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.ex.api.util.HttpUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * spot business
 * @create 2022/10/28 15:04
 */
public class RestTest {

    @Test
    public void testPostOrder() {
        String uri = "/v1/spot/order";
        Map<String, Object> param = new HashMap<>();
        param.put("symbol", "btc_usdt");
        param.put("clientOrderId", IdUtil.fastUUID());
        param.put("side", "BUY");
        param.put("type", "LIMIT");
        param.put("timeInForce", "GTC");
        param.put("bizType", "SPOT");
        param.put("price", "11430");
        param.put("quantity", "2");
        System.out.println("json===="+ JSON.toJSONString(param));
        System.out.println("result====" + HttpUtil.post(uri,JSON.toJSONString(param)));
    }

    @Test
    public void getOrder() {
        String uri = "/v1/spot/order/401878710881651008";
        System.out.println("result====" + HttpUtil.get(uri, null));
    }

    @Test
    public void queryOrder() {
        String uri = "/v1/spot/order";
        Map<String, Object> param = new HashMap<>();
        param.put("orderId", 401878710881651008L);
        System.out.println("result====" + HttpUtil.get(uri, param));
    }

    @Test
    public void delOrder() {
        String uri = "/v1/spot/order/403747058624101696";
        System.out.println("result====" + HttpUtil.delete(uri, null));
    }

    @Test
    public void batchOrderGet() {
        String uri = "/v1/spot/batch-order";
        Map<String, Object> param = new HashMap<>();
        param.put("orderIds", "401876438755871040,401653869310150976");
        System.out.println("result====" + HttpUtil.get(uri, param));
    }

    @Test
    public void batchOrderDel() {
        String uri = "/v1/spot/batch-order";
        Map<String, Object> param = new HashMap<>();
        param.put("clientBatchId", "f8cdd5d7-eb77-4377-b5f3-14a2e10203ea");
        param.put("orderIds", List.of(403747400422128960L,403747482995391808L));
        System.out.println("result====" + HttpUtil.deleteWithBody(uri, JSON.toJSONString(param)));
    }

    @Test
    public void getOpenOrder() {
        String uri = "/v1/spot/open-order";
        Map<String, Object> param = new HashMap<>();
        param.put("symbol","btc_usdt");
        param.put("bizType","SPOT");
        System.out.println("result====" + HttpUtil.get(uri, param));
    }

    @Test
    public void delOpenOrder() {
        String uri = "/v1/spot/open-order";
        Map<String, Object> param = new HashMap<>();
        param.put("bizType", "SPOT");
        System.out.println("result====" + HttpUtil.deleteWithBody(uri, JSON.toJSONString(param)));
    }

    @Test
    public void getHistoryOrder() {
        String uri = "/v1/spot/history-order";
        Map<String, Object> param = new HashMap<>();
        param.put("bizType", "SPOT");
        System.out.println("result====" + HttpUtil.get(uri, param));
    }


    @Test
    public void getTrade() {
        String uri = "/v1/spot/trade";
        Map<String, Object> param = new HashMap<>();
        param.put("bizType", "SPOT");
        System.out.println("result====" + HttpUtil.get(uri, param));
    }


    @Test
    public void getBalance() {
        String uri = "/v1/spot/balance";
        Map<String, Object> param = new HashMap<>();
        param.put("currency", "usdt");
        System.out.println("result====" + HttpUtil.get(uri, param));
    }

    @Test
    public void getBalances() {
        String uri = "/v1/spot/balances";
        Map<String, Object> param = new HashMap<>();
        param.put("currencies", "usdt,btc");
        System.out.println("result====" + HttpUtil.get(uri, param));
    }
    
    @Test
    public void getToken() {
        String uri = "/v4/ws-token";
        String token = HttpUtil.post(uri, null);
        System.out.println("result====" + token);
    }

    @Test
    public void deposit() {
        String uri = "/v1/spot/deposit/address";
        Map<String, Object> param = new HashMap<>();
        param.put("chain", "Tron");
        param.put("currency", "usdt");
        System.out.println("result====" + HttpUtil.get(uri, param));
    }

    @Test
    public void withdraw() {
        String uri = "/v1/spot/withdraw";
        Map<String, Object> param = new HashMap<>();
        param.put("chain", "Tron");
        param.put("currency", "usdt");
        param.put("amount", 10);
        param.put("address", "TKr47rQg831zd1UAY3u5K71fXuXMEowFXW");

        System.out.println("json===="+ JSON.toJSONString(param));
        System.out.println("result====" + HttpUtil.post(uri,JSON.toJSONString(param)));
    }

    @Test
    public void transfer() {
        String uri = "/v1/spot/balance/transfer";
        Map<String, Object> param = new HashMap<>();
        param.put("bizId", IdUtil.getSnowflakeNextIdStr());
        param.put("from", "SPOT");
        param.put("to", "FUTURES_U");
        param.put("currency", "usdt");
        param.put("amount", "10");
        param.put("remark", "划转");
        System.out.println("json===="+ JSON.toJSONString(param));
        System.out.println("result====" + HttpUtil.post(uri,JSON.toJSONString(param)));
    }
}

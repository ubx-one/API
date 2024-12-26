package com.ex.api.dto.spot;

import lombok.Builder;
import lombok.Data;

/**
 * @create 2023/9/20 16:09
 */
@Data
@Builder
public class SpotPostOrderRequest {

    private String symbol;
    private String side;
    private String type;
    private String timeInForce;

    private String bizType;
    private String price;

    private String quantity;
}

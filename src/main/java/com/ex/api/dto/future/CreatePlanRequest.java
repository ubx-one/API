package com.ex.api.dto.future;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreatePlanRequest {

    private String symbol;
    private String price;
    private String stopPrice;
    private String orderSide;
    private String entrustType;
    private int origQty;
    private String positionSide;
    private String timeInForce;
    private String triggerPriceType;
}

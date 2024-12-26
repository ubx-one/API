package com.ex.api.dto.future;

import lombok.Builder;
import lombok.Data;


/**
 * @create 2023/9/21 18:03
 */
@Data
@Builder
public class FutureBatchOrderRequest {

    private String list;
}

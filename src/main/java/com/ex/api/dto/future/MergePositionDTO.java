package com.ex.api.dto.future;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class MergePositionDTO {

    //交易对
    private String symbol;

    //合并数量
    private BigDecimal mergeAmount;

}

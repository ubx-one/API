package com.ex.api.dto.future;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CancelOrderBatchRequest {

    List<Long> orderIds;

    List<String> clientOrderIds;
}

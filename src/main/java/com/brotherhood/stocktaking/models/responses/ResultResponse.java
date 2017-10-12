package com.brotherhood.stocktaking.models.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ResultResponse {
    private boolean success;
}

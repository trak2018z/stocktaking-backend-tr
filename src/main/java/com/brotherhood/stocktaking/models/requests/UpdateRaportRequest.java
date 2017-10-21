package com.brotherhood.stocktaking.models.requests;

import com.brotherhood.stocktaking.models.entities.RaportStatus;
import lombok.Getter;

@Getter
public class UpdateRaportRequest {
    private Integer raportId;
    private RaportStatus raportStatus;
    private String url;
}

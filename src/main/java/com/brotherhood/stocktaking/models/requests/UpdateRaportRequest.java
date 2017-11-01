package com.brotherhood.stocktaking.models.requests;

import com.brotherhood.stocktaking.models.entities.RaportStatus;
import lombok.Getter;

@Getter
public class UpdateRaportRequest {
    private Integer raportId;
    private RaportStatus raportStatus;
    private String url;

    public UpdateRaportRequest(Integer raportId, RaportStatus raportStatus, String url) {
        this.raportId = raportId;
        this.raportStatus = raportStatus;
        this.url = url;
    }
}

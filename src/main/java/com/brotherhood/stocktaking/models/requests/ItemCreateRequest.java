package com.brotherhood.stocktaking.models.requests;

import com.brotherhood.stocktaking.models.entities.CodeType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Date;

@Getter
@Setter
@Accessors(chain = true)
public class ItemCreateRequest {
    private String name;
    private String description;
    private Date date;
    private CodeType codeType;
    private Integer localizationId;
    private Integer itemTypeId;
    private int count;
    private Float value;
}

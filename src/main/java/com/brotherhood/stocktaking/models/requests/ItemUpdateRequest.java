package com.brotherhood.stocktaking.models.requests;

import com.brotherhood.stocktaking.models.entities.CodeType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Date;

@Getter
@Setter
@Accessors(chain = true)
public class ItemUpdateRequest {
    private Integer itemId;
    private String name;
    private String description;
    private Integer count;
    private Float value;
    private Date date;
    private Integer userId;
    private String localizationName;
    private Integer itemTypeId;
    private CodeType codeType;
}

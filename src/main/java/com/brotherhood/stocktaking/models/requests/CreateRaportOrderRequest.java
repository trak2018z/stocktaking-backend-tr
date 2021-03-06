package com.brotherhood.stocktaking.models.requests;

import com.brotherhood.stocktaking.models.entities.CodeType;
import lombok.Getter;

import java.sql.Date;
import java.util.List;

@Getter
public class CreateRaportOrderRequest {
    private Integer countMin;
    private Integer countMax;
    private Float valueMin;
    private Float valueMax;
    private String name;
    private Date dateMin;
    private Date dateMax;
    private String codeType;
    private List<String> localizationsNames;
    private List<String> usersNames;
}

package com.brotherhood.stocktaking.models.requests;

import com.brotherhood.stocktaking.models.entities.CodeType;
import lombok.Getter;

import java.sql.Date;
import java.util.List;

@Getter
public class CreateRaportOrderRequest {
    private String raportOwnerNick;
    private Integer countMin;
    private Integer countMax;
    private Float valueMin;
    private Float valueMax;
    private Date dateMin;
    private Date dateMax;
    private CodeType codeType;
    private List<Integer> localizationsIds;
    private List<Integer> usersIds;
}

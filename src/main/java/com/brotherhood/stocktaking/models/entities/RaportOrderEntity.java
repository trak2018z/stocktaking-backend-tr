package com.brotherhood.stocktaking.models.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Accessors(chain = true)
@Table(name = "RaportOrder")
public class RaportOrderEntity {
    @Id
    @Column
    private Integer raportOrderId;

    @Column
    private Integer countMin;

    @Column
    private Integer countMax;

    @Column
    private Float valueMin;

    @Column
    private Float valueMax;

    @Column
    private Date dateMin;

    @Column
    private Date dateMax;

    @Column
    @Enumerated(EnumType.STRING)
    private CodeType codeType;

    @Column(name = "usersNames")
    private String usersNamesJson;

    @Column(name = "localizations")
    private String localizationsJson;

    @OneToOne(fetch=FetchType.LAZY, mappedBy = "raportOrderId")
    @JsonBackReference
    private RaportEntity raportEntity;
}

package com.brotherhood.stocktaking.models.entities;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Accessors(chain = true)
@Table(name = "RaportOrderLocalization")
public class RaportOrderLocalizationEntity {

    @Column
    private Integer raportOrderId;

    @Column
    @Id
    private Integer localizationId;
}

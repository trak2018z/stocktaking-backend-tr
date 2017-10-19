package com.brotherhood.stocktaking.models.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Accessors(chain = true)
@Table(name = "RaportOrderUser")
public class RaportOrderUserEntity {
    @Column
    private Integer userId;

    @Column
    @Id
    private Integer raportOrderId;
}

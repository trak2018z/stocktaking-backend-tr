package com.brotherhood.stocktaking.models.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Accessors(chain = true)
@Table(name = "RaportOrder")
public class RaportOrderEntity {
    @Id
    @Column(name = "raportOrderId")
    private Integer id;

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
    private CodeType codeType;

    @ManyToOne
    @JsonManagedReference
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "userId")
    private UserEntity user;

    @OneToOne(fetch=FetchType.LAZY, mappedBy = "raportOrderId")
    @JsonBackReference
    private RaportEntity raportEntity;

}

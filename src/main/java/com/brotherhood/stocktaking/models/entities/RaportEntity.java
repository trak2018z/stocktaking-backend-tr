package com.brotherhood.stocktaking.models.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Accessors(chain = true)
@Table(name = "Raport")
public class RaportEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer raportId;

    @Column
    private String url;

    @Column
    private String raportName;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RaportStatus status;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonManagedReference
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private UserEntity user;

    @Setter(AccessLevel.NONE)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "raportId")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonManagedReference
    private RaportOrderEntity raportOrderId;
}

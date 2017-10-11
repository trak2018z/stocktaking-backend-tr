package com.brotherhood.stocktaking.models.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "Item")
public class ItemEntity {
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Integer count;

    @Column
    private Float value;

    @Column
    private Date date;

    @Column
    @Enumerated(EnumType.STRING)
    private CodeType codeType;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "localizationId")
    private LocalizationEntity localization;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "idType")
    private ItemTypeEntity itemType;
}

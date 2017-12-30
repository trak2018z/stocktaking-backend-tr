package com.brotherhood.stocktaking.models.entities;

import com.fasterxml.jackson.annotation.*;
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
@Table(name = "Localization")
public class LocalizationEntity {

    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column
    private String name;

    @OneToMany(mappedBy = "localization")
    @JsonBackReference
    private List<ItemEntity> itemEntities;
}

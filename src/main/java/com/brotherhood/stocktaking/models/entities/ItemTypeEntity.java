package com.brotherhood.stocktaking.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "ItemType")
public class ItemTypeEntity {
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column
    private String type;

    @OneToMany(mappedBy = "itemType", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ItemEntity> itemEntities;
}

package com.brotherhood.stocktaking.models.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "UserGroup")
public class UserGroupEntity {
    @Column
    @GeneratedValue
    @Id
    private Integer id;

    @Column
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<UserEntity> userEntities;
}

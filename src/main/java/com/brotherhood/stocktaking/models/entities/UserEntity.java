package com.brotherhood.stocktaking.models.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Accessors(chain = true)
@Table(name = "User")
public class UserEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nick;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    @ManyToOne
    @JoinColumn(name = "groupId")
    private UserGroupEntity userGroupEntity;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ItemEntity> items;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<RaportEntity> raports;
}

package org.example.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "goods")
@Getter
@Setter
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 150)
    private String name;

    @ManyToOne
    @JoinColumn(name = "goodsType_id", referencedColumnName = "id", nullable = false)
    private GoodsType goodsType;

    @Column(name = "cost", columnDefinition = "double default 0", nullable = false)
    private Double cost = 0D;

    @Column(columnDefinition = "integer default '0'")
    private Integer count;

    @Column(columnDefinition = "boolean default true")
    private Boolean isLeft;

    private String description;
}

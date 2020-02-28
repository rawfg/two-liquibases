package com.problem.two.liquibases.entity.a;

import javax.persistence.*;

@Entity(name = "entityA")
public class EntityA {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long id;

    @Column(name = "fieldA")
    public String fieldA;
}

package com.problem.two.liquibases.entity.b;

import javax.persistence.*;

@Entity(name = "entityB")
public class EntityB {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long id;

    @Column(name = "fieldB")
    public String fieldB;
}

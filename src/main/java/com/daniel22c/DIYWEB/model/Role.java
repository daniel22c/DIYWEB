package com.daniel22c.DIYWEB.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Myungho on 3/13/2017.
 */
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;
    private String name;
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Role(){}
    public Long getId() {

        return id;
    }

    public String getName() {
        return name;
    }


}

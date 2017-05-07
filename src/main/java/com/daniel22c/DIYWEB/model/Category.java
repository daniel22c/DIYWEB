package com.daniel22c.DIYWEB.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Myungho on 4/24/2017.
 */
@Entity
@Table(name="Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name="category_id")
    private long id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "category")   //one:Category, many=DIYs
    private Set<DIY> diys;

    public Category(){}
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<DIY> getDiys() {
        return diys;
    }

    public void setDiys(Set<DIY> diys) {
        this.diys = diys;
    }
}

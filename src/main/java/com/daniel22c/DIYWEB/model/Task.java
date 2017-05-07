package com.daniel22c.DIYWEB.model;

import javax.persistence.*;

@Entity
@Table(name = "Task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OrderColumn
    private Long id;

    private String description;
    private boolean complete;

    @ManyToOne   //many tasks, one DIY
    @JoinColumn(name="DIY_id")
    private DIY diy;

    public Task() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public DIY getDiy() {
        return diy;
    }

    public void setDiy(DIY diy) {
        this.diy = diy;
    }

}

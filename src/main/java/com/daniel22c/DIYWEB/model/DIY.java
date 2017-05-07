package com.daniel22c.DIYWEB.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Myungho on 4/8/2017.
 */
@Entity
@Table(name="DIY")
public class DIY {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="diy_id")
    private long id;

    @Column(unique = true)
    private String title;

    @OneToMany(mappedBy = "diy")   //one:DIY, many:tasks
    private Set<Task> tasks;

    @ManyToOne   //many=DIYs one category
    @JoinColumn(name="category_id")
    private Category category;

    public DIY(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title.replace(' ' , '-');
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Task> getTasks() {
        return tasks;
    }
    public Task getTaskByTaskID(Long id){
        for(Task task:tasks){
            if(task.getId()==id){
                return task;
            }
        }
        return null;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

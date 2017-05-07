package com.daniel22c.DIYWEB.model;

import org.hibernate.annotations.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Myungho on 3/13/2017.
 */
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id", nullable=false)
    private Long id;

    @Column(unique = true, nullable=false)
    @Size(min = 8 , max=20)
    private String username;

    @Column (length = 130)
    private String password;

    @Column(nullable = false)
    private boolean enabled;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Fetch(FetchMode.JOIN)
    @ManyToMany(fetch =FetchType.LAZY)   //many:User to many:DIY
    @JoinColumn(name="user_id")
    private List<DIY> diys;

    @ElementCollection(fetch = FetchType.EAGER)
    @Cascade({org.hibernate.annotations.CascadeType.DELETE,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.SAVE_UPDATE})
//  @CollectionTable(name ="completedTasks", joinColumns =@JoinColumn(name="username"))
    //@OrderColumn
    private List<Long> completedTasks;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities  = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getName()));

        return authorities;
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public List<DIY> getDiys() {
        return diys.parallelStream().distinct().collect(Collectors.toList());

    }

    public User(){}

    public void setDiys(List<DIY> diys) {
        this.diys = diys;
    }

    public void addDIYs(DIY diy) {
        diys.add(diy);
    }

    public List<Long> getCompletedTasks(){
        //return unique items
        return completedTasks.stream().distinct().collect(Collectors.toList());
    }

    public void setCompletedTasks(List<Long> completedTasks) {
        this.completedTasks = completedTasks;
    }

    public void addCompletedTasks(Long id) {
        if (!completedTasks.contains(id)){
            completedTasks.add(id);
        }
    }
    public void removeCompletedTasks(Long id){

        completedTasks.remove(id);
    }
    public void addOrRemoveTasks(Long id){
        if (!completedTasks.contains(id)){
            completedTasks.add(id);
        }else{
            //completedTasks.remove(id);
            Iterator<Long> i = completedTasks.iterator();
            while (i.hasNext()) {
                Long object = i.next();
                //some condition
                if(object==id) {
                    i.remove();
                }
            }
        }
    }

}

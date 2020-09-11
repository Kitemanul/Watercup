package com.zslin.basic.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.zslin.web.model.Site;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Data
@Entity
@Table(name="a_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private Integer status;
    @Column(name="create_date")
    private Date createDate;
    /** 用户昵称 */
    private String nickname;

    @Column(name="is_admin")
    private Integer isAdmin;

   @OneToMany(mappedBy = "user")
///*    @JsonManagedReference*/
    private List<Site> sites;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list=new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_"+Integer.toString(isAdmin==1?1:2)));
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status==1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

   /* public User() {
    }

    protected boolean canEqual(Object other) {
        return other instanceof User;
    }
*/
}

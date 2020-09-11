package com.zslin.web.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 */

@Data
@Entity
@Table(name = "t_account")
public class Account implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String email;

    private String nickname;

    /** 座右铭 */
    private String motto;

    @Lob
    private String remark;

    /** 状态 */
    private String status;

    /** 头像 */
    private String headimg;

    private String password;

    private String remain;

    /**  充值金额*/
   private String paymoney;

    /**  充值状态：0为未成功，1为成功，null为初始值*/
   private String paystate;

   @ManyToOne
    private Card card;

   @OneToMany(mappedBy = "account")
    private List<Order> orders;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> list=new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_1"));
        return list;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status.equals("1");
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

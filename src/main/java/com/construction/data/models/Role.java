package com.construction.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role
        extends BaseEntity
        implements GrantedAuthority {

    @Column
    private String name;

    @Override
    @Transient
    public String getAuthority() {
        return this.getName();
    }
}

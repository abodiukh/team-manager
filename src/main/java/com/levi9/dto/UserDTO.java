package com.levi9.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;

import com.levi9.annotations.PasswordMatches;
import com.levi9.annotations.ValidEmail;

import org.hibernate.validator.constraints.NotEmpty;


@PasswordMatches
public class UserDTO {

    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    private Set<String> roles;

    private boolean enabled;

    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String confirmedPassword;

    public UserDTO() {
    }

    public UserDTO(final Long id, final String email, final String name, final Set<String> roles, final boolean enabled) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.roles = roles;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(final Set<String> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(final String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}

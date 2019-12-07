package com.entertainment.userservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Component
@Table(name = "users", catalog = "ent_users")
public class UserEntity {
    private String username;
    private String email;
    private Integer phone;
    private Date dob;
    private Gender gender;
    private Date initDate;
    private Boolean isOrganization;

    protected UserEntity() { }

    public UserEntity(String username, String email, Integer phone, Date dob, Gender gender, Date initDate,
                      Boolean isOrganization) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.initDate = initDate;
        this.isOrganization = isOrganization;
    }

    @Id
    @Column(name = "username", length = 50, nullable = false)
    public String getUsername() {
        return username;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "email", length = 50, unique = true)
    public String getEmail() {
        return email;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "phone")
    public Integer getPhone() {
        return phone;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "dob")
    public Date getDob() {
        return dob;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Column(name = "gender")
    @Enumerated(value = EnumType.STRING)
    public Gender getGender() {
        return gender;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "initDate", nullable = false)
    public Date getInitDate() {
        return initDate;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    @NotNull
    @Column(name = "isOrganization", nullable = false)
    public Boolean getOrganization() {
        return isOrganization;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public void setOrganization(Boolean organization) {
        isOrganization = organization;
    }
}

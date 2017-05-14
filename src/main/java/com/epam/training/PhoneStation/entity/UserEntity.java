package com.epam.training.PhoneStation.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = javax.persistence.CascadeType.ALL,orphanRemoval = true)
    private List<ContractEntity> contractEntities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = javax.persistence.CascadeType.ALL)
    private List<CallEntity> callEntities;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "user",
            targetEntity=PaymentEntity.class,
            cascade = javax.persistence.CascadeType.ALL
    )
    @OrderBy("paid,id desc")
    private List<PaymentEntity> paymentEntities;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role.name();
    }

    public List<ContractEntity> getContractEntities() {
        return contractEntities;
    }

    public void setContractEntities(List<ContractEntity> contractEntities) {
        this.contractEntities = contractEntities;
    }

    public List<CallEntity> getCallEntities() {
        return callEntities;
    }

    public void setCallEntities(List<CallEntity> callEntities) {
        this.callEntities = callEntities;
    }

    public List<PaymentEntity> getPaymentEntities() {
        return paymentEntities;
    }

    public void setPaymentEntities(List<PaymentEntity> paymentEntities) {
        this.paymentEntities = paymentEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;

        UserEntity that = (UserEntity) o;

        if (getId() != that.getId()) return false;
        if (!getUsername().equals(that.getUsername())) return false;
        if (!getFullName().equals(that.getFullName())) return false;
        if (!getPassword().equals(that.getPassword())) return false;
        return getRole().equals(that.getRole());
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getUsername().hashCode();
        result = 31 * result + getFullName().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getRole().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

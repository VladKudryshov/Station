package com.epam.training.PhoneStation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "payments")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    private ContractEntity contract;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "call_id")
    private CallEntity call;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "paid", nullable = false)
    @Type(type="yes_no")
    private Boolean paid;

    @Column(name = "cost")
    private int cost;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ContractEntity getContract() {
        return contract;
    }

    public void setContract(ContractEntity contract) {
        this.contract = contract;
    }

    public CallEntity getCall() {
        return call;
    }

    public void setCall(CallEntity call) {
        this.call = call;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentEntity)) return false;

        PaymentEntity that = (PaymentEntity) o;

        if (getId() != that.getId()) return false;
        if (getCost() != that.getCost()) return false;
        if (!getUser().equals(that.getUser())) return false;
        if (!getContract().equals(that.getContract())) return false;
        if (!getCall().equals(that.getCall())) return false;
        if (!getPaymentDate().equals(that.getPaymentDate())) return false;
        return getPaid().equals(that.getPaid());
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getUser().hashCode();
        result = 31 * result + getContract().hashCode();
        result = 31 * result + getCall().hashCode();
        result = 31 * result + getPaymentDate().hashCode();
        result = 31 * result + getPaid().hashCode();
        result = 31 * result + getCost();
        return result;
    }

    @Override
    public String toString() {
        return "PaymentEntity{" +
                "id=" + id +
                ", user=" + user +
                ", contract=" + contract +
                ", call=" + call +
                ", paymentDate=" + paymentDate +
                ", paid=" + paid +
                ", cost=" + cost +
                '}';
    }
}

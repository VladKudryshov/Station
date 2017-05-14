package com.epam.training.PhoneStation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "calls")
public class CallEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "time_call")
    private Time time;

    @Column(name = "date_call")
    private Date date;

    @Column(name = "cost")
    private int cost;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "call")
    private PaymentEntity payment;

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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public PaymentEntity getPayment() {
        return payment;
    }

    public void setPayment(PaymentEntity payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CallEntity)) return false;

        CallEntity that = (CallEntity) o;

        if (getId() != that.getId()) return false;
        if (getCost() != that.getCost()) return false;
        if (!getTime().equals(that.getTime())) return false;
        return getDate().equals(that.getDate());
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getTime().hashCode();
        result = 31 * result + getDate().hashCode();
        result = 31 * result + getCost();
        return result;
    }

    @Override
    public String toString() {
        return "CallEntity{" +
                "id=" + id +
                ", time=" + time +
                ", date=" + date +
                ", cost=" + cost +
                '}';
    }
}

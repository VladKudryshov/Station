package com.epam.training.PhoneStation.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "calls")
public class Call {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "time_call")
    private Time time;


    @Column(name = "cost")
    private int cost;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "call")
    private List<Payment> payments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Call call = (Call) o;

        if (id != call.id) return false;
        if (cost != call.cost) return false;
        if (user != null ? !user.equals(call.user) : call.user != null) return false;
        if (time != null ? !time.equals(call.time) : call.time != null) return false;
        return payments != null ? payments.equals(call.payments) : call.payments == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + cost;
        result = 31 * result + (payments != null ? payments.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Call{" +
                "id=" + id +
                    ", user=" + user.getId() +
                ", time=" + time +
                ", cost=" + cost +
                ", payments=" + payments +
                '}';
    }
}

package com.epam.training.PhoneStation.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private ServiceModel service;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "call_id")
    private Call call;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "paid", nullable = false)
    private boolean paid;

    @Column(name = "cost")
    private int cost;


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

    public ServiceModel getService() {
        return service;
    }

    public void setService(ServiceModel service) {
        this.service = service;
    }

    public Call getCall() {
        return call;
    }

    public void setCall(Call call) {
        this.call = call;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
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
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (id != payment.id) return false;
        if (paid != payment.paid) return false;
        if (cost != payment.cost) return false;
        if (user != null ? !user.equals(payment.user) : payment.user != null) return false;
        if (service != null ? !service.equals(payment.service) : payment.service != null) return false;
        if (call != null ? !call.equals(payment.call) : payment.call != null) return false;
        return paymentDate != null ? paymentDate.equals(payment.paymentDate) : payment.paymentDate == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (service != null ? service.hashCode() : 0);
        result = 31 * result + (call != null ? call.hashCode() : 0);
        result = 31 * result + (paymentDate != null ? paymentDate.hashCode() : 0);
        result = 31 * result + (paid ? 1 : 0);
        result = 31 * result + cost;
        return result;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", user=" + user.getId() +
                ", service=" + (service!=null ? service.getId(): null) +
                ", call=" + (call!=null ? call.getId(): null) +
                ", paymentDate=" + paymentDate +
                ", paid=" + paid +
                ", cost=" + cost +
                '}';
    }
}

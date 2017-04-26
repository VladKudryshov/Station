package com.epam.training.PhoneStation.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "services")
public class ServiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private int cost;

    @Column(name = "period")
    private int period;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "service")
    private List<Contract> contracts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "service")
    private List<Payment> payments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
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

        ServiceModel that = (ServiceModel) o;

        if (id != that.id) return false;
        if (cost != that.cost) return false;
        if (period != that.period) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (contracts != null ? !contracts.equals(that.contracts) : that.contracts != null) return false;
        return payments != null ? payments.equals(that.payments) : that.payments == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + cost;
        result = 31 * result + period;
        result = 31 * result + (contracts != null ? contracts.hashCode() : 0);
        result = 31 * result + (payments != null ? payments.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ServiceModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                ", period=" + period +
                ", contracts=" + contracts +
                ", payments=" + payments +
                '}';
    }
}

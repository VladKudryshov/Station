package com.epam.training.PhoneStation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "services")
public class ServiceEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title_en")
    private String titleEn;

    @Column(name = "title_ru")
    private String titleRu;

    @Column(name = "cost")
    private int cost;

    @Column(name = "period")
    private int period;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "service")
    private List<ContractEntity> contractEntities;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getTitleRu() {
        return titleRu;
    }

    public void setTitleRu(String titleRu) {
        this.titleRu = titleRu;
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

    public List<ContractEntity> getContractEntities() {
        return contractEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServiceEntity)) return false;

        ServiceEntity that = (ServiceEntity) o;

        if (id != that.getId()) return false;
        if (cost != that.getCost()) return false;
        if (period != that.getPeriod()) return false;
        if (!titleEn.equals(that.getTitleEn())) return false;
        return titleRu.equals(that.getTitleRu());
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + titleEn.hashCode();
        result = 31 * result + titleRu.hashCode();
        result = 31 * result + cost;
        result = 31 * result + period;
        return result;
    }

    @Override
    public String toString() {
        return "ServiceEntity{" +
                "id=" + id +
                ", titleEn='" + titleEn + '\'' +
                ", titleRu='" + titleRu + '\'' +
                ", cost=" + cost +
                ", period=" + period +
                '}';
    }
}

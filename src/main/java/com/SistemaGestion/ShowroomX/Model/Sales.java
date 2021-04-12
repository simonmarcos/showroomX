package com.SistemaGestion.ShowroomX.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "Sales")
public class Sales implements Serializable, Comparable<Sales> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSales;

    @Column(name = "date", nullable = false)
    private java.sql.Date date;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "paymentType", nullable = false)
    private String paymentTpye;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_CLIENT", nullable = false)
    @JsonBackReference
    private Client client;

    @JoinTable(name = "BrandSales", joinColumns = @JoinColumn(name = "FK_SALES", nullable = false), inverseJoinColumns = @JoinColumn(name = "FK_BRAND", nullable = false))
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Brand> brand;

    public Sales() {
    }

    public Sales(long idSales, Date date, double price, int amount, String paymentTpye, Client client, Set<Brand> brand) {
        this.idSales = idSales;
        this.date = date;
        this.price = price;
        this.amount = amount;
        this.paymentTpye = paymentTpye;
        this.client = client;
        this.brand = brand;
    }

    public long getIdSales() {
        return idSales;
    }

    public void setIdSales(long idSales) {
        this.idSales = idSales;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPaymentTpye() {
        return paymentTpye;
    }

    public void setPaymentTpye(String paymentTpye) {
        this.paymentTpye = paymentTpye;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Brand> getBrand() {
        return brand;
    }

    public void setBrand(Set<Brand> brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "idSales=" + idSales +
                ", date=" + date +
                ", price=" + price +
                ", amount=" + amount +
                ", paymentTpye='" + paymentTpye + '\'' +
                ", client=" + client +
                ", brand=" + brand +
                '}';
    }

    @Override
    public int compareTo(Sales o) {
        return this.date.compareTo(o.getDate());
    }
}

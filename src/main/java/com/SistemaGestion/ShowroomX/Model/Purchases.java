package com.SistemaGestion.ShowroomX.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "Purchases")
public class Purchases implements Serializable, Comparable<Purchases> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPurchases;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false)
    private Calendar date;

    @Column(name = "amount", nullable = false, length = 30)
    private int amount;

    @Column(name = "price", nullable = false, length = 30)
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_BRAND", updatable = false)
    @JsonBackReference
    private Brand brand;

    public Purchases() {
    }

    public Purchases(long idPurchases, Calendar date, int amount, double price, Brand bran) {
        this.idPurchases = idPurchases;
        this.date = date;
        this.amount = amount;
        this.price = price;
        this.brand = bran;
    }


    public long getIdPurchases() {
        return idPurchases;
    }

    public void setIdPurchases(long idPurchases) {
        this.idPurchases = idPurchases;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Brand getBran() {
        return brand;
    }

    public void setBran(Brand bran) {
        this.brand = bran;
    }

    @Override
    public String toString() {
        return "Purchases{" +
                "idPurchases=" + idPurchases +
                ", date=" + date +
                ", amount=" + amount +
                ", price=" + price +
                ", bran=" + brand +
                '}';
    }

    @Override
    public int compareTo(Purchases o) {
        return this.date.compareTo(o.getDate());
    }
}

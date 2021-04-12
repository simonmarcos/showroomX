package com.SistemaGestion.ShowroomX.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Provider")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idProvider")
public class Provider implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProvider;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @Column(name = "province", nullable = false, length = 50)
    private String province;

    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    //@JoinTable(name = "ProviderBrand", joinColumns = @JoinColumn(name = "FK_PROVIDER", nullable = false), inverseJoinColumns = @JoinColumn(name = "FK_BRAND", nullable = false))

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "provider")
    @JsonManagedReference
    private Set<Brand> brand;

    public Provider() {
    }

    public Provider(long idProvider, String name, String province, String phone, Set<Brand> brand) {
        this.idProvider = idProvider;
        this.name = name;
        this.province = province;
        this.phone = phone;
        this.brand = brand;
    }

    public long getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(long idProvider) {
        this.idProvider = idProvider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Brand> getBrand() {
        return brand;
    }

    public void setBrand(Set<Brand> brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Provider)) return false;
        Provider provider = (Provider) o;
        return Objects.equals(name, provider.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Provider{" +
                "idProvider=" + idProvider +
                ", name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", phone='" + phone + '\'' +
                ", brand=" + brand +
                '}';
    }
}

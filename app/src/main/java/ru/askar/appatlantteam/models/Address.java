package ru.askar.appatlantteam.models;

import java.io.Serializable;

/**
 * Created by Сайида on 23.10.2017.
 */

public class Address implements Serializable {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoLocation geo;

    public Address(String street, String suite, String city, String zipcode, GeoLocation geo) {

        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geo = geo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public GeoLocation getGeo() {
        return geo;
    }

    public void setGeo(GeoLocation geo) {
        this.geo = geo;
    }

    @Override
    public int hashCode() {
        int hash = 9*street.hashCode() + 11*suite.hashCode() + 13*city.hashCode() + 17*zipcode.hashCode() + 19*geo.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Address address = (Address) obj;
        if (!street.equals(address.street)) return false;
        if (!suite.equals(address.suite)) return false;
        if (!city.equals(address.city)) return false;
        if (!zipcode.equals(address.zipcode)) return false;
        return geo.equals(address.geo);
    }
}

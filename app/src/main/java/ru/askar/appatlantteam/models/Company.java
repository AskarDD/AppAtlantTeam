package ru.askar.appatlantteam.models;

import java.io.Serializable;

/**
 * Created by Сайида on 23.10.2017.
 */

public class Company implements Serializable {
    private String name;
    private String catchPhrase;
    private String bs;

    public Company(String name, String catchPhrase, String bs) {
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    @Override
    public int hashCode() {
        int hash = 9*name.hashCode() + 11*catchPhrase.hashCode() + 13*bs.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Company company = (Company) obj;
        if (!name.equals(company.name) || !catchPhrase.equals(company.catchPhrase) || !bs.equals(company.bs))
            return false;
        return true;
    }
}

package de.bfs.idfparser.model;

import java.io.Serializable;

public class Country implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;

    private String idfCountry;

    private String idfCountryGe;

    public Country() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIdfCountry() {
        return idfCountry;
    }

    public void setIdfCountry(String idfCountry) {
        this.idfCountry = idfCountry;
    }

    public String getIdfCountryGe() {
        return idfCountryGe;
    }

    public void setIdfCountryGe(String idfCountryGe) {
        this.idfCountryGe = idfCountryGe;
    }
}

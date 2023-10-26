package de.bfs.idfparser.model;

import java.io.Serializable;

public class Nuclide implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;

    private String nuclide;

    private String nuclideText;

    private String idfNuclideKey;

    public Nuclide() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getNuclide() {
        return nuclide;
    }

    public void setNuclide(String nuclide) {
        this.nuclide = nuclide;
    }

    public String getNuclideText() {
        return nuclideText;
    }

    public void setNuclideText(String nuclideText) {
        this.nuclideText = nuclideText;
    }

    public String getIdfNuclideKey() {
        return idfNuclideKey;
    }

    public void setIdfNuclideKey(String idfNuclideKey) {
        this.idfNuclideKey = idfNuclideKey;
    }

}

package de.bfs.idfparser.model;

import java.io.Serializable;

public class Unit implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;

    private String unit;

    private String unitText;

    private String unitEudf;

    public Unit() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitText() {
        return unitText;
    }

    public void setUnitText(String unitText) {
        this.unitText = unitText;
    }

    public String getUnitEudf() {
        return unitEudf;
    }

    public void setUnitEudf(String unitEudf) {
        this.unitEudf = unitEudf;
    }

}

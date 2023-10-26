package de.bfs.idfparser.model;

import java.io.Serializable;

public class Dom implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer codeDom;

    private Integer domUnit;

    private Integer duration;

    private Integer durationUnit;

    private Integer envmedium;

    private String domText;

    private Integer delay;

    private String durationText;

    private Integer idfNuclide;

    public Dom() {
    }

    public Integer getCodeDom() {
        return codeDom;
    }

    public void setCodeDom(Integer codeDom) {
        this.codeDom = codeDom;
    }

    public Integer getDomUnit() {
        return domUnit;
    }

    public void setDomUnit(Integer domUnit) {
        this.domUnit = domUnit;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getDurationUnit() {
        return durationUnit;
    }

    public void setDurationUnit(Integer durationUnit) {
        this.durationUnit = durationUnit;
    }

    public Integer getEnvmedium() {
        return envmedium;
    }

    public void setEnvmedium(Integer envmedium) {
        this.envmedium = envmedium;
    }

    public String getDomText() {
        return domText;
    }

    public void setDomText(String domText) {
        this.domText = domText;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public String getDurationText() {
        return durationText;
    }

    public void setDurationText(String durationText) {
        this.durationText = durationText;
    }

    public Integer getIdfNuclide() {
        return idfNuclide;
    }

    public void setIdfNuclide(Integer idfNuclide) {
        this.idfNuclide = idfNuclide;
    }

}

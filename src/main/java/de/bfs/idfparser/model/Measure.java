package de.bfs.idfparser.model;

import java.io.Serializable;
import java.util.Date;

import javax.json.bind.annotation.JsonbProperty;

public class Measure implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean attnData;

    private Boolean testData;

    private String localityCode;

    private Date startMeasure;

    private Date endMeasure;

    private Integer dom;

    private String valueConstraint;

    private Double measuredValue;

    private Integer nuclide;

    private Integer status;

    private Double uncertainty;

    private Double detectionLimit;

    private Date timeStamp;

    private String comment;

    public Measure() {
    }

    public Boolean getAttnData() {
        return attnData;
    }

    public void setAttnData(Boolean attnData) {
        this.attnData = attnData;
    }

    public Boolean getTestData() {
        return testData;
    }

    public void setTestData(Boolean testData) {
        this.testData = testData;
    }

    public String getLocalityCode() {
        return localityCode;
    }

    public void setLocalityCode(String localityCode) {
        this.localityCode = localityCode;
    }

    public Date getStartMeasure() {
        return startMeasure;
    }

    public void setStartMeasure(Date startMeasure) {
        this.startMeasure = startMeasure;
    }

    public Date getEndMeasure() {
        return endMeasure;
    }

    public void setEndMeasure(Date endMeasure) {
        this.endMeasure = endMeasure;
    }

    public Integer getDom() {
        return dom;
    }

    public void setDom(Integer dom) {
        this.dom = dom;
    }

    public String getValueConstraint() {
        return valueConstraint;
    }

    public void setValueConstraint(String valueConstraint) {
        this.valueConstraint = valueConstraint;
    }

    public Double getMeasuredValue() {
        return measuredValue;
    }

    public void setMeasuredValue(Double measuredValue) {
        this.measuredValue = measuredValue;
    }

    public Integer getNuclide() {
        return nuclide;
    }

    public void setNuclide(Integer nuclide) {
        this.nuclide = nuclide;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getUncertainty() {
        return uncertainty;
    }

    public void setUncertainty(Double uncertainty) {
        this.uncertainty = uncertainty;
    }

    public Double getDetectionLimit() {
        return detectionLimit;
    }

    public void setDetectionLimit(Double detectionLimit) {
        this.detectionLimit = detectionLimit;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

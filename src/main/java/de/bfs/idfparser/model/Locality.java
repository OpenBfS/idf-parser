package de.bfs.idfparser.model;

import java.io.Serializable;
import java.util.Date;

public class Locality implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean attnData;

    private boolean testData;

    private String localityCode;

    private String localityName;

    private Double latitude;

    private Double longitude;

    private Double heightAboveSea;

    private Double heightAboveGround;

    private Date timeStamp;

    private String comment;

    private String geoKey;

    public Locality() {
    }

    public boolean isAttnData() {
        return attnData;
    }

    public void setAttnData(boolean attnData) {
        this.attnData = attnData;
    }

    public boolean isTestData() {
        return testData;
    }

    public void setTestData(boolean testData) {
        this.testData = testData;
    }

    public String getLocalityCode() {
        return localityCode;
    }

    public void setLocalityCode(String localityCode) {
        this.localityCode = localityCode;
    }

    public String getLocalityName() {
        return localityName;
    }

    public void setLocalityName(String localityName) {
        this.localityName = localityName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getHeightAboveSea() {
        return heightAboveSea;
    }

    public void setHeightAboveSea(Double heightAboveSea) {
        this.heightAboveSea = heightAboveSea;
    }

    public Double getHeightAboveGround() {
        return heightAboveGround;
    }

    public void setHeightAboveGround(Double heightAboveGround) {
        this.heightAboveGround = heightAboveGround;
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

    public String getGeoKey() {
        return geoKey;
    }

    public void setGeoKey(String geoKey) {
        this.geoKey = geoKey;
    }
}

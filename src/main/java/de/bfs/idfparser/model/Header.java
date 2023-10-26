package de.bfs.idfparser.model;

import javax.json.bind.annotation.JsonbProperty;

public class Header {

    @JsonbProperty("sender_country")
    private String senderCountry;

    @JsonbProperty("sender_organization")
    private String senderOrganization;

    @JsonbProperty("dest_country")
    private String destCountry;

    @JsonbProperty("dest_org")
    private String destOrg;

    private String date;

    @JsonbProperty("idf_version")
    private String version;

    public String getSenderCountry() {
        return senderCountry;
    }

    public void setSenderCountry(String senderCountry) {
        this.senderCountry = senderCountry;
    }

    public String getSenderOrganization() {
        return senderOrganization;
    }

    public void setSenderOrganization(String senderOrganization) {
        this.senderOrganization = senderOrganization;
    }

    public String getDestCountry() {
        return destCountry;
    }

    public void setDestCountry(String destCountry) {
        this.destCountry = destCountry;
    }

    public String getDestOrg() {
        return destOrg;
    }

    public void setDestOrg(String destOrg) {
        this.destOrg = destOrg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

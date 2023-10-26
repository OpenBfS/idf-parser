package de.bfs.idfparser.model;

import java.util.List;

import javax.json.bind.annotation.JsonbProperty;

public class IdfJson {
    @JsonbProperty("site")
    private List<Locality> site;
    @JsonbProperty("data")
    private List<Measure> data;
    @JsonbProperty("header")
    private Header header;

    public List<Locality> getSite() {
        return this.site;
    }

    public void setSite(List<Locality> site) {
        this.site = site;
    }

    public List<Measure> getData() {
        return this.data;
    }

    public void setData(List<Measure> data) {
        this.data = data;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

}

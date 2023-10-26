package de.bfs.idfparser.model;

import java.io.Serializable;

public class Envmedium implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;

    private String envmediumText;

    public Envmedium() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getEnvmediumText() {
        return envmediumText;
    }

    public void setEnvmediumText(String envmediumText) {
        this.envmediumText = envmediumText;
    }

}

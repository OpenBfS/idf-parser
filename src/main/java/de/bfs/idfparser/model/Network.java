package de.bfs.idfparser.model;

import java.io.Serializable;

public class Network implements Serializable {
    private static final long serialVersionUID = 1L;

    private String networkId;

    private String network;

    public Network() {
    }

    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }
}

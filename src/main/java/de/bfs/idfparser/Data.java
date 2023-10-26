package de.bfs.idfparser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.bfs.idfparser.model.Country;
import de.bfs.idfparser.model.Dom;
import de.bfs.idfparser.model.Envmedium;
import de.bfs.idfparser.model.Network;
import de.bfs.idfparser.model.Nuclide;
import de.bfs.idfparser.model.Unit;

public class Data {

    private static Data instance = null;

    private Map<String, Country> countries;
    private Map<Integer, Dom> doms;
    private Map<Integer, Envmedium> envmediums;
    private Map<String, Network> networks;
    private Map<String, Nuclide> nuclides;
    private Map<Integer, Nuclide> nuclidesByCode;
    private Map<Integer, Unit> units;

    private Data() throws JsonParseException, JsonMappingException, IOException {
        countries = new HashMap<String, Country>();
        doms = new HashMap<Integer, Dom>();
        envmediums = new HashMap<Integer, Envmedium>();
        networks = new HashMap<String, Network>();
        nuclides = new HashMap<String,Nuclide>();
        nuclidesByCode = new HashMap<Integer, Nuclide>();
        units = new HashMap<Integer, Unit>();
        loadData();
    }

    public static void init() throws JsonParseException, JsonMappingException, IOException {
        if (instance == null) {
            instance = new Data();
        }
    }

    public static Data getInstance() throws JsonParseException, JsonMappingException, IOException {
        if (instance == null) {
            instance = new Data();
        }
        return instance;
    }

    private void loadData() throws JsonParseException, JsonMappingException, IOException {
        loadCountries();
        loadDoms();
        loadEnvmediums();
        loadNetworks();
        loadNuclides();
        loadUnits();
    }

    private void loadCountries() throws JsonParseException, JsonMappingException, IOException {
        InputStream inputStream = loadFile("country.json");
        ObjectMapper m = new ObjectMapper();
        Country[] objects = m.readValue(inputStream, Country[].class);
        for (Country country : objects) {
            countries.put(country.getCode(), country);
        }
    }

    private void loadDoms() throws JsonParseException, JsonMappingException, IOException {
        InputStream inputStream = loadFile("dom.json");
        ObjectMapper m = new ObjectMapper();
        Dom[] objects = m.readValue(inputStream, Dom[].class);
        for (Dom dom: objects) {
            doms.put(dom.getCodeDom(), dom);
        }
    }

    private void loadEnvmediums() throws JsonParseException, JsonMappingException, IOException {
        InputStream inputStream = loadFile("envmedium.json");
        ObjectMapper m = new ObjectMapper();
        Envmedium[] objects = m.readValue(inputStream, Envmedium[].class);
        for (Envmedium env: objects) {
            envmediums.put(env.getCode(), env);
        }
    }

    private void loadNetworks() throws JsonParseException, JsonMappingException, IOException {
        InputStream inputStream = loadFile("network.json");
        ObjectMapper m = new ObjectMapper();
        Network[] objects = m.readValue(inputStream, Network[].class);
        for (Network network: objects) {
            networks.put(network.getNetworkId(), network);
        }
    }

    private void loadNuclides() throws JsonParseException, JsonMappingException, IOException {
        InputStream inputStream = loadFile("nuclide.json");
        ObjectMapper m = new ObjectMapper();
        Nuclide[] objects = m.readValue(inputStream, Nuclide[].class);
        for (Nuclide nuclide: objects) {
            nuclides.put(nuclide.getIdfNuclideKey(), nuclide);
            nuclidesByCode.put(nuclide.getCode(), nuclide);
        }
    }

    private void loadUnits() throws JsonParseException, JsonMappingException, IOException {
        InputStream inputStream = loadFile("unit.json");
        ObjectMapper m = new ObjectMapper();
        Unit[] objects = m.readValue(inputStream, Unit[].class);
        for (Unit unit: objects) {
            units.put(unit.getCode(), unit);
        }
    }

    private InputStream loadFile(String fileName) throws IllegalArgumentException{
        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }

    public Collection<Country> getCountries() {
        return countries.values();
    }

    public Country getCountryByCode(String code) {
        if (countries.containsKey(code)) {
            return countries.get(code);
        }
        return null;
    }

    public Collection<Dom> getDoms() {
        return doms.values();
    }

    public Dom getDomByCode(Integer code) {
        if (doms.containsKey(code)) {
            return doms.get(code);
        }
        return null;
    }

    public Collection<Envmedium> getEnvmediums() {
        return envmediums.values();
    }

    public Envmedium getEnvmediumByCode(Integer code) {
        if (envmediums.containsKey(code)) {
            return envmediums.get(code);
        }
        return null;
    }

    public Collection<Network> getNetworks() {
        return networks.values();
    }

    public Network getNetworkById(String id) {
        if (networks.containsKey(id)) {
            return networks.get(id);
        }
        return null;
    }

    public Collection<Nuclide> getNuclides() {
        return nuclides.values();
    }

    public Nuclide getNuclideByIdfKey(String key) {
        if (nuclides.containsKey(key)) {
            return nuclides.get(key);
        }
        return null;
    }

    public Nuclide getNuclideByCode(Integer key) {
        if (nuclidesByCode.containsKey(key)) {
            return nuclidesByCode.get(key);
        }
        return null;
    }

    public Collection<Unit> getUnits() {
        return units.values();
    }

    public Unit getUnitByCode(Integer code) {
        if (units.containsKey(code)) {
            return units.get(code);
        }
        return null;
    }
}

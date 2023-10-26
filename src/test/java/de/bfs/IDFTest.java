package de.bfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import de.bfs.idfparser.Data;
import de.bfs.idfparser.IDFToJSON;
import de.bfs.idfparser.JSONToIDF;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * Unit tests for IDF importer
 */
public class IDFTest extends TestCase{

    /**
     * Test loading the resource files.
     */
    @Test
    public void testLoadResources() {
        try {
            Data.getInstance();
        } catch (JsonParseException e) {
            assertTrue(false);
            e.printStackTrace();
        } catch (JsonMappingException e) {
            assertTrue(false);
            e.printStackTrace();
        } catch (IOException e) {
            assertTrue(false);
            e.printStackTrace();
        }
    }

    @Test
    public void testCountries() {
        try {
            assertTrue(Data.getInstance().getCountries().size() > 0);
        } catch (JsonParseException e) {
            assertTrue(false);
            e.printStackTrace();
        } catch (JsonMappingException e) {
            assertTrue(false);
            e.printStackTrace();
        } catch (IOException e) {
            assertTrue(false);
            e.printStackTrace();
        }
    }

    @Test
    public void testDoms() {
        try {
            assertTrue(Data.getInstance().getDoms().size() > 0);
        } catch (JsonParseException e) {
            assertTrue(false);
            e.printStackTrace();
        } catch (JsonMappingException e) {
            assertTrue(false);
            e.printStackTrace();
        } catch (IOException e) {
            assertTrue(false);
            e.printStackTrace();
        }
    }

    @Test
    public void testEnvmediums() {
        try {
            assertTrue(Data.getInstance().getEnvmediums().size() > 0);
        } catch (JsonParseException e) {
            assertTrue(false);
            e.printStackTrace();
        } catch (JsonMappingException e) {
            assertTrue(false);
            e.printStackTrace();
        } catch (IOException e) {
            assertTrue(false);
            e.printStackTrace();
        }
    }

    @Test
    public void testNetworks() {
        try {
            assertTrue(Data.getInstance().getNetworks().size() > 0);
        } catch (JsonParseException e) {
            assertTrue(false);
            e.printStackTrace();
        } catch (JsonMappingException e) {
            assertTrue(false);
            e.printStackTrace();
        } catch (IOException e) {
            assertTrue(false);
            e.printStackTrace();
        }
    }
  
    @Test
    public void testNuclides() {
        try {
            assertTrue(Data.getInstance().getNuclides().size() > 0);
        } catch (JsonParseException e) {
            assertTrue(false);
            e.printStackTrace();
        } catch (JsonMappingException e) {
            assertTrue(false);
            e.printStackTrace();
        } catch (IOException e) {
            assertTrue(false);
            e.printStackTrace();
        }
    }
    @Test
    public void testUnits() {
        try {
            assertTrue(Data.getInstance().getUnits().size() > 0);
        } catch (JsonParseException e) {
            assertTrue(false);
            e.printStackTrace();
        } catch (JsonMappingException e) {
            assertTrue(false);
            e.printStackTrace();
        } catch (IOException e) {
            assertTrue(false);
            e.printStackTrace();
        }
    }

    /**
     * Test with an json input file with predefined header and print out the result on success.
     */
    @Test
    public void testJsonImportWithHeader() {
        JSONToIDF parser = new JSONToIDF();
        File initialFile = new File("test.json");
        try {
            InputStream targetStream = new FileInputStream(initialFile);
            String result = parser.jsonToIdf(targetStream, "DEA ITJ 9505162347 302");
            System.out.println("Importer Output:");
            assertNotNull(result);
            System.out.println(result);
        } catch (FileNotFoundException e) {
            fail("File for test not found");
        }
    }

    /**
     * Test with an json input file and print out the result on success.
     */
    @Test
    public void testJsonImportWithoutHeader() {
        JSONToIDF parser = new JSONToIDF();
        File initialFile = new File("test.json");
        try {
            InputStream targetStream = new FileInputStream(initialFile);
            String result = parser.jsonToIdf(targetStream);
            System.out.println("Importer Output:");
            assertNotNull(result);
            System.out.println(result);
        } catch (FileNotFoundException e) {
            fail("File for test not found");
        }
    }

    /**
     * Test with an input file and print out the result on success.
     */
    @Test
    public void testIdfImport() {
        IDFToJSON parser = new IDFToJSON();
        File initialFile = new File("test.dat");
        try {
            InputStream targetStream = new FileInputStream(initialFile);
            String result = parser.idfToJson(targetStream);
            System.out.println("Importer Output:");
            assertNotNull(result);
            System.out.println(result);
        } catch (FileNotFoundException e) {
            fail("File for test not found");
        }
    }
}

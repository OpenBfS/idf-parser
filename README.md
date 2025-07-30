# IDF Parser

The IDF (International Data Exchange Format) format is a data interface for exchanging radiological data. This library parses either IDF data files to JSON or vice versa. The format description can be found in the document 'IDFe331.pdf' located in the ./docs folder.
The JSON object parsed from an IDF file has five main properties, errors{}, warnings{}, header{}, site[], data[] and text[].
The errors-object contains lines that cannot be interpreted due to missing or incorrect entries, e.g. for site-data this would be the lack of a coordinate specification, an example for measure data would be the lack of the DOM-code (description of measurement).
The warnings-object contains lines that are interpretable but deviate from the IDF standard, for example more decimal places are specified for a measured value than intended. Both the errors- and warnings-object contain the line number to identify the corresponding line in the original IDF file.
Successfully parsed lines are stored in the header-object, site-, data- and/or text-array.

Passed IDF files with coordinates in Gauss/Krueger (IDF Geo Key 3) or UTM (WGS84 datum - IDF Geo Key 6) are transformed into geographic coordinates in decimal places (IDF Geo Key 1) during parsing.

## License

The software is available under the GNU GPL v>=3 licence. For details see file COPYING.

## Using

The entry point for parsing IDF data is the function 'idfToJson' of the class 'IDFToJSON'. For parsing JSON data use the function 'jsonToIdf' of the class 'JSONToIDF'. Both functions expect a 'java.io.InputStream' as input parameter and returns the result as 'String' (JSON object or IDF string).

## Structure

The importer uses ANTLR4 (V 4.9) to parse the data format and split it into its components. Further interpretation of the data is done in the 'de.bfs.idfparser.parser.IDFListener', which types the input data.

With 'mvn clean compile package' the jar can be created.

## Tests

The function of the importer is tested by unit tests. To do this, it is necessary to place the file to be imported in the main folder (the same as this readme) and rename it to test.dat or test.json.
The tests can be started with 'mvn test'.

## Contact

Marco Pochert\
mpochert@bfs.de\
Bundesamt für Strahlenschutz │ Federal Office for Radiation Protection\
Koordination Notfallschutzsysteme │ Coordination of Emergency Response Systems │ RN 1\

Willy-Brandt-Strasse 5\
38226 Salzgitter\
info@bfs.de\
<https://www.bfs.de>

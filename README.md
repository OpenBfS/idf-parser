# IDF Parser

The IDF (International Data Exchange Format) format is a data interface for exchanging radiological data. This library parses either IDF data files to JSON or vice versa (see ./test.dat and ./test.json).


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
Koordination Notfallschutzsysteme │ Coordination Emergency Systems │ RN 1\

Willy-Brandt-Strasse 5\
38226 Salzgitter\
info@bfs.de\
<https://www.bfs.de>

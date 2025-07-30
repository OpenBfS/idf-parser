# Change Log
All notable changes to this project will be documented in this file.
 
## [1.4.2] - 2025-07-02
  
### Added

- [2.3.3 Time of measurement] 
  IDF file passed to idf-parser allows now seconds (ToDo: adaption in IDFe331.pdf).

### Changed

- [2.3.2 Site ID]
  Site ID now allows a minimum string length of 4 bytes instead of 5 bytes (ToDo: adaption in IDFe331.pdf).

- [2.3.11.1 Geographical Longitude and Latitude]
  Adopt coordinate accuracy from IDF file (ToDo: adaption in IDFe331.pdf). 
 
### Fixed

- [2.3.4 Time key]
  JSON to IDF: Time zone of 'time of measurement' is UTC.

- [2.3.11.2 UTM Coordinates (not MGRS) & 2.3.11.3 Gauss/Krueger Coordinates]
  Fixed coordination transformation to geographical coordinates.
   

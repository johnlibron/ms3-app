# Summary

The purpose of this repository is for Coding Challenge in Mountain State Software Solutions (MS³).

# How to Use

* Run Eclipse Mars 2
* Set the java version to 1.8
* Download Spring IDE 3.8.4.RELEASE in Eclipse Marketplace
* Update Maven project in Eclipse
* Run the project as Spring Boot App
* Select ms3Interview.csv file
* Upload the CSV file

# Overview

I used Spring Boot in coding because it helps accelerate my application development. The approach I used is Controller, Service, and DAO. I used OpenCSV to handle the reading the uploaded CSV file and writing a CSV file (ms3Interview-bad.csv) for invalid records. I used JpaRepository's saveAll function to save all the list of records at once and helps dealing with the issues of overhead transactions. To increase speed and save SQL statement execution time, DynamicInsert annotation was used in the Record entity.

# Tools used

* Eclipse Mars 2
* Spring Boot
* SQLite database
* OpenCSV
* Thymeleaf
* Tomcat
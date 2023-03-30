# API Testing Framework for service - Genderize.io
```
https://genderize.io/
```
- This service provides an opportunity to determine the gender by the specified name.
- To determine the gender, you need to send a GET request to: 
```
https://api.genderize.io/
```
and pass the GET parameter with key "name" and value "{any name}".

Example of request:
```
https://api.genderize.io/?name=John
```
### The stack used for this project
- Java
- Maven
- TestNG
- REST Assured
### Description of framework and test cases:
##### 1. The test cases were designed based on the POJO concept:
- Path to POJO class for results with gender data
`/api-rest-assured/src/test/java/api/genderize/genders/GenderData.java`
- Path to POJO class for results with error data
`/api-rest-assured/src/test/java/api/genderize/genders/MissingParameter.java`
##### 2. Two test classes were developed to test the functionality:
- Path for positive test cases:
`/api-rest-assured/src/test/java/api/genderize/PositiveTests.java`
- Path for negative test cases:
`/api-rest-assured/src/test/java/api/genderize/NegativeTests.java`
##### 3. For testing, static data is used that is placed in java classes:
- Path for query parameters
`/api-rest-assured/src/test/java/api/genderize/genders/QueryParameters.java`
- Path for response values (text in body / headers / status code descriptions)
`/api-rest-assured/src/test/java/api/genderize/genders/ResponseValues.java`
- Path for another helper data 
`/api-rest-assured/src/test/java/api/genderize/helpers/HelperData.java`
#### 4. Some test cases have been developed based on the requirements specified on the main page of the site 
```
https://genderize.io/
```
#### 5. Running test classes via the command line 
```
mvn -Dtest=PositiveTests,NegativeTests* test
```

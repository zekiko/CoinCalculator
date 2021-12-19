# Coin Calculator Rest API

This is a Spring Boot project for link conversion between web and mobile requests.

### REQUIREMENTS
* Java 8
* Maven
* PostgreSQL
* Git
* Docker Client

## RUN THE PROJECT

- Clone the project
- Configure PostgreSQL password in application.properties
- Build the project:
```
mvn clean package
```
- Run the application with jar file
```
java -jar target/coin-calculator-1.0.0.jar
```
- Make sure your PostgreSQL is up and running.

### With DOCKER

- Build the project
```
mvn clean package
```
- Run using docker-compose
- Type this:
```
docker build . 
```
- Then this:
```
docker-compose up -d 
```
- Or you can use single command below:
```
docker-compose up --build 
```

## RUN TESTS
```
mvn test 
```

## USAGE
- You can use the api with Postman or Swagger like tools.
- Or you can use the api directly in your application.
### RULES:
-Valid Coin Types:
```
BTC, ETH
```
-Valid Currency Types:
```
USD, EUR
```
-Minimum and maximum currency amount:
```
min: 25 USD/EUR
max: 5000 USD/EUR
```
#### Example request 1: 
- Calculate USD to BTC - Post Endpoint and body:
```
/api/calculate/coin
{
  "coinType": "BTC",
  "currencyAmount": 1000,
  "currencyType": "USD"
}
```
#### Example request 2:
- Calculate BTC to USD - Post Endpoint and body:
```
/api/calculate/currency
{
  "coinAmount": 10,
  "coinType": "BTC",
  "currencyType": "USD"
}
```
#### Example request 2:
- Get all rows from db - Get Endpoint:
```
/api/coins
```
 
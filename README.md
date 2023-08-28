# README

## Pull the Docker Image

Pull image from remote Docker repository
``docker pull magnitus375/fetch-interview``

## Create the Docker Image
Create the executable Spring Boot jar  
``gradle :bootJar``

For windows
``.\gradlew :booJar``


Create docker image  
``docker build -t fetch-inteview . ``

```docker run -p 8082:8080 fetch-inteview```

```
curl --location 'http://localhost:8082/receipt/process' \
--header 'Content-Type: application/json' \
--data '{
  "retailer": "Target",
  "purchaseDate": "2022-01-01",
  "purchaseTime": "13:01",
  "items": [
    {
      "shortDescription": "Mountain Dew 12PK",
      "price": "6.49"
    },{
      "shortDescription": "Emils Cheese Pizza",
      "price": "12.25"
    },{
      "shortDescription": "Knorr Creamy Chicken",
      "price": "1.26"
    },{
      "shortDescription": "Doritos Nacho Cheese",
      "price": "3.35"
    },{
      "shortDescription": "   Klarbrunn 12-PK 12 FL OZ  ",
      "price": "12.00"
    }
  ],
  "total": "35.35"
}'
```

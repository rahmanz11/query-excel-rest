"# query-excel-rest" 

## Then environment must have maven and java-8 installed

## To run the application
    First go to inside the project directory
    Then execute:
    mvn spring-boot:run
## Sample request-1
    http://localhost:8000/fruit-month-price/fruit/cucumber/month/jul

## Sample response of request-1
    {
        "id": 16733,
        "fruit": "cucumber",
        "month": "jul",
        "fmp": "2.73",
        "environment": "8000"
    }

## Sample request-2
    http://localhost:8000/fruit-total-price/fruit/cucumber/month/jul/quantity/10
    
## Sample response of request-2
    {
        "id": 11432,
        "fruit": "cucumber",
        "month": "jul",
        "fmp": "2.73",
        "quantity": 10,
        "totalPrice": 27.3,
        "environment": "8000"
    }

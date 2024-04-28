# Shopping Cart RESTFul API

This is a RESTful API for shopping cart functionality with `Spring Boot`.

## Teach Stack

- Spring + Spring Boot 3
- MySQL
- Swagger

## Features

- RestFul API best practices
- CURD operations for products, shopping cart, and shopping cart items
- Protect `/api/v1/products` routes
- Role based authentication for `POST` request to `/api/v1/products` to create new product
- Rate limit to `/api/v1/products` routes (10 requests per 10 seconds)
- Pagination
- Query filter

## TODO

- [x] Setup database
- [x] Setup modeling
- [x] Setup endpoints
- [x] Develop CURD operations
- [x] Add validation for user requests
- [x] Pagination for products
- [x] Filter query
- [x] Rate Limiting (10 requests per second)
- [x] Add Authentication
- [x] Document the API
- [x] Containerizing the application

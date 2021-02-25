## spring-Kafka
This is a simple application ready to produce and consume events ok Kafka

## How to use

There's an end-point send user information to a topic on Kafka.

**URL:** http://localhost:8080/user ( POST )

**bodyExample:**

{
"name": "John Doe",
"email": "john@gmail.com",
"phone": "12345678",
"age": 43
}

## Obs:

One topic will be consumed by more than one consumer only if the topic have more than one partition.
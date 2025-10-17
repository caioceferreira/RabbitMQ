Use docker to run
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
This is the producer, the consumer will be in a separated project

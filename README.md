# playgroud
You are trying to build a rocket!

1. ~~netflix-eureka spring-cloud-confi~~ consul
2. spring-cloud-gateway
3. spring-cloud-sleuth(with zipkin) 
   
```shell
docker run -d -p 9411:9411 openzipkin/zipkin-slim
docker run --name consul -d -p 8500:8500 consul agent -dev -ui -client '0.0.0.0'
```

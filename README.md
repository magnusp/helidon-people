# helidon-people



## Build and run


With JDK21
```bash
mvn package
java -jar target/helidon-people.jar
```

## Exercise the application

JSON:
```
> curl -XPOST -H "Content-Type: application/json" -d '{"name": "foo"}' http://localhost:8090/person
{"id":1,"name":"foo"}

> curl http://localhost:8090/person
{"values":[{"id":1,"name":"foo"}]}

> curl -X PUT -H "Content-Type: application/json" -d '{"id" : "1", "name": "bar"}' http://localhost:8090/person/1

> curl http://localhost:8090/person
{"values":[{"id":1,"name":"bar"}]}
```

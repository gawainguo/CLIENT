# CLIENT
## Build

```shell script
# Build jar
mvn clean install -DskipTests

# Docker image
docker build -t ${image}:${tag} .
```

## Run
```shell script
docker-compose up -d
```

## Test
```shell script
curl http://localhost:5004/manager?url=http://10.225.25.89:5003/worker&n=100
```
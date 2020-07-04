### Deployment

#### Deploy with Docker

Start cluster with 3 brokers and 3 bookies.

```shell script
cd deployment/docker
docker-compose up -d
```

Shutdown the cluster
```shell script
cd deployment/docker
./down.sh
```

## Deploy a cluster on bare metal

Following the [official instruction](https://pulsar.apache.org/docs/en/deploy-bare-metal/).

### Usage

Config the 

Build the project

```shell script
mvn clean package
```

Start Producer

```shell script
java -cp ./target/pulsar-demo-0.0.1-SNAPSHOT-jar-with-dependencies.jar org.vander.ProducerThreadPool
```

Start Consumer

```shell script
java -cp ./target/pulsar-demo-0.0.1-SNAPSHOT-jar-with-dependencies.jar org.vander.ConsumerThreadPool
```


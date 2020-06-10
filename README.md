### Deployment
Start cluster with 3 brokers and 3 bookies.

```shell script
docker-compose up -d
```

### Usage

Producer

```shell script
java -cp ./target/pulsar-demo-0.0.1-SNAPSHOT-jar-with-dependencies.jar org.vander.ProducerThreadPool
```

Consumer

```shell script
java -cp ./target/pulsar-demo-0.0.1-SNAPSHOT-jar-with-dependencies.jar org.vander.ConsumerThreadPool
```

### Example Result

```
Message with ID 8:29:-1:0 successfully sent with time 76
Message with ID 7:29:-1:0 successfully sent with time 76
Message with ID 13:29:-1:0 successfully sent with time 76
Message with ID 17:30:-1:0 successfully sent with time 25
Message with ID 0:30:-1:0 successfully sent with time 26
Message with ID 9:30:-1:0 successfully sent with time 26
```
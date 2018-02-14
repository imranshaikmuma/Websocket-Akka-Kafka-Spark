# Websocket-Akka-Kafka-Spark
To stream trades(binance) through a websocket directly to spark, it gives an advantage to do analysis on trades on the go.
Spark structed streaming is very easy to do analysis as any data can be brought in as Streaming Dataframe and just can be queired like a regular SQL like syntax

Spark can get data from TCP ports directly but not Websockets

For that we need to take help from Akka Streams which is really awesome project. Akka Streams can be connected to Kafka which in turn can be connected to Spark

To run this project and see the live trades on Spark Streaming console:

1. Download Kafka from apache.kafka.com

2. Start the Zookeeper server:
   bin/zookeepererver-start.sh config/zookeeper.properties
   
3. Start the Kafka Server:
   bin/kafka-server-start.sh config/server.properties
  
4. Start the Kafka Consumer on port 9092 with any topic of choice:
   bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic binance --new-consumer

5. Download the project and run using "sbt run"

6. You can see the trades now on Kafka Consumer port of 9092:
  
![screenshot from 2018-02-14 10-12-37](https://user-images.githubusercontent.com/24683611/36211885-71de16e2-1170-11e8-8414-2d9aac6ca047.png)


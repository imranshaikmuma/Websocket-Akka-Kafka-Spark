import akka.Done
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.model.ws.{Message,WebSocketRequest, WebSocketUpgradeResponse}
import akka.stream.{ActorMaterializer}
import akka.stream.scaladsl.{Flow, Keep, Sink}
import com.softwaremill.react.kafka.{ProducerProperties, ReactiveKafka}
import scala.concurrent.Promise
import akka.stream.scaladsl.Source
import kafka.producer.ReactiveKafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer


object binance{
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    import system.dispatcher


    val kafka = new ReactiveKafka()


    val producer = ReactiveKafkaProducer[Array[Byte], String](ProducerProperties(
      bootstrapServers = "localhost:9092",
      topic = "binance",
      valueSerializer = new StringSerializer()
    ))


    val flow: Flow[Message, Message, Promise[Option[Message]]] =
      Flow.fromSinkAndSourceMat(
        Sink.foreach[Message](record=>producer.producer.send(new ProducerRecord[Array[Byte],String]("binance",record.toString()))),
        Source.maybe[Message])(Keep.right)


    val (upgradeResponse, promise) =
      Http().singleWebSocketRequest(
        WebSocketRequest("wss://stream.binance.com:9443/ws/bnbbtc@trade"),
        flow)

    val connected = upgradeResponse.map { upgrade =>
      if (upgrade.response.status == StatusCodes.SwitchingProtocols) {
        Done
      } else {
        throw new RuntimeException(s"Connection failed: ${upgrade.response.status}")
      }
    }
    connected.onComplete(println)
    //promise.success(None)
  }
}

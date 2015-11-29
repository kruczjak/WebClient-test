package controllers;

import play.libs.Akka;
import play.libs.F;
import play.mvc.Controller;
import play.mvc.WebSocket;

import play.*;
import play.libs.Json;
import play.mvc.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

public class Socket extends Controller {

    public WebSocket<String> index() {
        return new WebSocket<String>() {

            // Called when the Websocket Handshake is done.
            public void onReady(WebSocket.In<String> in, WebSocket.Out<String> out) {

                // For each event received on the socket,
                in.onMessage(new F.Callback<String>() {
                    public void invoke(String event) {
                        // Log events to the console
                        System.out.println(event);
                    }
                });

                // When the socket is closed.
                in.onClose(new F.Callback0() {
                    public void invoke() {
                        System.out.println("Disconnected");
                    }
                });

                startWriter(out);
            }
        };
    }

    private void startWriter(WebSocket.Out<String> out)  {
        Akka.system().scheduler().scheduleOnce(
            Duration.create(10, TimeUnit.MILLISECONDS),
            new Runnable() {
                public void run() {
                    Jedis jedis = new Jedis("localhost");
                    jedis.subscribe(new JedisPubSub() {
                        @Override
                        public void onMessage(String channel, String message) {
                            out.write(message);
                        }

                        @Override
                        public void onPMessage(String pattern, String channel, String message) {
                            out.write(message);
                        }
                    }, "client");
                }
            },
            Akka.system().dispatcher()
        );
    }
}

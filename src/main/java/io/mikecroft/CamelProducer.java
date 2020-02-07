package io.mikecroft;

import org.apache.camel.builder.RouteBuilder;

public class CamelProducer extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:foo?period=1s")
            .setBody(simple("Hello MQTT! ${header.firedTime}")) 
            .to("paho:topic");
    }

}
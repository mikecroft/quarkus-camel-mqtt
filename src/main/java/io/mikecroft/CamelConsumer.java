package io.mikecroft;

import org.apache.camel.builder.RouteBuilder;

public class CamelConsumer extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("paho:topic")
            .log("${body}");
    }

}
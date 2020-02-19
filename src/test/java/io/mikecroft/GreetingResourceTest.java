package io.mikecroft;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.microshed.testing.jupiter.MicroShedTest;
import org.testcontainers.containers.FixedHostPortGenericContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import javax.annotation.PostConstruct;

@MicroShedTest
@QuarkusTest
public class GreetingResourceTest {

    @Container
    public static GenericContainer<?> mqtt = new FixedHostPortGenericContainer<>("eclipse-mosquitto:1.6.8")
            .withFixedExposedPort(1883,1883);

    @PostConstruct
    public void init(){
        System.setProperty("camel.component.paho.broker-url",mqtt.getMappedPort(1883).toString());
    }
    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}
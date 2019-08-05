package revolut.money.transfer.controller;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import io.reactivex.Flowable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import revolut.money.transfer.Transfer;
import revolut.money.transfer.TransferStatus;
import revolut.money.transfer.service.MembershipService;
import revolut.money.transfer.service.MoneyTransferService;
import revolut.money.transfer.service.User;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class MoneyTransferControllerTest {

    @Inject
    EmbeddedServer embeddedServer;

    @BeforeEach
    void setUp() {
    }

    @Test
    void doTransfer() {
        try(RxHttpClient client = embeddedServer.getApplicationContext().createBean(RxHttpClient.class, embeddedServer.getURL())) {
            String requestBody = "{\"amount\" : 10.00,\"source\" :{\"username\":\"name_1\"},\"destination\" : {\"username\":\"name_2\"}}";
            HttpResponse<TransferStatus> rsp = client.toBlocking().exchange(HttpRequest.POST("/transfer",requestBody));
            assertEquals(200, rsp.getStatus().getCode());
        }
    }
}
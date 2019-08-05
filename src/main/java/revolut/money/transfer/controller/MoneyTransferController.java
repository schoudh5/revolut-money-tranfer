package revolut.money.transfer.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import revolut.money.transfer.service.MoneyTransferService;
import revolut.money.transfer.Transfer;
import revolut.money.transfer.TransferStatus;

import javax.inject.Inject;

@Controller("/transfer")
public class MoneyTransferController {
    @Inject
    private MoneyTransferService moneyTransferService;
    @Post()
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<TransferStatus> doTransfer(@Body Transfer transfer){
        return HttpResponse.ok(moneyTransferService.transfer(transfer));
    }
}

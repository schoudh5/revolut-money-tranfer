package revolut.money.transfer;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.HttpStatus;

@Controller("/test")
public class TestController {

    @Get("/")
    public HttpStatus index() {
        return HttpStatus.OK;
    }
}
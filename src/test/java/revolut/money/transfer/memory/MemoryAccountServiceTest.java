package revolut.money.transfer.memory;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import revolut.money.transfer.service.Account;
import revolut.money.transfer.service.AccountService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@MicronautTest
class MemoryAccountServiceTest {
    private static final double INITIAL_MONEY = 100.00;
    private static final String ACCOUNT_ID = UUID.randomUUID().toString();
    private AccountService accountService;

    @BeforeEach
    void setup(){
        accountService = new MemoryAccountService();
    }

    @Test
    void getAccountById_whenSearchById_thenReturnAccount(){
        Account newAccount = new Account(ACCOUNT_ID,INITIAL_MONEY);
        accountService.create(newAccount);
        assertEquals(newAccount, accountService.getAccountById(ACCOUNT_ID));
    }

    @Test
    void create_whenCalledWithNewAccount_thenAccountIsCreated() {
        Account newAccount = new Account(ACCOUNT_ID,INITIAL_MONEY);
        accountService.create(newAccount);
        assertEquals(newAccount, accountService.getAccountById(ACCOUNT_ID));
    }

    @Test
    void delete_whenCalledWithExistingAccount_thenAccountIsDeleted() {
        Account newAccount = new Account(ACCOUNT_ID,INITIAL_MONEY);
        accountService.create(newAccount);
        assertEquals(newAccount, accountService.getAccountById(ACCOUNT_ID));
        accountService.delete(newAccount);
        assertNull(accountService.getAccountById(ACCOUNT_ID));
    }
}
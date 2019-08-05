package revolut.money.transfer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountTest {

    private static final double INITIAL_MONEY = 100.00;
    private static final String ACCOUNT_ID = UUID.randomUUID().toString();
    private Account account;

    @BeforeEach
    void before(){
        this.account = new Account(ACCOUNT_ID, INITIAL_MONEY);
    }

    @Test
    void getAccountId_whenRetrieved_thenReturned() {
        assertEquals(ACCOUNT_ID, this.account.getAccountId());
    }

    @Test
    void getAvailableMoney_whenRetrieved_thenReturned() {
        assertEquals(INITIAL_MONEY, this.account.getAvailableMoney());
    }

    @Test
    void deposit_whenMoneyAdded_thenBalanceIsUpdated() {
        double amount = 10.00;
        account.deposit(amount);
        assertEquals(110.00, account.getAvailableMoney());
    }

    @Test
    void withdraw_whenMoneyWithdrawn_thenBalanceIsUpdated() {
        double amount = 10.00;
        account.withdraw(amount);
        assertEquals(90.00, account.getAvailableMoney());
    }
}
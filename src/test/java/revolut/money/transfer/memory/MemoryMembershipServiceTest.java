package revolut.money.transfer.memory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import revolut.money.transfer.service.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemoryMembershipServiceTest {
    private static final String USERNAME = "DUMMY_USERNAME";
    private static final double INITIAL_MONEY = 100.00;
    private static final String ACCOUNT_ID = UUID.randomUUID().toString();

    private UserService userService;
    private AccountService accountService;
    private MembershipService membershipService;

    @BeforeEach
    void setUp() {
        userService = new MemoryUserService();
        accountService = new MemoryAccountService();
        membershipService = new MemoryMembershipService(userService, accountService);
    }

    @Test
    void addAccountForUser() {
        verifyMembership();
    }

    @Test
    void getUserAccount() {
        verifyMembership();
    }

    private void verifyMembership() {
        User newUser= new User(USERNAME);
        Account account = new Account(ACCOUNT_ID, INITIAL_MONEY);
        userService.create(newUser);
        accountService.create(account);
        membershipService.addAccountForUser(newUser,account);
        Account userAccount = membershipService.getUserAccount(newUser);
        assertEquals(userAccount, account);
    }
}
package revolut.money.transfer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import revolut.money.transfer.Transfer;
import revolut.money.transfer.TransferStatus;
import revolut.money.transfer.memory.MemoryAccountService;
import revolut.money.transfer.memory.MemoryMembershipService;
import revolut.money.transfer.memory.MemoryUserService;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferServiceTest {
    private MembershipService membershipService;
    private UserService userService;
    private AccountService accountService;
    private MoneyTransferService moneyTransferService;
    @BeforeEach
    void setUp() {
        userService = new MemoryUserService();
        accountService = new MemoryAccountService();
        membershipService = new MemoryMembershipService(userService, accountService);
        moneyTransferService = new MoneyTransferService(membershipService, userService, accountService);
    }

    @Test
    void transfer() {
        TransferStatus transferStatus = moneyTransferService.transfer(new Transfer(20,
                new User("name_1"),
                new User("name_2")));
        assertEquals(80.00, transferStatus.getSourceBalance());
        assertEquals("name_1", transferStatus.getSourceUsername());
        assertEquals(120.00, transferStatus.getDestinationBalance());
        assertEquals("name_2", transferStatus.getDestinationUsername());
    }
}
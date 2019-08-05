package revolut.money.transfer.service;

import org.springframework.transaction.annotation.Transactional;
import revolut.money.transfer.Transfer;
import revolut.money.transfer.TransferStatus;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MoneyTransferService {

    private static final double INITIAL_MONEY = 100.00;
    private static final String ACCOUNT_ID_ONE = "accountOne";
    private static final String ACCOUNT_ID_TWO = "accounttwo";
    private static final String USERNAME_ONE = "name_1";
    private static final String USERNAME_TWO = "name_2";

    @Inject
    private MembershipService membershipService;
    @Inject
    private UserService userService;
    @Inject
    private AccountService accountService;

    public MoneyTransferService(MembershipService membershipService, UserService userService, AccountService accountService) {
        this.membershipService = membershipService;
        this.userService = userService;
        this.accountService = accountService;
        loadDummyData();
    }

    @Transactional
    public TransferStatus transfer(Transfer transfer){
        Account sourceAccount = membershipService.getUserAccount(transfer.getSource());
        Account destinationAccount = membershipService.getUserAccount(transfer.getDestination());
        if(sourceAccount.getAvailableMoney() >=transfer.getAmount()){
            sourceAccount.withdraw(transfer.getAmount());
            destinationAccount.deposit(transfer.getAmount());
        }
        return TransferStatus.newBuilder()
                .withSourceUsername(transfer.getSource().getUsername())
                .withDestinationUsername(transfer.getDestination().getUsername())
                .withSourceBalance(sourceAccount.getAvailableMoney())
                .withDestinationBalance(destinationAccount.getAvailableMoney())
                .build();
    }

    private void loadDummyData(){
        Account accountOne = new Account(ACCOUNT_ID_ONE, INITIAL_MONEY);
        Account accountTwo = new Account(ACCOUNT_ID_TWO, INITIAL_MONEY);
        User userOne = new User(USERNAME_ONE);
        User userTwo = new User(USERNAME_TWO);
        accountService.create(accountOne);
        accountService.create(accountTwo);
        userService.create(userOne);
        userService.create(userTwo);
        membershipService.addAccountForUser(userOne,accountOne);
        membershipService.addAccountForUser(userTwo,accountTwo);
    }

}

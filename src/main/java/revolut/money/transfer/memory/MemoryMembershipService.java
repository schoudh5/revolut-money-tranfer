package revolut.money.transfer.memory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import revolut.money.transfer.service.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;

@Singleton
public class MemoryMembershipService implements MembershipService {
    private static final Logger LOG = LoggerFactory.getLogger(MemoryMembershipService.class);
    private final Map<User, Account> accountByUser = new HashMap<>();

    @Inject
    private UserService userService;
    @Inject
    private AccountService accountService;

    public MemoryMembershipService(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void addAccountForUser(User user, Account account){
        preconditions(user, account);
        accountByUser.put(user,account);
    }

    @Override
    public Account getUserAccount(User user){
        if(!accountByUser.containsKey(user)){
            throw new IllegalArgumentException("User " + user.getUsername() + "account does not exist");
        }
        return accountByUser.get(user);
    }

    private void preconditions(User user, Account account) {
        requireNonNull(account);
        requireNonNull(user);
        duplicateAccountCheck(user);
        requireExists(user);
        requireExists(account);
    }

    private void duplicateAccountCheck(User user) {
        if(accountByUser.containsKey(user)){
            throw new IllegalArgumentException("User " + user.getUsername() + "account already exists");
        }
    }

    private void requireExists(User user) {
        requireNonNull(user, "user");
        if (userService.findByUsername(user.getUsername()) == null) {
            throw new IllegalArgumentException("User " + user.getUsername() + " does not exist!");
        }
    }

    private void requireExists(Account account) {
        requireNonNull(account, "account");
        if (accountService.getAccountById(account.getAccountId()) == null) {
            throw new IllegalArgumentException("Account " + account.getAccountId() + " does not exist!");
        }
    }
}


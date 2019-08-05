package revolut.money.transfer.memory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import revolut.money.transfer.service.Account;
import revolut.money.transfer.service.AccountService;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;

@Singleton
public class MemoryAccountService implements AccountService {

    private static final Logger LOG = LoggerFactory.getLogger(MemoryAccountService.class);
    private final Map<String, Account> accounts = new HashMap<>();

    @Override
    public Account getAccountById(String accountId) {
        requireNonNull(accountId, "accountId");
        return accounts.get(accountId);
    }

    @Override
    public void create(Account account) {
        requireNonNull(account, "account is not supplied");
        if (accounts.containsKey(account.getAccountId())) {
            throw new IllegalArgumentException("Account " + account.getAccountId() + " already exists");
        }
        accounts.put(account.getAccountId(), account);
        LOG.debug("Created account: {}", account.getAccountId());
    }

    @Override
    public void delete(Account account) {
        requireNonNull(account, "account is not supplied");
        accounts.remove(account.getAccountId());
        LOG.debug("Deleted account: {}", account.getAccountId());
    }
}

package revolut.money.transfer.service;

public interface AccountService {
    Account getAccountById(String accountId);
    void create(Account account);
    void delete(Account account);
}

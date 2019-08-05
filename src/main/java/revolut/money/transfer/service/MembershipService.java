package revolut.money.transfer.service;

public interface MembershipService {
    void addAccountForUser(User user, Account account);
    Account getUserAccount(User user);
}

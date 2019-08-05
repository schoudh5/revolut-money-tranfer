package revolut.money.transfer.service;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class Account {
    private final String accountId;
    private double availableMoney;
    public Account(String accountId, double availableMoney) {
        this.accountId = accountId;
        this.availableMoney = availableMoney;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getAvailableMoney() {
        return availableMoney;
    }

    public double deposit(double amount){
        double tempBalance = 0.0;
        tempBalance = availableMoney;
        tempBalance+=amount;
        availableMoney=tempBalance;
        return tempBalance;
    }

    public double withdraw(double amount){
        double tempBalance = 0.0;
        tempBalance = availableMoney;
        tempBalance-=amount;
        availableMoney=tempBalance;
        return tempBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return accountId.equals(account.accountId);
    }

    @Override
    public int hashCode() {
        return accountId.hashCode();
    }
}

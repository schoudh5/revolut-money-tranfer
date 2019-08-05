package revolut.money.transfer.service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private final String accountId;
    private double availableMoney;
    private Lock lock;
    public Account(String accountId, double availableMoney) {
        this.accountId = accountId;
        this.availableMoney = availableMoney;
        lock = new ReentrantLock();
    }

    public String getAccountId() {
        return accountId;
    }

    public double getAvailableMoney() {
        try{
            lock.lock();
            return availableMoney;
        } finally {
            lock.unlock();
        }

    }

    public double deposit(double amount){
        double tempBalance = 0.0;
        try{
            lock.lock();
            tempBalance = availableMoney;
            tempBalance+=amount;
            availableMoney=tempBalance;
            return tempBalance;
        } finally {
            lock.unlock();
        }
    }

    public double withdraw(double amount){
        double tempBalance = 0.0;
        try{
            lock.lock();
            tempBalance = availableMoney;
            tempBalance-=amount;
            availableMoney=tempBalance;
            return tempBalance;
        } finally {
            lock.unlock();
        }
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

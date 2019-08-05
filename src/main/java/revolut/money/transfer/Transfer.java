package revolut.money.transfer;

import revolut.money.transfer.service.User;

public class Transfer {
    private double amount;
    private User source;
    private User destination;

    public Transfer(double amount, User source, User destination) {
        this.amount = amount;
        this.source = source;
        this.destination = destination;
    }

    public Transfer() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getSource() {
        return source;
    }

    public void setSource(User source) {
        this.source = source;
    }

    public User getDestination() {
        return destination;
    }

    public void setDestination(User destination) {
        this.destination = destination;
    }
}

package revolut.money.transfer;

public class TransferStatus {
    private String sourceUsername;
    private double sourceBalance;
    private String destinationUsername;
    private double destinationBalance;

    public String getSourceUsername() {
        return sourceUsername;
    }

    public void setSourceUsername(String sourceUsername) {
        this.sourceUsername = sourceUsername;
    }

    public double getSourceBalance() {
        return sourceBalance;
    }

    public void setSourceBalance(double sourceBalance) {
        this.sourceBalance = sourceBalance;
    }

    public String getDestinationUsername() {
        return destinationUsername;
    }

    public void setDestinationUsername(String destinationUsername) {
        this.destinationUsername = destinationUsername;
    }

    public double getDestinationBalance() {
        return destinationBalance;
    }

    public void setDestinationBalance(double destinationBalance) {
        this.destinationBalance = destinationBalance;
    }

    private TransferStatus(Builder builder) {
        sourceUsername = builder.sourceUsername;
        sourceBalance = builder.sourceBalance;
        destinationUsername = builder.destinationUsername;
        destinationBalance = builder.destinationBalance;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String sourceUsername;
        private double sourceBalance;
        private String destinationUsername;
        private double destinationBalance;

        private Builder() {
        }

        public Builder withSourceUsername(String val) {
            sourceUsername = val;
            return this;
        }

        public Builder withSourceBalance(double val) {
            sourceBalance = val;
            return this;
        }

        public Builder withDestinationUsername(String val) {
            destinationUsername = val;
            return this;
        }

        public Builder withDestinationBalance(double val) {
            destinationBalance = val;
            return this;
        }

        public TransferStatus build() {
            return new TransferStatus(this);
        }
    }
}

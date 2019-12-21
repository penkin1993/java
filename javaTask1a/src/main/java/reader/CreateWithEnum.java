package reader;

import reader.classes.*;


public class CreateWithEnum implements CreateWith {
    @Override
    public TradeInterface getData(Transaction transaction) {
        return TradeType.valueOf(transaction.getType()).createTrade(transaction.getPrice());
    }
}

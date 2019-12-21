package reader;

import reader.classes.*;


public class CreateWithIf implements CreateWith {
    @Override
    public TradeInterface getData(Transaction transaction) {
        switch (transaction.getType()) {
            case "BOND":
                System.out.println("Bond(" + transaction.getPrice() + ")");
                return new Bond(transaction.getPrice());
            case "COMMODITY_SPOT":
                System.out.println("CommoditySpot(" + transaction.getPrice() + ")");
                return new CommoditySpot(transaction.getPrice());
            case "FX_SPOT":
                System.out.println("FxSpot(" + transaction.getPrice() + ")");
                return new FxSpot(transaction.getPrice());
            case "IR_SWAP":
                System.out.println("IrSwap(" + transaction.getPrice() + ")");
                return new IrSwap(transaction.getPrice());
            default: {
                throw new IllegalArgumentException("Type is not found");
            }
        }
    }
}


package reader;

import reader.classes.*;


public static class GetTransactionIf{
    TradeInterface transactionType;
    Transaction transaction = getData(filename);

    switch (transaction.getType()){
        case "FX_SPOT":
            transactionType = new FxSpot(transaction.getPrice());
            System.out.println("FxSpot(" + transaction.getPrice() + ")");
            return transactionType;
        case "BOND":
            transactionType = new Bond(transaction.getPrice());
            System.out.println("Bond(" + transaction.getPrice() + ")");
            return transactionType;
        case "COMMODITY_SPOT":
            transactionType = new CommoditySpot(transaction.getPrice());
            System.out.println("CommoditySpot(" + transaction.getPrice() + ")");
            return transactionType;
        case "IR_SWAP":
            System.out.println("IrSwap(" + transaction.getPrice() + ")");
            transactionType = new IrSwap(transaction.getPrice());
            return transactionType;
        default: {
            throw new IllegalArgumentException("Type is not found");
        }
    }
}


public static class GetTransactionIf{
    TradeInterface transactionType;
    Transaction transaction = getData(filename);

    switch (transaction.getType()){
        case "FX_SPOT":
            transactionType = new FxSpot(transaction.getPrice());
            System.out.println("FxSpot(" + transaction.getPrice() + ")");
            return transactionType;
        case "BOND":
            transactionType = new Bond(transaction.getPrice());
            System.out.println("Bond(" + transaction.getPrice() + ")");
            return transactionType;
        case "COMMODITY_SPOT":
            transactionType = new CommoditySpot(transaction.getPrice());
            System.out.println("CommoditySpot(" + transaction.getPrice() + ")");
            return transactionType;
        case "IR_SWAP":
            System.out.println("IrSwap(" + transaction.getPrice() + ")");
            transactionType = new IrSwap(transaction.getPrice());
            return transactionType;
        default: {
            throw new IllegalArgumentException("Type is not found");
        }
    }
}

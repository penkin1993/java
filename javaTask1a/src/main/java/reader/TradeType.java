package reader;

import reader.classes.*;

public enum TradeType {
    BOND {
        public TradeInterface createTrade(float price) {
            System.out.println("Bond(" + price + ")");
            return new Bond(price);
        }
    },
    COMMODITY_SPOT {
        public TradeInterface createTrade(float price) {
            System.out.println("CommoditySpot(" + price + ")");
            return new CommoditySpot(price);
        }
    },
    FX_SPOT {
        public TradeInterface createTrade(float price) {
            System.out.println("FxSpot(" + price + ")");
            return new FxSpot(price);
        }
    },
    IR_SWAP {
        public TradeInterface createTrade(float price) {
            System.out.println("IrSwap(" + price + ")");
            return new IrSwap(price);
        }
    };
    public abstract TradeInterface createTrade(float price);
}

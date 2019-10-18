package reader.classes;

public enum TradeType {
    BOND {
        public TradeInterface createTrade(float price) {
            return new Bond(price);
        }
    },
    COMMODITY_SPOT {
        public TradeInterface createTrade(float price) {
            return new CommoditySpot(price);
        }
    },
    FX_SPOT {
        public TradeInterface createTrade(float price) {
            return new FxSpot(price);
        }
    },
    IR_SWAP {
        public TradeInterface createTrade(float price) {
            return new IrSwap(price);
        }
    };
    public abstract TradeInterface createTrade(float price);
}


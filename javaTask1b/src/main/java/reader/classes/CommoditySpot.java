package reader.classes;

public class CommoditySpot implements TradeInterface{
    private final float priceValue;

    public CommoditySpot(float priceValue){
        this.priceValue = priceValue;
    }

    public float getPriceValue() {
        return priceValue;
    }
}

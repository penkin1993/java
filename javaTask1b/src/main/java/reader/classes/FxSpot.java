package reader.classes;

public class FxSpot implements TradeInterface{
    private final float priceValue;

    public FxSpot(float priceValue){
        this.priceValue = priceValue;
    }

    public float getPriceValue() {
        return priceValue;
    }
}
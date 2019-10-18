package reader.classes;

public class Bond implements TradeInterface{
    private final float priceValue;

    public Bond(float priceValue){
        this.priceValue = priceValue;
    }

    public float getPriceValue() {
        return priceValue;
    }
}

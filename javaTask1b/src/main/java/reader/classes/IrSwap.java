package reader.classes;

public class IrSwap implements TradeInterface{
    private final float priceValue;

    public IrSwap(float priceValue){
        this.priceValue = priceValue;
    }

    public float getPriceValue() {
        return priceValue;
    }
}
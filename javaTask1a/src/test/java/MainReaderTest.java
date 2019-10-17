import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.junit.Test;
import reader.MainReader;
import static org.junit.Assert.*;
import reader.classes.*;

public class MainReaderTest {
    private TradeInterface bond = MainReader.getInstanceTransaction("./trades/BOND.txt");
    private TradeInterface commoditySpot = MainReader.getInstanceTransaction("./trades/COMMODITY_SPOT.txt");
    private TradeInterface fxSpot = MainReader.getInstanceTransaction("./trades/FX_SPOT.txt");
    private TradeInterface irSwap = MainReader.getInstanceTransaction("./trades/IR_SWAP.txt");

    @Test
    public void assertEqualPrice(){
        assertEquals(bond.getPriceValue(), 10.20, 0.001);
        assertEquals(commoditySpot.getPriceValue(), 14.20, 0.001);
        assertEquals(fxSpot.getPriceValue(), 100.230, 0.001);
        assertEquals(irSwap.getPriceValue(), 72.22, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void assertNotInstance(){
        MainReader.getInstanceTransaction("./trades/INVALID.txt");
    }

    @Test(expected = ValueException.class)
    public void assertCorrectInstance(){
        MainReader.getInstanceTransaction("./trades/VALUE_INVALID.txt");
    }


}
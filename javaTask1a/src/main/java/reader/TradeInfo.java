package reader;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import reader.classes.TradeInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TradeInfo {
    public static TradeInterface getInstanceTransaction(String filename){
        CreateWith tradeDtype;
        Transaction transaction = getData(filename);
        tradeDtype = new CreateWithEnum();
        return tradeDtype.getData(transaction);
    }

    private static Transaction getData(String fileName){
        BufferedReader reader;
        String substr;
        Pattern PATTERN = Pattern.compile("\\{(.*?)\\}");
        float price = -1;
        String type = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                Matcher m = PATTERN.matcher(line);
                if (m.find()){
                    substr = m.group(0);
                    substr = substr.substring(1, substr.length() - 1);
                    if(line.contains("price")){
                        price = Float.parseFloat(substr);
                    }
                    if(line.contains("type")){
                        type = substr;
                    }
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if ((type == null) || (price == -1)){
            throw new ValueException("Type or price are not define");
        }
        else{
            return new Transaction(price, type);
        }
    }
}

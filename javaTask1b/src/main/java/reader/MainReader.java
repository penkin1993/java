package reader;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import reader.classes.*;
//import java.util.StringTokenizer;

import java.util.regex.*;
import java.io.*;


class Transaction {
    private float price;
    private String type;

    Transaction(float price, String type){
        this.price = price;
        this.type = type;
    }

    float getPrice() {
        return this.price;
    }

    String getType() {
        return this.type;
    }
}


public class MainReader {

    public static void main(String[] args){ //throws IOException{
        Object transactionType;
        String filename = args[0];
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(in.readLine());
        //String filename = st.nextToken();
        transactionType = getInstanceTransaction(filename);
    }

    public static TradeInterface getInstanceTransaction(String filename){ // TODO  в отдельный класс, // наследуемый от отдного интерфйса
        // TradeInterface transactionType;
        Transaction transaction = getData(filename);
        return TradeType.valueOf(transaction.getType()).createTrade(transaction.getPrice());
    }

    private static Transaction getData(String fileName){
        BufferedReader reader;
        String substr;
        Pattern pattern = Pattern.compile("\\{(.*?)\\}");
        float price = -1;
        String type = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            try {
                String line = reader.readLine();
                while (line != null) {
                    Matcher m = pattern.matcher(line);
                    if (m.find()) {
                        substr = m.group(0);
                        substr = substr.substring(1, substr.length() - 1);
                        if (line.contains("price")) {
                            price = Float.parseFloat(substr);
                        }
                        if (line.contains("type")) {
                            type = substr;
                        }
                    }
                    line = reader.readLine();
                } // TODO finally close // line = reader.readLine();
            }
            finally {
                reader.close();
            }
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

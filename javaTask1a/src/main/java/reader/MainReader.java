package reader;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import reader.classes.*;

import java.util.StringTokenizer;

import java.util.regex.*;
import java.io.*;


class Transaction {
    private float price;
    private String type;

    Transaction(float price, String type) {
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

    public static void main(String[] args) throws IOException {
        Object transactionType;
        //String filename = args[0];
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        String filename = st.nextToken();
        transactionType = TradeInfo.getInstanceTransaction(filename);
    }

}

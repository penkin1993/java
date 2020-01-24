package project;

import project.Cache;
import project.CashType;

import java.util.Date;
import java.util.List;


interface Service { 
    @Cache(cacheType = CashType.FILE, fileNamePrefix = "data", zip = true, identityBy = {String.class, double.class}) 
    List<String> run(String item, double value, Date date);  

    @Cache(cacheType = CashType.IN_MEMORY, listList = 100_000) 
    List<String> work(String item); 
}


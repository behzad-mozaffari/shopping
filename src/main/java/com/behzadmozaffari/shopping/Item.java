package com.behzadmozaffari.shopping;

import java.math.BigDecimal;

//To keep things simple the item types and their prices is hardcoded here. This can be moved to a file
//or and external config source to make adding new types easier
public enum Item {
    ipd("Super iPad",  new BigDecimal(549.99)),
    mbp("MacBook Pro", new BigDecimal(1399.99)),
    atv("Apple TV", new BigDecimal(1399.99)),
    vga("VGA adapter", new BigDecimal(30.00));


    private final String name;
    private final BigDecimal price;

    Item(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}

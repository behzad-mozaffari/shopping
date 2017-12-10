package com.behzadmozaffari.shopping;

import java.math.BigDecimal;

//To keep things simple the item types and their prices is hardcoded here. This can be moved to a file
//or and external config source to make adding new types easier
public enum Item {
    ipd("Super iPad",  BigDecimal.valueOf(54999, 2)),
    mbp("MacBook Pro", BigDecimal.valueOf(139999, 2)),
    atv("Apple TV", BigDecimal.valueOf(10950, 2)),
    vga("VGA adapter", BigDecimal.valueOf(3000, 2));


    private final String name;
    private final BigDecimal price;

    Item(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}

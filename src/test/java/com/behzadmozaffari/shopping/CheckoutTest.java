package com.behzadmozaffari.shopping;

import org.junit.Test;

import java.util.ArrayList;

public class CheckoutTest {
    @Test
    public void shouldNotFailWithNoRules() {
        Checkout co = new Checkout(new ArrayList<>());
        co.scan(Item.ipd);
        co.total();
    }

}
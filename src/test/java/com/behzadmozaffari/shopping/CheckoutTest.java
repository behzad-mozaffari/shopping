package com.behzadmozaffari.shopping;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CheckoutTest {
    @Test
    public void shouldNotFailWithNoRules() {
        Checkout co = new Checkout(new ArrayList<>());
        co.scan(Item.ipd);
        co.total();
    }

    @Test
    public void shouldReturnZeroWhenNothingScanned() {
        Checkout co = new Checkout(new ArrayList<>());
        assertThat(co.total(), is(BigDecimal.ZERO));
    }


}
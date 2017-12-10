package com.behzadmozaffari.shopping;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
        assertThat(formatMoney(co.total()), is("$0.00"));
    }


    @Test
    public void shouldReturnSumOfThePricesWithNoRules() {
        Checkout co = new Checkout(new ArrayList<>());
        co.scan(Item.ipd);
        co.scan(Item.atv);
        co.scan(Item.mbp);
        assertThat(formatMoney(co.total()), is("$3349.97"));
    }

    //Matchers didn't work very well with BigDecimal.
    private String formatMoney(BigDecimal value) {
        return new DecimalFormat("$0.00").format(value);
    }

}
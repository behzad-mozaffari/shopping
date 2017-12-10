package com.behzadmozaffari.shopping.pricingRules;

import com.behzadmozaffari.shopping.Item;
import org.junit.Test;

import java.math.BigDecimal;

import static com.behzadmozaffari.shopping.Item.ipd;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class TakeOneForFreeTest {
    @Test
    public void shouldReturnZeroIfItemIsNotScanned() {
        TakeOneForFree rule = new TakeOneForFree(ipd, 3);
        assertThat(rule.getDiscount(), is(BigDecimal.valueOf(0, 2)));
    }

    @Test
    public void shouldIgnoreOtherItems() {
        TakeOneForFree rule = new TakeOneForFree(ipd, 3);
        rule.scan(Item.atv);
        rule.scan(Item.vga);
        assertThat(rule.getDiscount(), is(BigDecimal.valueOf(0, 2)));
    }

    @Test
    public void shouldReturnZeroIfNotEnoughItemsScanned() {
        TakeOneForFree rule = new TakeOneForFree(ipd, 3);
        rule.scan(ipd);
        rule.scan(ipd);
        assertThat(rule.getDiscount(), is(BigDecimal.valueOf(0, 2)));
    }

    @Test
    public void shouldDiscountOneOutOfTheCount() {
        TakeOneForFree rule = new TakeOneForFree(ipd, 3);
        for (int i = 0; i < 7; i++) {
            rule.scan(ipd);
        }
        assertThat(rule.getDiscount(), is(ipd.getPrice().multiply(BigDecimal.valueOf(2))));
    }

}
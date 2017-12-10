package com.behzadmozaffari.shopping.PricingRules;

import org.junit.Test;

import java.math.BigDecimal;

import static com.behzadmozaffari.shopping.Item.ipd;
import static com.behzadmozaffari.shopping.Item.vga;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BundleDiscountTest {
    @Test
    public void shouldReturnZeroIfNothingIsScanned() {
        BundleDiscount rule = new BundleDiscount(ipd, vga);
        assertThat(rule.getDiscount(), is(BigDecimal.valueOf(0, 2)));
    }

    @Test
    public void shouldReturnZeroIfNoPaidItemScanned() {
        BundleDiscount rule = new BundleDiscount(ipd, vga);
        rule.scan(vga);
        rule.scan(vga);
        rule.scan(vga);
        assertThat(rule.getDiscount(), is(BigDecimal.valueOf(0, 2)));
    }

    @Test
    public void shouldReturnZeroIfNoBundledItemIsScanned() {
        BundleDiscount rule = new BundleDiscount(ipd, vga);
        rule.scan(ipd);
        rule.scan(ipd);
        rule.scan(ipd);
        assertThat(rule.getDiscount(), is(BigDecimal.valueOf(0, 2)));
    }

    @Test
    public void shouldDiscountBundledItemIfEnoughPaidIsScanned() {
        BundleDiscount rule = new BundleDiscount(ipd, vga);
        rule.scan(ipd);
        rule.scan(ipd);
        rule.scan(ipd);
        rule.scan(vga);
        rule.scan(vga);
        assertThat(rule.getDiscount(), is(vga.getPrice().multiply(BigDecimal.valueOf(2))));
    }

    @Test
    public void shouldDiscountOneBundlePerPaid() {
        BundleDiscount rule = new BundleDiscount(ipd, vga);
        rule.scan(ipd);
        rule.scan(ipd);
        rule.scan(vga);
        rule.scan(vga);
        rule.scan(vga);
        assertThat(rule.getDiscount(), is(vga.getPrice().multiply(BigDecimal.valueOf(2))));
    }

    @Test(expected = InvalidBundleItems.class)
    public void shouldRejectTheSameTypesAsBundle() {
        new BundleDiscount(ipd, ipd);
    }


}
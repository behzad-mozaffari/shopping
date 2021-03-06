package com.behzadmozaffari.shopping.pricingRules;

import org.junit.Test;

import java.math.BigDecimal;

import static com.behzadmozaffari.shopping.Item.ipd;
import static com.behzadmozaffari.shopping.Item.mbp;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BulkDiscountTest {
    @Test
    public void shouldReturnZeroIfNothingScanned() {
        BulkDiscount rule = new BulkDiscount(ipd, 1, BigDecimal.valueOf(1));
        BigDecimal discount = rule.getDiscount();
        assertThat(discount, is(BigDecimal.valueOf(0, 2)));
    }

    @Test
    public void shouldNotCountOtherItemTypes() {
        BulkDiscount rule = new BulkDiscount(ipd, 1, BigDecimal.valueOf(1));
        rule.scan(mbp);
        rule.scan(mbp);
        rule.scan(mbp);
        BigDecimal discount = rule.getDiscount();
        assertThat(discount, is(BigDecimal.valueOf(0, 2)));
    }

    @Test
    public void shouldReturnSumOfDiscountForAllItems() {
        BulkDiscount rule = new BulkDiscount(ipd, 3, ipd.getPrice().subtract(BigDecimal.valueOf(10)));
        rule.scan(ipd);
        rule.scan(ipd);
        rule.scan(ipd);
        rule.scan(ipd);
        rule.scan(ipd);
        BigDecimal discount = rule.getDiscount();
        assertThat(discount, is(BigDecimal.valueOf(1000, 2).multiply(BigDecimal.valueOf(5))));
    }

    @Test
    public void shouldOnlyDiscountIfNotLessThanMinimumItemsAreScanned() {
        BulkDiscount rule = new BulkDiscount(ipd, 5, BigDecimal.valueOf(10));
        rule.scan(ipd);
        rule.scan(ipd);
        BigDecimal discount = rule.getDiscount();
        assertThat(discount, is(BigDecimal.valueOf(0, 2)));
    }

    @Test(expected = com.behzadmozaffari.shopping.PricingRules.InvalidDiscountedPrice.class)
    public void shouldRaiseExceptionIfAfterDiscountValueIsNegative() {
        new BulkDiscount(ipd, 5, BigDecimal.valueOf(-1));
    }

    @Test(expected = com.behzadmozaffari.shopping.PricingRules.InvalidDiscountedPrice.class)
    public void shouldRaiseExceptionIfAfterDiscountValueIsGreaterThanOriginalPrice() {
        new BulkDiscount(ipd, 5, ipd.getPrice().add(BigDecimal.ONE));
    }
}
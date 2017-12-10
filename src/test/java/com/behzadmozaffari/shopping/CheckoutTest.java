package com.behzadmozaffari.shopping;

import com.behzadmozaffari.shopping.PricingRules.BulkDiscount;
import com.behzadmozaffari.shopping.PricingRules.PricingRule;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.behzadmozaffari.shopping.Item.atv;
import static com.behzadmozaffari.shopping.Item.ipd;
import static com.behzadmozaffari.shopping.Item.mbp;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CheckoutTest {
    @Test
    public void shouldNotFailWithNoRules() {
        Checkout co = new Checkout(new ArrayList<>());
        co.scan(ipd);
        co.total();
    }

    @Test
    public void shouldReturnZeroWhenNothingScanned() {
        Checkout co = new Checkout(new ArrayList<>());
        assertThat(co.total(), is(BigDecimal.valueOf(0, 2)));
    }

    @Test
    public void shouldReturnSumOfThePricesWithNoRules() {
        Checkout co = new Checkout(new ArrayList<>());
        scanAll(co ,ipd, atv, mbp);
        assertThat(co.total(), is(BigDecimal.valueOf(205948, 2)));
    }

    @Test
    public void shouldCalculateBulkDiscount() {
        List<PricingRule> rules = Collections.singletonList(
                new BulkDiscount(ipd, 5, BigDecimal.valueOf(49999, 2)));
        Checkout co = new Checkout(rules);
        scanAll(co, atv, ipd, ipd, atv, ipd, ipd, ipd);
        assertThat(co.total(), is(BigDecimal.valueOf(271895, 2)));
    }

    private void scanAll(Checkout co, Item ...items) {
        Arrays.asList(items).forEach(co::scan);
    }
}
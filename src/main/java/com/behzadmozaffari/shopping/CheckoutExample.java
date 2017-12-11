package com.behzadmozaffari.shopping;

import com.behzadmozaffari.shopping.pricingRules.BulkDiscount;
import com.behzadmozaffari.shopping.pricingRules.BundleDiscount;
import com.behzadmozaffari.shopping.pricingRules.PricingRule;
import com.behzadmozaffari.shopping.pricingRules.TakeOneForFree;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import static com.behzadmozaffari.shopping.Item.atv;
import static com.behzadmozaffari.shopping.Item.ipd;
import static com.behzadmozaffari.shopping.Item.mbp;
import static com.behzadmozaffari.shopping.Item.vga;

public class CheckoutExample {
    public static void main(String[] args) {
        List<PricingRule> rules = Arrays.asList(
                new TakeOneForFree(atv, 3),
                new BulkDiscount(ipd, 5, BigDecimal.valueOf(49999, 2)),
                new BundleDiscount(mbp, vga)
        );


        Checkout co = new Checkout(rules);

        co.scan(ipd);
        co.scan(ipd);
        co.scan(atv);
        co.scan(vga);

        System.out.println("total : " + new DecimalFormat("$0.00").format(co.total()));
    }
}

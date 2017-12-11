The class `com.behzadmozaffari.shopping.CheckoutExample` shows and example of 
usage as well as the API for setting up the PricingRules.

Three pricing rules are implemented as follows :

* BulkDiscount : takes a parameter of item type, the minimum number of item 
that results in a discount and the price of the item after discount

* BundleDiscount : Takes two item types and discounts one of the second one 
per one scan of the first one

* TakeOneForFree : Takes an item type and the number of purchases of the item 
which makes one of them free

The setting up of the rules could be made prettier by building some kind of builder 
like ```Bundle(itemtype1).withA(itemType2)``` 
or ```WhenMoreThan(n).of(itemType).purchasedSetPriceTo($...)``` but that would 
take too much time

The interface of the Checkout is kept to match the problem description.

The checkout class delegates every scanned item to all of the rules so in theory 
the rules can use the whole list of items for calculations.

The rules are implemented with internal state similar to the Checkout class, this 
makes the rule objects one time use. To make the objects reusable there needs to be 
some kind of cleanup mechanism for Checkout and all the rules. 

The end result of all the rules is implemented as discount value which makes them 
not usable for more complex reward programs (ex. vouchers or customer loyalty points).
To fix this the output can be changed to some kind of policy applying type that can
handle more than discount.

Throughout the code and specially the tests the price values are set as bigDecimals 
with 2 decimal points. This makes those parts of the code less readable but building 
a wrapper for the prices can solve it.
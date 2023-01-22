package com.interview.shoppingbasket;

import java.util.List;

public class RetailPriceCheckoutStep implements CheckoutStep {
    private PricingService pricingService;
    private PromotionsService promotionService;
    private double retailTotal;

    public RetailPriceCheckoutStep(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @Override
    public void execute(CheckoutContext checkoutContext) {
        Basket basket = checkoutContext.getBasket();
        List<Promotion> promotions = checkoutContext.getPromotions();
        retailTotal = 0.0;

        for (BasketItem basketItem: basket.getItems()) {
            int quantity = basketItem.getQuantity();
            double price = pricingService.getPrice(basketItem.getProductCode());
            Promotion promotion = promotions.stream().filter(p -> p.getProductCode().equalsIgnoreCase(basketItem.getProductCode())).findFirst().get();
            price = applyPromotion(promotion, basketItem, price);
            basketItem.setProductRetailPrice(price);
            retailTotal += quantity*price;
        }

        checkoutContext.setRetailPriceTotal(retailTotal);
    }

    public double applyPromotion(Promotion promotion, BasketItem item, double price) {
    	return promotion.applyPromotion(item, price);
    }
}

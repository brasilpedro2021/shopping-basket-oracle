package com.interview.shoppingbasket;

import java.util.List;

public class PromotionRetrievalCheckoutStep implements CheckoutStep {
	
    private PromotionsService promotionService;

    public PromotionRetrievalCheckoutStep(PromotionsService promotionService) {
        this.promotionService = promotionService;
    }

    @Override
    public void execute(CheckoutContext checkoutContext) {
        Basket basket = checkoutContext.getBasket();
        List<Promotion> promotions = promotionService.getPromotions(basket);
        checkoutContext.setPromotions(promotions);
    }

}

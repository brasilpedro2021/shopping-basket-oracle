package com.interview.shoppingbasket;

import lombok.Data;

@Data
public class Promotion {
    
    private String productCode;
    private PromotionType promotionType;
    private int value;
    
    public Double applyPromotion(BasketItem item, Double price) {
    	if (item == null || !this.productCode.equalsIgnoreCase(item.getProductCode())) {
    		return price;
    	}
    	if (this.promotionType == PromotionType.FIXED) {
    		return price - this.value < 0 ? 0.0 : price - this.value;
    	}
    	return price * (100 - value) / 100;
    }
	
}

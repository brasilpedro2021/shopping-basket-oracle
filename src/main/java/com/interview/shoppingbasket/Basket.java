package com.interview.shoppingbasket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Basket {
    private List<BasketItem> items = new ArrayList<>();

    public void add(String productCode, String productName, int quantity) {
        BasketItem basketItem = new BasketItem();
        basketItem.setProductCode(productCode);
        basketItem.setProductName(productName);
        basketItem.setQuantity(quantity);

        items.add(basketItem);
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public void consolidateItems() {
    	if (this.items == null || this.items.size() == 1) {
    		return;
    	}
    	List<BasketItem> consolidatedItems = new ArrayList<>();
    	this.items.stream().forEach(bi -> {
    		if (consolidatedItems.stream().noneMatch(mbi -> bi.getProductCode().equalsIgnoreCase(mbi.getProductCode()))) {
        		List<BasketItem> duplicatedItems = items.stream().filter(cbi -> bi.getProductCode().equalsIgnoreCase(cbi.getProductCode())).collect(Collectors.toList());
        		BasketItem newBasketItem = new BasketItem();
        		newBasketItem.setProductCode(bi.getProductCode());
        		newBasketItem.setProductName(bi.getProductName());
        		newBasketItem.setQuantity(duplicatedItems.stream().reduce(0,(r,biq) -> r.getQuantity() + biq.getQuantity(), Integer::sum));
        		consolidatedItems.add(newBasketItem);
    		}
    	});
    	this.items = consolidatedItems;
    }
}

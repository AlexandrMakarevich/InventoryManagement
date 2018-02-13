package com.builder;

import com.entity.InventoryState;
import com.entity.InventoryStatePk;
import com.entity.Product;

import java.time.LocalDateTime;

public class InventoryStateBuilder {

    private InventoryState inventoryState;
    private InventoryStatePk inventoryStatePk;

    public InventoryStateBuilder() {
        init();
    }

    public void init() {
        inventoryStatePk = new InventoryStatePk();
        inventoryStatePk.setProduct(new Product());
        inventoryState = new InventoryState();
        inventoryState.setInventoryStatePk(inventoryStatePk);
        inventoryState.setQuantity(1);
        inventoryStatePk.setStateDate(LocalDateTime.now().minusDays(1));
    }


    public InventoryStateBuilder withProduct(Product product) {
        inventoryState.getInventoryStatePk().setProduct(product);
        return this;
    }

    public InventoryStateBuilder withQuantity(int quantity) {
        inventoryState.setQuantity(quantity);
        return this;
    }

    public InventoryStateBuilder withDate(LocalDateTime localDateTime) {
        inventoryState.getInventoryStatePk().setStateDate(localDateTime);
        return this;
    }

    public InventoryStateBuilder withInventoryStatePK(InventoryStatePk inventoryStatePk) {
        inventoryState.setInventoryStatePk(inventoryStatePk);
        return this;
    }

    public InventoryState build() {
        return inventoryState;
    }

    public void reset() {
        init();
    }
}

package com.carie.receipt.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Item {

    private static RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    private int id;
    private String name;
    private BigDecimal price;
    private ItemType type;
    private boolean imported;

    public Item(int id, String name, BigDecimal price, ItemType type, boolean imported) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.imported = imported;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price.setScale(2, ROUNDING_MODE);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public boolean isImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }
}

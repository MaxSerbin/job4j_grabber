package ru.job4j.ood.lsp.foodStore.structure;

import java.util.Date;

public class Food {
    private String name;
    private Date expiryDate;
    private Date createDate;
    private int price;
    private int discount;

    public Food(String name, Date expiryDate, Date createDate, int price, int discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getExpDateInPercent(Date inventDate) {
        long pastShelfLifeDays = inventDate.getTime() - createDate.getTime();
        long shelfLifeDays = expiryDate.getTime() - createDate.getTime();
        return Math.round((float) pastShelfLifeDays / shelfLifeDays * 100);
      //  long remainingShelfLife = expiryDate.getTime() - inventDate.getTime();
      //  long shelfLifeDays = expiryDate.getTime() - createDate.getTime();
      //  return Math.round((float) remainingShelfLife/shelfLifeDays * 100);
    }
}

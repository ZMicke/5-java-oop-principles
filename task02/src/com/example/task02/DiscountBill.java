package com.example.task02;

public class DiscountBill extends Bill{
    private long discountPrice;

    public DiscountBill(long discountPrice){
        this.discountPrice = discountPrice;
    }

    public String getDisPrice(){
        return discountPrice + "%";
    }
    @Override
    public long getPrice(){
        return (super.getPrice() - super.getPrice() * (this.discountPrice / 100));
    }
    public long getAbsolutDiscount(){
        return super.getPrice() - getPrice();
    }
}

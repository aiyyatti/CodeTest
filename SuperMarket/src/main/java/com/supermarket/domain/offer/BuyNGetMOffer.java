package com.supermarket.domain.offer;

import com.supermarket.domain.Item;
import com.supermarket.domain.NetBill;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Buy n get m offer.
 */
public class BuyNGetMOffer extends Offer {
    private Item onesLikeThis;
    private Integer n = 0;
    private Integer m = 0;
    private List<Item> items = new LinkedList<>();

    public BuyNGetMOffer(NetBill netBill, Item onesLikeThis, Integer n, Integer m) {
        super(netBill);
        this.onesLikeThis = onesLikeThis;
        this.n = n;
        this.m = m;
    }

    @Override
    public void resetOffer() {
        this.n = 0;
        this.m = 0;
    }

    @Override
    public Boolean matches(Item item) {
        return item.getProductCode() == this.onesLikeThis.getProductCode();
    }

    @Override
    public void apply(Item item) {
        if (matches(item)) {
            items.add(item);
            if (items.size() == n) {
                List<Item> freebees = new ArrayList<>();
                for (int i = 0; i < m; i++) {
                    freebees.add(onesLikeThis.clone());
                }
                getNetBill().addFreebees(freebees);
                resetOffer();
            }
        }
    }
}
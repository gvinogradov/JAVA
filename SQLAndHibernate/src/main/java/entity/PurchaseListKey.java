package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PurchaseListKey implements Serializable {
    private int price;
    private Date subscriptionDate;

    public PurchaseListKey() {
    }

    public PurchaseListKey(int price, Date subscriptionDate) {
        this.price = price;
        this.subscriptionDate = subscriptionDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, subscriptionDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PurchaseListKey pList = (PurchaseListKey) obj;
        return subscriptionDate.equals(pList.subscriptionDate) &&
               price == pList.price;
    }
}

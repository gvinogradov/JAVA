import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Date;

@Embeddable
public class PurchaseListKey implements Serializable {
    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public PurchaseListKey() {
    }

    public PurchaseListKey(int price, Date subscriptionDate) {
        this.price = price;
        this.subscriptionDate = subscriptionDate;
    }

    @Override
    public int hashCode() {
        return subscriptionDate.hashCode() + Integer.hashCode(price);
    }

    @Override
    public boolean equals(Object obj) {
        PurchaseListKey pList = (PurchaseListKey) obj;
        return subscriptionDate.equals(pList.getSubscriptionDate()) &&
               price == pList.getPrice();
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}

package Bean;

/**
 * Created by Administrator on 2015/4/21 0021.
 */
public class Price {

    private String priceName;
    private String priceId;

    public Price() {
    }

    public Price(String priceName, String priceId) {
        this.priceName = priceName;
        this.priceId = priceId;
    }

    public String getPriceName() {
        return priceName;
    }

    public void setPriceName(String priceName) {
        this.priceName = priceName;
    }

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }
}

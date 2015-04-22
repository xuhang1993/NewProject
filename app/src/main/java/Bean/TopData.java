package Bean;

/**
 * Created by Administrator on 2015/4/21 0021.
 */
public class TopData {

    private String sImageUrl;
    private String sLink;


    public TopData() {
    }

    public TopData(String sImageUrl, String sLink) {
        this.sImageUrl = sImageUrl;
        this.sLink = sLink;
    }

    public String getsImageUrl() {
        return sImageUrl;
    }

    public void setsImageUrl(String sImageUrl) {
        this.sImageUrl = sImageUrl;
    }

    public String getsLink() {
        return sLink;
    }

    public void setsLink(String sLink) {
        this.sLink = sLink;
    }
}

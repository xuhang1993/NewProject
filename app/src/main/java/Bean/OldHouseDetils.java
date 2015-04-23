package Bean;

/**
 * Created by Administrator on 2015/4/22 0022.
 */
public class OldHouseDetils {

    private String locPath;
    private String houseInfo;
    private String averPrice;
    private String id;
    private String title;
    private String area;
    private String totalPrice;
    private String orientation;
    private String buildId;
    private String fitment;
    private String floor;
    private String address;
    private String houseType;
    private String buildImgs;
    private String shareUrl;

    public OldHouseDetils() {
    }

    public OldHouseDetils(String locPath, String houseInfo, String averPrice, String id, String title, String area, String totalPrice, String orientation, String buildId, String fitment, String floor, String address, String houseType,String shareUrl) {
        this.locPath = locPath;
        this.houseInfo = houseInfo;
        this.averPrice = averPrice;
        this.id = id;
        this.title = title;
        this.area = area;
        this.totalPrice = totalPrice;
        this.orientation = orientation;
        this.buildId = buildId;
        this.fitment = fitment;
        this.floor = floor;
        this.address = address;
        this.houseType = houseType;
        this.shareUrl=shareUrl;

    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getBuildImgs() {
        return buildImgs;
    }

    public void setBuildImgs(String buildImgs) {
        this.buildImgs = buildImgs;
    }

    public String getHouseInfo() {
        return houseInfo;
    }

    public void setHouseInfo(String houseInfo) {
        this.houseInfo = houseInfo;
    }

    public String getAverPrice() {
        return averPrice;
    }

    public void setAverPrice(String averPrice) {
        this.averPrice = averPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    public String getFitment() {
        return fitment;
    }

    public void setFitment(String fitment) {
        this.fitment = fitment;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getLocPath() {
        return locPath;
    }

    public void setLocPath(String locPath) {
        this.locPath = locPath;
    }
}

package Bean;

/**
 * Created by Administrator on 2015/4/22 0022.
 */
public class OldHouseData {

    private String  id;
    private String  title;
    private String  buildName;
    private String  price;
    private String  areaName;
    private String  buildId;
    private String  name;
    private String  path;


    public OldHouseData() {
    }

    public OldHouseData(String id, String title, String buildName, String price, String areaName, String buildId, String name, String path) {
        this.id = id;
        this.title = title;
        this.buildName = buildName;
        this.price = price;
        this.areaName = areaName;
        this.buildId = buildId;
        this.name = name;
        this.path = path;
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

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


}

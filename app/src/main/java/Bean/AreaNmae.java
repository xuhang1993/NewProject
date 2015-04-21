package Bean;

/**
 * Created by Administrator on 2015/4/21 0021.
 */
public class AreaNmae {

    private String areaName;
    private String areaId;
    private String cityId;


    public AreaNmae() {
    }


    public AreaNmae(String areaName, String areaId, String cityId) {
        this.areaName = areaName;
        this.areaId = areaId;
        this.cityId = cityId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
}

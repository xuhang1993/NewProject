package Bean;

/**
 * Created by Administrator on 2015/4/20 0020.
 */
public class CityName {

    private String sName;

    private String iCodeID;

    public CityName() {
    }

    public CityName(String sName, String iCodeID) {
        this.sName = sName;
        this.iCodeID = iCodeID;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getiCodeID() {
        return iCodeID;
    }

    public void setiCodeID(String iCodeID) {
        this.iCodeID = iCodeID;
    }
}

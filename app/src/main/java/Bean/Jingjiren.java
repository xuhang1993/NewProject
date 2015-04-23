package Bean;

/**
 * Created by Administrator on 2015/4/22 0022.
 */
public class Jingjiren {

    private String agentName;
    private String mobile;
    private String agencyName;
    private String avatar;
    public Jingjiren() {
    }

    public Jingjiren(String agentName, String mobile, String agencyName,String avatar) {
        this.agentName = agentName;
        this.mobile = mobile;
        this.agencyName = agencyName;
        this.avatar=avatar;

    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }
}

package Users;

public class Users {
    private String userid;
    private String password;

    public Users(String userid, String password) {
        this.userid = userid;
        this.password = password;
    }

    public String getUserid() {
        return userid;
    }
}

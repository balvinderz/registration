package register.csi.com.registerforpublicityapp;

public class User {
    String deviceid;
    String email;
    int last_entry;
    int member_id;
    String member_initial;
    String member_name;
    String password;
    int balance;
    int admin;
    public User() {
    }

    public User(String deviceid, String email, int last_entry, int member_id, String member_initial, String member_name, String password,int admin) {
        this.deviceid = deviceid;
        this.email = email;
        this.last_entry = last_entry;
        this.member_id = member_id;
        this.member_initial = member_initial;
        this.member_name = member_name;
        this.password = password;
        this.balance=balance;
        this.admin=admin;
    }

    public String getDeviceid() {
        return deviceid;
    }
    public int getAdmin()
    {
        return  admin;
    }
    public void setAdmin(int admin)
    {
        this.admin=admin;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLast_entry() {
        return last_entry;
    }

    public void setLast_entry(int last_entry) {
        this.last_entry = last_entry;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getMember_initial() {
        return member_initial;
    }

    public void setMember_initial(String member_initial) {
        this.member_initial = member_initial;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

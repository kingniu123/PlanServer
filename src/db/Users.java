package db;


public class Users {
	private String  u_id;
    private String  u_psd;
    private String  u_nickname;
    private String  u_phone;
    private String  u_email;

    public Users(){

    }
    
    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getU_psd() {
        return u_psd;
    }

    public void setU_psd(String u_psd) {
        this.u_psd = u_psd;
    }

    public String getU_nickname() {
        return u_nickname;
    }

    public void setU_nickname(String u_nickname) {
        this.u_nickname = u_nickname;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

}

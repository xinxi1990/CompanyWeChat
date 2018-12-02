package config;

public class TokenConfig {
    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpirestime() {
        return expirestime;
    }

    public void setExpirestime(String expirestime) {
        this.expirestime = expirestime;
    }

    public String access_token;
    public String expirestime;


}

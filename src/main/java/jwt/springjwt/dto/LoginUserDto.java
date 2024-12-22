package jwt.springjwt.dto;

public class LoginUserDto {
    private String email;

    private String password;
    private String token;
    private String expirationDate;


    public LoginUserDto(){};
    public LoginUserDto(String email, String password,String token,String expirationDate) {
        this.email = email;
        this.password = password;
        this.token = token;
        this.expirationDate = expirationDate;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}

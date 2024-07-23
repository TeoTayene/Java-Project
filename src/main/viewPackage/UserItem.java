package main.viewPackage;

public class UserItem {
    private Integer userId;
    private String email;

    public UserItem(Integer userId, String  email) {
        setUserId(userId);
        setEmail(email);
    }

    @Override
    public String toString() {
        return email;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

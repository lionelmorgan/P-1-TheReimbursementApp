package dto;

public class UsersDTO {
    String username;
    Integer user_role_id;


    public UsersDTO() {
    }

    public UsersDTO(String username, Integer user_role_id) {
        this.username = username;
        this.user_role_id = user_role_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUser_role_id() {
        return user_role_id;
    }

    public void setUser_role_id(Integer user_role_id) {
        this.user_role_id = user_role_id;
    }

    @Override
    public String toString() {
        return "UsersDTO{" +
                "username='" + username + '\'' +
                ", user_role_id='" + user_role_id + '\'' +
                '}';
    }
}

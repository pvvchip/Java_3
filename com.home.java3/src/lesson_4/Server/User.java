package lesson_4.Server;

public interface User {
    public String getLogin();
    public String getPassword();
    public String getNickname();
    public boolean isActive();
    public void setActive(boolean active);
}

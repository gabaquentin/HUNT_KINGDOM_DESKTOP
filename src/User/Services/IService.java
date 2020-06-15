package User.Services;

import User.Model.User;

public interface IService<U> {
    public int find(String s);
    public int findEmail(String s);
    public String findRole(String s);
}

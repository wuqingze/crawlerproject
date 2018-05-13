package dao;

import java.util.ArrayList;
import bean.User;

public interface UserADFUL {
	public void add(User user);
	public void  delete(User user);
	public User find(String name);
	public void update(User user);
	public ArrayList<User> list();
}

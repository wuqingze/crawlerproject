package test;

import bean.User;
import daoImp.UserADFULImp;

public class TestUserDelete {

	public static void main(String[] args) {
		UserADFULImp userADFULImp = new UserADFULImp();
		User user = new User();
		user.setUsername("nong");
		userADFULImp.delete(user);
	}

}

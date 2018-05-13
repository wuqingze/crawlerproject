package test;

import bean.User;
import dao.UserADFUL;
import daoImp.UserADFULImp;

public class TestUserFind {

	public static void main(String[] args) {
		UserADFULImp userADFULImp = new UserADFULImp();
		User user = userADFULImp.find("nong");
		System.out.println(user.getUsername()+","+user.getPassword());
	}

}

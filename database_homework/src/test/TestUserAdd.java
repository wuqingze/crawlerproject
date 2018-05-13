package test;

import bean.User;
import daoImp.UserADFULImp;

public class TestUserAdd {

	public static void main(String[] args) {
		UserADFULImp userADFULImp = new UserADFULImp();
		User user = new User();
		user.setUsername("nongxiaolang@foxmail.com");
		user.setPassword("1234");
		user.setAddress("上海市普陀区中山北路3663号，华东师范大学软件学院");
		user.setEmail("nongxiaolang@foxmail.com");
		user.setIsLogin("false");
		userADFULImp.add(user);
		System.out.println(user.getId());
	}

}

package test;

import bean.User;
import daoImp.UserADFULImp;

public class TestUserAdd {

	public static void main(String[] args) {
		UserADFULImp userADFULImp = new UserADFULImp();
		User user = new User();
		user.setUsername("nongxiaolang@foxmail.com");
		user.setPassword("1234");
		user.setAddress("�Ϻ�����������ɽ��·3663�ţ�����ʦ����ѧ���ѧԺ");
		user.setEmail("nongxiaolang@foxmail.com");
		user.setIsLogin("false");
		userADFULImp.add(user);
		System.out.println(user.getId());
	}

}

package bean;

import java.util.ArrayList;

public class User {
	
	private int id;
	private String username;
	private String password;
	private String address;
	private String email;
	private String isLogin;
	private ArrayList<Goods> orders;
	private ArrayList<Goods> shoppingCar;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIsLogin() {
		return isLogin;
	}
	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
	}
	public ArrayList<Goods> getOrders() {
		return orders;
	}
	public void setOrders(ArrayList<Goods> orders) {
		this.orders = orders;
	}
	public ArrayList<Goods> getShoppingCar() {
		return shoppingCar;
	}
	public void setShoppingCar(ArrayList<Goods> shoppingCar) {
		this.shoppingCar = shoppingCar;
	}
	
	
	
	
}

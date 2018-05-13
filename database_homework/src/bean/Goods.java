package bean;

public class Goods {
	 private int id;
	 private String name;
	 private String introduction;
	 private String details;
	 private int count;
	 private double price;
	 private int saleCount;
	 private String pictures;
	 private String category;
	 
	public Goods() {
		super();
	}

	public Goods(String name, String introduction, String details, int count, double price, int saleCount,
			String pictures, String category) {
		super();
		this.name = name;
		this.introduction = introduction;
		this.details = details;
		this.count = count;
		this.price = price;
		this.saleCount = saleCount;
		this.pictures = pictures;
		this.category = category;
	}

	 public int getId() {
			return id;
		}


	public void setId(int id) {
			this.id = id;
		}


	public void setPrice(double price) {
			this.price = price;
		}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getIntroduction() {
		return introduction;
	}


	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}


	public String getDetails() {
		return details;
	}


	public void setDetails(String details) {
		this.details = details;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public int getSaleCount() {
		return saleCount;
	}


	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}


	public String getPictures() {
		return pictures;
	}


	public void setPictures(String pictures) {
		this.pictures = pictures;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}

	
	 
}

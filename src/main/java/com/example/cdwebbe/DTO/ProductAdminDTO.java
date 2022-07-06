package com.example.cdwebbe.DTO;

public class ProductAdminDTO {
	  private String name;
	    private String image;
	    private String description;
	    private double price;
	    private String color;
	    private double price_sale;
	    private int score; // số s
	    private CategoryDTO category;
		public ProductAdminDTO(String name, String image, String description, double price, String color,
				double price_sale, int score, CategoryDTO category) {
			super();
			this.name = name;
			this.image = image;
			this.description = description;
			this.price = price;
			this.color = color;
			this.price_sale = price_sale;
			this.score = score;
			this.category = category;
		}
		public ProductAdminDTO() {
			super();
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public double getPrice_sale() {
			return price_sale;
		}
		public void setPrice_sale(double price_sale) {
			this.price_sale = price_sale;
		}
		public int getScore() {
			return score;
		}
		public void setScore(int score) {
			this.score = score;
		}
		public CategoryDTO getCategory() {
			return category;
		}
		public void setCategory(CategoryDTO category) {
			this.category = category;
		}
	    

}

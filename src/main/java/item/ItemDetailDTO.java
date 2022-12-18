package item;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import time.Time;

public class ItemDetailDTO {
	private Integer id;
	
	private String title;
	
	private String content;
	
	private BigDecimal price;
	
	private String status;
	
	private int view_count;
	
	private int like_count;
	
	private String created_at;
	
	private String updated_at;
	
	private Integer seller_id;
	
	private String seller_nickname;
	
	private Integer category_id;
	
	private String category;
	
	private String address;

	
	public Integer getCategory_id() {
		return category_id;
	}
	
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getView_count() {
		return view_count;
	}

	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	
	public int getLike_count() {
		return like_count;
	}

	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = Time.convertLocalDateTimeToTime(updated_at);
	}
	
	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = Time.convertLocalDateTimeToTime(created_at);
	}


	public Integer getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(Integer seller_id) {
		this.seller_id = seller_id;
	}

	public String getSeller_nickname() {
		return seller_nickname;
	}

	public void setSeller_nickname(String seller_nickname) {
		this.seller_nickname = seller_nickname;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		String[] temp = address.split(" ");
		if(temp[0].equals("세종특별자치시")){
			this.address = temp[0] + " " + temp[1];
		}else{
			this.address = temp[0] + " " + temp[1] + " " + temp[2];
		}
	}
	
	
	
}

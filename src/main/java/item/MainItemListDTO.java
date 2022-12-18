package item;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import time.Time;

public class MainItemListDTO {
	
	private Integer id;
	private Integer member_id;
	private String nickname;
	private String address;
	
	private Integer item_id;
	private String title;
	private BigDecimal price;
	private int view_count;
	private int like_count;
	private String status;
	private String updated_at;
	private String created_at;
	private Integer seller_id;
	
	private Integer file_id;
	private String savedname;
	
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		String[] temp = address.split(" ");
		if(temp[0].equals("세종특별자치시")){
			this.address = temp[0] + " " + temp[1];
		}else{
			this.address = temp[1] + " " + temp[2];
		}
	}
	public Integer getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = Time.convertLocalDateTimeToTime(updated_at);
	}
	public Integer getSeller_id() {
		return seller_id;
	}
	
	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = Time.convertLocalDateTimeToTime(created_at);
	}
	
	public void setSeller_id(Integer seller_id) {
		this.seller_id = seller_id;
	}
	public Integer getFile_id() {
		return file_id;
	}
	public void setFile_id(Integer file_id) {
		this.file_id = file_id;
	}
	public String getSavedname() {
		return savedname;
	}
	public void setSavedname(String savedname) {
		this.savedname = savedname;
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
	
}

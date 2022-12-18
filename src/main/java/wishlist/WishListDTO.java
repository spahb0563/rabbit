package wishlist;

public class WishListDTO {
	private Integer wishlist_id; //id
	private Integer member_id; // member_id
	private Integer item_id; // item_id
	private int like_count; // like_count
	
	public Integer getWishlist_id() {
		return wishlist_id;
	}
	public void setWishlist_id(Integer wishlist_id) {
		this.wishlist_id = wishlist_id;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	public Integer getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
	public int getLike_count() {
		return like_count;
	}
	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}
	
}

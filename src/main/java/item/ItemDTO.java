package item;
import java.math.BigDecimal;
import java.time.LocalDateTime;
public class ItemDTO {
	/*	-- Table `item`  db테이블내용
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS .`item` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(45) NOT NULL,
    `content` VARCHAR(45) NOT NULL,
    `price` DECIMAL NOT NULL,
    `status` VARCHAR(45) NOT NULL,
    `view_count` INT NOT NULL,
    `like_count` INT NOT NULL,
    `created_at` DATETIME NOT NULL,
    `updated_at` DATETIME NULL,
    `category_id` BIGINT NOT NULL,
    `seller_id` BIGINT NOT NULL,
    `buyer_id` BIGINT NULL,

    PRIMARY KEY (`id`))*/
	private Integer id;
	private String title;
	private String content;
	private BigDecimal price;
	private String status;
	
	private int view_count;
	private int like_count;
	
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	
	private Integer category_id;
	private Integer seller_id;
	private Integer buyer_id;
	
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
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	public LocalDateTime getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
	public Integer getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(Integer seller_id) {
		this.seller_id = seller_id;
	}
	public Integer getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(Integer buyer_id) {
		this.buyer_id = buyer_id;
	}
	
}

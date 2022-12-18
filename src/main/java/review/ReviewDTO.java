package review;

import java.time.LocalDateTime;

public class ReviewDTO {
	private Integer id;
	private String content;
	private LocalDateTime created_at;
	private Integer target_id;
	private Integer writer_id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	public Integer getTarget_id() {
		return target_id;
	}
	public void setTarget_id(Integer target_id) {
		this.target_id = target_id;
	}
	public Integer getWriter_id() {
		return writer_id;
	}
	public void setWriter_id(Integer writer_id) {
		this.writer_id = writer_id;
	}
	
	
	
	
}//class-end

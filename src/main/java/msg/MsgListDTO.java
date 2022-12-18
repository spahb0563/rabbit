package msg;

import java.time.LocalDateTime;

public class MsgListDTO {
	
	private Integer id;
	
	private String content;
	
	private int status;
	
	private Integer sender_id;
	
	private LocalDateTime created_at;
	
	private String sender_nickname;
	
	private String receiver_nickname;
	
	private Integer item_id;

	private String item_title;
	
	public String getItem_title() {
		return item_title;
	}

	public void setItem_title(String item_title) {
		this.item_title = item_title;
	}
	
	public String getReceiver_nickname() {
		return receiver_nickname;
	}

	public void setReceiver_nickname(String receiver_nickname) {
		this.receiver_nickname = receiver_nickname;
	}

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Integer getSender_id() {
		return sender_id;
	}

	public void setSender_id(Integer sender_id) {
		this.sender_id = sender_id;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public String getSender_nickname() {
		return sender_nickname;
	}

	public void setSender_nickname(String sender_nickname) {
		this.sender_nickname = sender_nickname;
	}
	
	
	
	
}

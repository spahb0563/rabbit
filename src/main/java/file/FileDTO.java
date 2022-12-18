package file;

import java.time.LocalDateTime;

public class FileDTO {
	
	private Integer id;
	
	private String name;
	
	private String savedname;
	
	private String path;
	
	private Long size;
	
	private String type;
	
	private LocalDateTime created_at;
	
	private Integer item_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSavedname() {
		return savedname;
	}

	public void setSavedname(String savedname) {
		this.savedname = savedname;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
	
	
	
}

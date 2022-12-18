package address;

import java.util.List;

public class AddressDTO {
	private Integer id;
	
	private String city;
	
	private List<String> districtList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<String> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<String> districtList) {
		this.districtList = districtList;
	}
	
	
	
}

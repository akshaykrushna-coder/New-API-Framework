package resourse;

public enum API_Resources {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json"),
	UpdatePlaceAPI("/maps/api/place/update/json");
	
	String resource;
	API_Resources(String resource){
		this.resource=resource;
	}
	
	public String getResource(){
		return resource;
	}
	
}

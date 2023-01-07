package resourse;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String name, String langauge, String address){
		AddPlace m = new AddPlace();
		m.setAccuracy(50);
		m.setAddress(address);
		m.setLangauge(langauge);
		m.setPhone_number("955240187");
		m.setName(name);
		m.setWebsite("www.akshay.com");

		List<String> myList = new ArrayList<String>();
		myList.add("shooe park");
		myList.add("shop");

		m.setTypes(myList);

		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);

		m.setLocation(l);
		return m;
	}
	public String deletePlacePayload(String placeId)
	{
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}

}

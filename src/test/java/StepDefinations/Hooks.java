package StepDefinations;

import cucumber.api.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws Throwable 
	{		//execute this code only when place id is null
		//write a code that will give you place id
		
		StepDefination m =new StepDefination();
		if(StepDefination.place_Id==null)
		{
		
		m.add_Place_Payload_with("Shetty", "French", "Asia");
		m.user_call_API_using_http_request("AddPlaceAPI","POST");
		m.verify_place_id_creating_map_to_using("Shetty", "GetPlaceAPI");
		}
		
		

	}

}

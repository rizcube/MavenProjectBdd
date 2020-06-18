package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayLoad(String name ,String language, String address) 
	{
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setName(name);
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress(address);
		p.setWebsite("https://www.google.com");
		p.setLanguage(language);
		
		
	// types is a list so we have to create a list object first
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		
		Location l= new Location();
		l.setLng(-38.383494);
		l.setLng(33.427362);
		
		p.setLocation(l);
		return p;
	}

}

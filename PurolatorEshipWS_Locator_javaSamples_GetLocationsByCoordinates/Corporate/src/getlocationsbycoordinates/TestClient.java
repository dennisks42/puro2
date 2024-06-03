package getlocationsbycoordinates;

import java.io.Console;

public class TestClient {
	public static void main(String[] args)
    {
        LocatorTestClient.TestClient returnsLocatorClient = new LocatorTestClient.TestClient();
       
        /*Search Criterias :Address,Coordinates,City,ID,PointOfInterest,PostalCode*/

        /*Call Get Location By Addrss*/
        returnsLocatorClient.CallGetLocations(LocatorTestClient.TestClient.LOCATION_BY_COORDINATES);

        /*Call Get Location By Coordinates*/
       // returnsLocatorClient.CallGetLocations(LocatorTestClient.TestClient.LOCATION_BY_COORDINATES); 

        /*Call Get Location By City*/
        //returnsLocatorClient.CallGetLocations(LocatorTestClient.TestClient.LOCATION_BY_CITY); 

        /*Call Get Location By ID*/
       // returnsLocatorClient.CallGetLocations(LocatorTestClient.TestClient.LOCATION_BY_ID); 

        /*Call Get Location By PointOfInterest*/
       // returnsLocatorClient.CallGetLocations(LocatorTestClient.TestClient.LOCATION_BY_POINTOFINTEREST); 

        /*Call Get Location By PostalCode*/
       // returnsLocatorClient.CallGetLocations(LocatorTestClient.TestClient.LOCATION_BY_POSTALCODE); 

        Console.ReadLine();
    }
}

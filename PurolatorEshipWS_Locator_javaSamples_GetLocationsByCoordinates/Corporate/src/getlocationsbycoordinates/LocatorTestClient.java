package getlocationsbycoordinates;

import project.ArrayOfDocumentCriteria;
import project.DocumentCriteria;
import project.DocumentTypes;
import project.GetDocumentsRequest;
import project.GetDocumentsRequestContainer;
import project.GetDocumentsResponse;
import project.GetDocumentsResponseContainer;
import project.InformationalMessage;
import project.PIN;
import project.RequestContextE;
import project.LocatorServiceStub;
import project.ShippingDocumentsServiceStub;
import pws.client.LocatorService.LocatorServiceStub.RequestContext;
import pws.client.LocatorService.LocatorServiceStub.RequestContext;
import pws.client.LocatorService.LocatorServiceStub.ResponseInformation;
import pws.client.LocatorService.LocatorServiceStub.GetLocationsRequestContainer;
import pws.client.LocatorService.LocatorServiceStub.GetLocationsRequest;
import pws.client.LocatorService.LocatorServiceStub.GetLocationsResponseContainer;
import pws.client.LocatorService.LocatorServiceStub.GetLocationsResponse;






import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;

public class LocatorTestClient {
	
	 // Constants for Locator services - Unifying the locations
    public static String LOCATION_BY_ADDRESS = "ADDRESS";
    public static String LOCATION_BY_CITY = "CITY";
    public static String LOCATION_BY_COORDINATES = "COORDINATES";
    public static String LOCATION_BY_ID = "ID";
    public static String LOCATION_BY_POINTOFINTEREST = "POINTOFINTEREST";
    public static String LOCATION_BY_POSTALCODE = "POSTALCODE";

    // Adding Location type for Locator service
    public enum LocationType { Staples, ShippingCentre, ShippingAgent, DropBox }
    // Adding DaysofWeek for locator service
    public enum DaysOfWeek { Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday }
	
	public static void main(java.lang.String args[]){
        try{
            LocatorServiceStub stub =
                new LocatorServiceStub 			
	("https://devwebservices.purolator.com/EWS/V1/Locator/LocatorService.asmx");

    		

		Options options = stub._getServiceClient().getOptions();
	        HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setUsername("your key here");
            	auth.setPassword("your password here");			            	
		
            	options.setProperty(HTTPConstants.AUTHENTICATE,auth);


		RequestContextE requestContext = new RequestContextE();
		RequestContext requestContextBody = new RequestContext();
		requestContextBody.setVersion("1.0");
		requestContextBody.setLanguage(Language.en);
		requestContextBody.setGroupID("");
		requestContextBody.setRequestReference("RequestReference");
		requestContextBody.setUserToken("Your User Token");
		requestContext.setRequestContext(requestContextBody);

		//Test Get locations
		CallGetLocations(stub,requestContext);
		
		
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("\n\n\n");
        }
    }
	
	public static void CallGetLocations(string SearchCriteria)
	{
		 LocatorProxy.LocatorService service = CreateLocatorService();
         LocatorProxy.GetLocationsResponseContainer response = new GetLocationsResponseContainer();
		
		try
        {        	
            switch (SearchCriteria)
            {
                case LOCATION_BY_ADDRESS:

                    // Call the GetLocationsByAddress
                    LocatorProxy.GetLocationsByAddressRequestContainer AddressRequest = TranslateAddressRequestContainer();
                    response = service.GetLocationsByAddress(AddressRequest);
                    break;
                case LOCATION_BY_COORDINATES:
                    // Call the GetLocationsByCoordinates
                    LocatorProxy.GetLocationsByCoordinatesRequestContainer CoordinatesRequest = TranslateCoordinatesRequestContainer();
                    response = service.GetLocationsByCoordinates(CoordinatesRequest);
                    break;
                case LOCATION_BY_CITY:
                    // Call the GetLocationsByCity
                    LocatorProxy.GetLocationsByCityRequestContainer CityRequest = TranslateCityRequestContainer();
                    response = service.GetLocationsByCity(CityRequest);
                    break;
                case LOCATION_BY_ID:
                    // Call the GetLocationsByID
                    LocatorProxy.GetLocationsByIDRequestContainer IDRequest = TranslateIDRequestContainer();
                    response = service.GetLocationsByID(IDRequest);
                    break;
                case LOCATION_BY_POINTOFINTEREST:
                    // Call the GetLocationsByPointOfInterest
                    LocatorProxy.GetLocationsByPointOfInterestRequestContainer PointOfInterestRequest = TranslatePointOfInterestRequestContainer();
                    response = service.GetLocationsByPointOfInterest(PointOfInterestRequest);
                    break;
                case LOCATION_BY_POSTALCODE:
                    // Call the GetLocationsByPostalCode
                    LocatorProxy.GetLocationsByPostalCodeRequestContainer PostalCodeRequest = TranslatePostalCodeRequestContainer();
                    response = service.GetLocationsByPostalCode(PostalCodeRequest);
                    break;
            }
            Display(response.ResponseInformation);
            TranslateDisplayLocatorInformation(response.Locations.ToList());
		
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("\n\n\n");
        }
    }
	
	
	 private static LocatorProxy.GetLocationsByAddressRequestContainer TranslateAddressRequestContainer()
     {
         LocatorProxy.GetLocationsByAddressRequestContainer ToProxy = new LocatorProxy.GetLocationsByAddressRequestContainer();
         Address ToAddress = new Address();

         // Set Address
         ToAddress.AddressLine1 = "1881";
         ToAddress.AddressLine2 = "Fairview";
         ToAddress.AddressLine3 = "";
         ToProxy.Address = ToAddress;

         // Set Serach options
         ToProxy.SearchOptions = GetSerachOption();

         // Set Hours Of Operation
         ToProxy.HoursOfOperation = GetHours();

         // Set LocationTypes
         ToProxy.LocationTypes = new string[4];
         ToProxy.LocationTypes[0] = LocationType.Staples.ToString();
         ToProxy.LocationTypes[1] = LocationType.ShippingCentre.ToString();
         ToProxy.LocationTypes[2] = LocationType.ShippingAgent.ToString();
         ToProxy.LocationTypes[3] = LocationType.DropBox.ToString();

         // Set DaysOfOperation
         ToProxy.DaysOfOperation = new string[7];
         ToProxy.DaysOfOperation[0] = DaysOfWeek.Monday.ToString();
         ToProxy.DaysOfOperation[1] = DaysOfWeek.Tuesday.ToString();
         ToProxy.DaysOfOperation[2] = DaysOfWeek.Wednesday.ToString();
         ToProxy.DaysOfOperation[3] = DaysOfWeek.Thursday.ToString();
         ToProxy.DaysOfOperation[4] = DaysOfWeek.Friday.ToString();
         ToProxy.DaysOfOperation[5] = DaysOfWeek.Saturday.ToString();
         ToProxy.DaysOfOperation[6] = DaysOfWeek.Sunday.ToString();


         return ToProxy;
     }
	 
	 private static LocatorProxy.GetLocationsByCityRequestContainer TranslateCityRequestContainer()
     {
         LocatorProxy.GetLocationsByCityRequestContainer ToProxy = new LocatorProxy.GetLocationsByCityRequestContainer();
          
         // Set City
         ToProxy.CityName = "Mississauga";
         ToProxy.ProvinceStateCode = "ON";
         ToProxy.CountryCode = "CA";

         // Set Serach options
         ToProxy.SearchOptions = GetSerachOption();

         // Set Hours Of Operation
         ToProxy.HoursOfOperation = GetHours();

         // Set LocationTypes
         ToProxy.LocationTypes = new string[4];
         ToProxy.LocationTypes[0] = LocationType.Staples.ToString();
         ToProxy.LocationTypes[1] = LocationType.ShippingCentre.ToString();
         ToProxy.LocationTypes[2] = LocationType.ShippingAgent.ToString();
         ToProxy.LocationTypes[3] = LocationType.DropBox.ToString();

         // Set DaysOfOperation
         ToProxy.DaysOfOperation = new string[7];
         ToProxy.DaysOfOperation[0] = DaysOfWeek.Monday.ToString();
         ToProxy.DaysOfOperation[1] = DaysOfWeek.Tuesday.ToString();
         ToProxy.DaysOfOperation[2] = DaysOfWeek.Wednesday.ToString();
         ToProxy.DaysOfOperation[3] = DaysOfWeek.Thursday.ToString();
         ToProxy.DaysOfOperation[4] = DaysOfWeek.Friday.ToString();
         ToProxy.DaysOfOperation[5] = DaysOfWeek.Saturday.ToString();
         ToProxy.DaysOfOperation[6] = DaysOfWeek.Sunday.ToString();

         return ToProxy;
     }
	 
     private static LocatorProxy.GetLocationsByCoordinatesRequestContainer TranslateCoordinatesRequestContainer()
     {
         LocatorProxy.GetLocationsByCoordinatesRequestContainer ToProxy = new LocatorProxy.GetLocationsByCoordinatesRequestContainer();
         Coordinates ToCoordinates = new Coordinates();

         // Set Coordinates
         ToCoordinates.Latitude = Convert.ToDecimal("43.6577072000");
         ToCoordinates.Longitude = Convert.ToDecimal("-79.3861553000");
         ToCoordinates.LatitudeSpecified = true;
         ToCoordinates.LongitudeSpecified = true;
         ToProxy.Coordinates = ToCoordinates;

         // Set Serach options
         ToProxy.SearchOptions = GetSerachOption();

         // Set Hours Of Operation
         ToProxy.HoursOfOperation = GetHours();

         // Set LocationTypes
         ToProxy.LocationTypes = new string[4];
         ToProxy.LocationTypes[0] = LocationType.Staples.ToString();
         ToProxy.LocationTypes[1] = LocationType.ShippingCentre.ToString();
         ToProxy.LocationTypes[2] = LocationType.ShippingAgent.ToString();
         ToProxy.LocationTypes[3] = LocationType.DropBox.ToString();

         // Set DaysOfOperation
         ToProxy.DaysOfOperation = new string[7];
         ToProxy.DaysOfOperation[0] = DaysOfWeek.Monday.ToString();
         ToProxy.DaysOfOperation[1] = DaysOfWeek.Tuesday.ToString();
         ToProxy.DaysOfOperation[2] = DaysOfWeek.Wednesday.ToString();
         ToProxy.DaysOfOperation[3] = DaysOfWeek.Thursday.ToString();
         ToProxy.DaysOfOperation[4] = DaysOfWeek.Friday.ToString();
         ToProxy.DaysOfOperation[5] = DaysOfWeek.Saturday.ToString();
         ToProxy.DaysOfOperation[6] = DaysOfWeek.Sunday.ToString();


         return ToProxy;
     }
     
     private static LocatorProxy.GetLocationsByIDRequestContainer TranslateIDRequestContainer()
     {
         LocatorProxy.GetLocationsByIDRequestContainer ToProxy = new LocatorProxy.GetLocationsByIDRequestContainer();
          
         // Set Location ID
         ToProxy.LocationId = "986012";
         
         // Set Serach options
         ToProxy.SearchOptions = GetSerachOption();

         // Set Hours Of Operation
         ToProxy.HoursOfOperation = GetHours();

         // Set LocationTypes
         ToProxy.LocationTypes = new string[4];
         ToProxy.LocationTypes[0] = LocationType.Staples.ToString();
         ToProxy.LocationTypes[1] = LocationType.ShippingCentre.ToString();
         ToProxy.LocationTypes[2] = LocationType.ShippingAgent.ToString();
         ToProxy.LocationTypes[3] = LocationType.DropBox.ToString();

         // Set DaysOfOperation
         ToProxy.DaysOfOperation = new string[7];
         ToProxy.DaysOfOperation[0] = DaysOfWeek.Monday.ToString();
         ToProxy.DaysOfOperation[1] = DaysOfWeek.Tuesday.ToString();
         ToProxy.DaysOfOperation[2] = DaysOfWeek.Wednesday.ToString();
         ToProxy.DaysOfOperation[3] = DaysOfWeek.Thursday.ToString();
         ToProxy.DaysOfOperation[4] = DaysOfWeek.Friday.ToString();
         ToProxy.DaysOfOperation[5] = DaysOfWeek.Saturday.ToString();
         ToProxy.DaysOfOperation[6] = DaysOfWeek.Sunday.ToString();


         return ToProxy;
     }
     
     private static LocatorProxy.GetLocationsByPointOfInterestRequestContainer TranslatePointOfInterestRequestContainer()
     {
         LocatorProxy.GetLocationsByPointOfInterestRequestContainer ToProxy = new LocatorProxy.GetLocationsByPointOfInterestRequestContainer();

         // Set Location ID
         ToProxy.PointOfInterest = "toronto";

         // Set Serach options
         ToProxy.SearchOptions = GetSerachOption();

         // Set Hours Of Operation
         ToProxy.HoursOfOperation = GetHours();

         // Set LocationTypes
         ToProxy.LocationTypes = new string[4];
         ToProxy.LocationTypes[0] = LocationType.Staples.ToString();
         ToProxy.LocationTypes[1] = LocationType.ShippingCentre.ToString();
         ToProxy.LocationTypes[2] = LocationType.ShippingAgent.ToString();
         ToProxy.LocationTypes[3] = LocationType.DropBox.ToString();

         // Set DaysOfOperation
         ToProxy.DaysOfOperation = new string[7];
         ToProxy.DaysOfOperation[0] = DaysOfWeek.Monday.ToString();
         ToProxy.DaysOfOperation[1] = DaysOfWeek.Tuesday.ToString();
         ToProxy.DaysOfOperation[2] = DaysOfWeek.Wednesday.ToString();
         ToProxy.DaysOfOperation[3] = DaysOfWeek.Thursday.ToString();
         ToProxy.DaysOfOperation[4] = DaysOfWeek.Friday.ToString();
         ToProxy.DaysOfOperation[5] = DaysOfWeek.Saturday.ToString();
         ToProxy.DaysOfOperation[6] = DaysOfWeek.Sunday.ToString();


         return ToProxy;
     }
     
     private static LocatorProxy.GetLocationsByPostalCodeRequestContainer TranslatePostalCodeRequestContainer()
     {
         LocatorProxy.GetLocationsByPostalCodeRequestContainer ToProxy = new LocatorProxy.GetLocationsByPostalCodeRequestContainer();

         // Set Location ID
         ToProxy.PostalCode = "L4Z0A8";

         // Set Serach options
         ToProxy.SearchOptions = GetSerachOption();

         // Set Hours Of Operation
         ToProxy.HoursOfOperation = GetHours();

         // Set LocationTypes
         ToProxy.LocationTypes = new string[4];
         ToProxy.LocationTypes[0] = LocationType.Staples.ToString();
         ToProxy.LocationTypes[1] = LocationType.ShippingCentre.ToString();
         ToProxy.LocationTypes[2] = LocationType.ShippingAgent.ToString();
         ToProxy.LocationTypes[3] = LocationType.DropBox.ToString();

         // Set DaysOfOperation
         ToProxy.DaysOfOperation = new string[7];
         ToProxy.DaysOfOperation[0] = DaysOfWeek.Monday.ToString();
         ToProxy.DaysOfOperation[1] = DaysOfWeek.Tuesday.ToString();
         ToProxy.DaysOfOperation[2] = DaysOfWeek.Wednesday.ToString();
         ToProxy.DaysOfOperation[3] = DaysOfWeek.Thursday.ToString();
         ToProxy.DaysOfOperation[4] = DaysOfWeek.Friday.ToString();
         ToProxy.DaysOfOperation[5] = DaysOfWeek.Saturday.ToString();
         ToProxy.DaysOfOperation[6] = DaysOfWeek.Sunday.ToString();


         return ToProxy;
     }
     
     private static LocatorProxy.SearchOptions GetSerachOption()
     {
         LocatorProxy.SearchOptions toOptions = new SearchOptions();
         toOptions.RadialDistanceInKM = 100;
         toOptions.HoldForPickup = true;
         toOptions.DangerousGoods = false;
         toOptions.Kiosk = false;
         toOptions.StreetAccess = true;
         toOptions.WheelChairAccess = true;
         toOptions.MaxNumberOfLocations = 30;
         return toOptions;
     }
     
     private static LocatorProxy.HoursOfOperation GetHours()
     {
         LocatorProxy.HoursOfOperation toHours = new HoursOfOperation();
         toHours.OpenTime = System.DateTime.MinValue;
         toHours.CloseTime = System.DateTime.MinValue;
         toHours.CurrentlyOpen = true;
         toHours.GMTOffset = System.DateTime.MinValue;
         return toHours;
     }
     
     private void Display(LocatorProxy.ResponseInformation respInf)
     {
         if (respInf == null)
             return;

         int i = 0;
         if (respInf.Errors != null && respInf.Errors.Length > 0)
         {
             foreach (LocatorProxy.Error error in respInf.Errors)
             {
                 i++;
                 Util.Print("Error", i);
                 Util.Push();
                 Util.Print("Error code", error.Code);
                 Util.Print("Error description", error.Description);
                 Util.Print("Additional Information", error.AdditionalInformation);
                 Util.Pop();
             }
         }

         i = 0;
         if (respInf.InformationalMessages != null && respInf.InformationalMessages.Length > 0)
         {
             foreach (LocatorProxy.InformationalMessage msg in respInf.InformationalMessages)
             {
                 i++;
                 Util.Print("InformationalMessage", i);
                 Util.Push();
                 Util.Print("", msg.Code);
                 Util.Print("message", msg.Message);
                 Util.Pop();
             }
         }
     }
     
     private void TranslateDisplayLocatorInformation(List<LocatorProxy.DepotLocation> response)
     {
         List<LocatorProxy.DepotLocation> to = new List<LocatorProxy.DepotLocation>();
         
         if (response != null)
         {
             Console.Clear();
             foreach (LocatorProxy.DepotLocation fromDepotLocation in response)
             {
                 Util.Print("ContactName", fromDepotLocation.ContactName);

                 Util.Print("AddressLine1", fromDepotLocation.locationAddress.AddressLine1);
                 Util.Print("AddressLine2", fromDepotLocation.locationAddress.AddressLine2);
                 Util.Print("AddressLine3", fromDepotLocation.locationAddress.AddressLine3);
                 Util.Print("");

                 Util.Print("AddressType", fromDepotLocation.locationAddress.AddressType);
                 Util.Print("FloorNumber", fromDepotLocation.locationAddress.FloorNumber);
                 Util.Print("StreetNumber", fromDepotLocation.locationAddress.StreetNumber);
                 Util.Print("UnitSuiteApt", fromDepotLocation.locationAddress.UnitSuiteApt);
                 Util.Print("StreetName", fromDepotLocation.locationAddress.StreetName);
                 Util.Print("StreetType", fromDepotLocation.locationAddress.StreetType);
                 Util.Print("Suffix", fromDepotLocation.locationAddress.Suffix);
                 Util.Print("Direction", fromDepotLocation.locationAddress.Direction);
                 Util.Print("");

                 Util.Print("CityName", fromDepotLocation.locationAddress.CityName);
                 Util.Print("CountryCode", fromDepotLocation.locationAddress.CountryCode);
                 Util.Print("ProvinceStateCode", fromDepotLocation.locationAddress.ProvinceStateCode);
                 Util.Print("PostalCode", fromDepotLocation.locationAddress.PostalCode);
                 Util.Print("CityCode", fromDepotLocation.locationAddress.CityCode);
                 Util.Print("");

                 Util.Print("Latitude", fromDepotLocation.Latitude);
                 Util.Print("Longitude", fromDepotLocation.Longitude);
                 Util.Print("ActivatedDate", fromDepotLocation.ActivatedDate);
                 Util.Print("LocationId", fromDepotLocation.LocationId);
                 Util.Print("LocationName", fromDepotLocation.LocationName);
                 Util.Print("");

                 Util.Print("CloseExceptionMon", fromDepotLocation.CloseExceptionMon);
                 Util.Print("CloseExceptionTue", fromDepotLocation.CloseExceptionTue);
                 Util.Print("CloseExceptionWed", fromDepotLocation.CloseExceptionWed);
                 Util.Print("CloseExceptionThu", fromDepotLocation.CloseExceptionThu);
                 Util.Print("CloseExceptionFri", fromDepotLocation.CloseExceptionFri);
                 Util.Print("CloseExceptionSat", fromDepotLocation.CloseExceptionSat);
                 Util.Print("CloseExceptionSun", fromDepotLocation.CloseExceptionSun);
                 Util.Print("");

                 Util.Print("CloseMon", fromDepotLocation.CloseMon);
                 Util.Print("CloseTue", fromDepotLocation.CloseTue);
                 Util.Print("CloseWed", fromDepotLocation.CloseWed);
                 Util.Print("CloseThu", fromDepotLocation.CloseThu);
                 Util.Print("CloseFri", fromDepotLocation.CloseFri);
                 Util.Print("CloseSat", fromDepotLocation.CloseSat);
                 Util.Print("CloseSun ", fromDepotLocation.CloseSun);
                 Util.Print("");


                 Util.Print("DangerousGoods", fromDepotLocation.DangerousGoods);
                 Util.Print("Depot", fromDepotLocation.Depot);
                 Util.Print("DropOffSatAirDom", fromDepotLocation.DropOffSatAirDom);
                 Util.Print("DropOffSatAirIntl", fromDepotLocation.DropOffSatAirIntl);
                 Util.Print("DropOffSatAirUS", fromDepotLocation.DropOffSatAirUS);
                 Util.Print("DropOffSatGndDom", fromDepotLocation.DropOffSatGndDom);
                 Util.Print("DropOffSatGndIntl", fromDepotLocation.DropOffSatGndIntl);
                 Util.Print("DropOffSatGndUS", fromDepotLocation.DropOffSatGndUS);
                 Util.Print("DropOffWeekDayAirDom", fromDepotLocation.DropOffWeekDayAirDom);
                 Util.Print("DropOffWeekDayAirIntl", fromDepotLocation.DropOffWeekDayAirIntl);
                 Util.Print("DropOffWeekDayAirUS", fromDepotLocation.DropOffWeekDayAirUS);
                 Util.Print("DropOffWeekDayGndDom", fromDepotLocation.DropOffWeekDayGndDom);
                 Util.Print("DropOffWeekDayGndIntl", fromDepotLocation.DropOffWeekDayGndIntl);
                 Util.Print("DropOffWeekDayGndUS", fromDepotLocation.DropOffWeekDayGndUS);
                 Util.Print("GMTOffset", fromDepotLocation.GMTOffset);
                 Util.Print("HoldForPickup", fromDepotLocation.HoldForPickup);
                 Util.Print("Kiosk", fromDepotLocation.Kiosk);
                 Util.Print("");

                 //Util.Print("OpenExceptionMon", fromDepotLocation.OpenExceptionMon);
                // Util.Print("OpenExceptionTue", fromDepotLocation.OpenExceptionTue);
                 //Util.Print("OpenExceptionWed", fromDepotLocation.OpenExceptionWed);
                // Util.Print("OpenExceptionThu", fromDepotLocation.OpenExceptionThu);
                // Util.Print("OpenExceptionFri", fromDepotLocation.OpenExceptionFri);
                 Util.Print("OpenExceptionSat", fromDepotLocation.OpenExceptionSat);
                 Util.Print("OpenExceptionSun", fromDepotLocation.OpenExceptionSun);
                 Util.Print("");


                 Util.Print("OpenMon", fromDepotLocation.OpenMon);
                 Util.Print("OpenTue", fromDepotLocation.OpenTue);
                 Util.Print("OpenWed", fromDepotLocation.OpenWed);
                 Util.Print("OpenThu", fromDepotLocation.OpenThu);
                 Util.Print("OpenFri", fromDepotLocation.OpenFri);
                 Util.Print("OpenSat", fromDepotLocation.OpenSat);
                 Util.Print("OpenSun", fromDepotLocation.OpenSun);
                 Util.Print("");

                 Util.Print("PhoneNumber", fromDepotLocation.PhoneNumber);
                 Util.Print("RadialDistanceInKM", fromDepotLocation.RadialDistanceInKM); ;
                 Util.Print("SpecialInstructionsEn", fromDepotLocation.SpecialInstructionsEn);
                 Util.Print("SpecialInstructionsFr", fromDepotLocation.SpecialInstructionsFr);
                 Util.Print("StreetAccess", fromDepotLocation.StreetAccess);
                 Util.Print("Unicode", fromDepotLocation.Unicode);
                 Util.Print("WheelChairAccess", fromDepotLocation.WheelChairAccess);


                 if (fromDepotLocation.LocationTypes != null && fromDepotLocation.LocationTypes.Count() > 0)
                 {
                     Util.Print("LocationType", fromDepotLocation.LocationTypes[0]);

                 }
                 Util.Print(""); Util.Print("");

                 break;
             }
         }
         
     }

}

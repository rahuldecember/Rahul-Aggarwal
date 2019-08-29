package payLoad;

public class payloads {

	public static String getAddProductPayload() {
		String pl= "{" + "\"name\": \"Duracell Test Product\"," + "\"type\": \"HardGood\"," + "\"price\": 5.00,"
				+ "\"upc\": \"10101010101010\"," + "\"shipping\": 0,"
				+ "\"description\": \"Test Product Compatible with select electronic devices; AAA size\","
				+ "\"manufacturer\": \"Duracell\"," + "\"model\": \"TestModal\","
				+ "\"url\": \"http://www.bestbuy.com\","
				+ "\"image\": \"http://img.bbystatic.com/43900_sa.jpg\"" + "}";
		return pl;
		
	}
	
	public static String getAddProductPayload_Missing() {
		String pl= "{" + "\"type\": \"HardGood\"," + "\"price\": 5.00,"
				+ "\"upc\": \"10101010101010\"," + "\"shipping\": 0,"
				+ "\"description\": \"Test Product Compatible with select electronic devices; AAA size\","
				+ "\"manufacturer\": \"Duracell\"," + "\"model\": \"TestModal\","
				+ "\"url\": \"http://www.bestbuy.com\","
				+ "\"image\": \"http://img.bbystatic.com/43900_sa.jpg\"" + "}";
		return pl;
		
	}
	
	public static String getAddCategoryPayload(int categoryId) {
		String pl= "{" 
					+ "\"name\": \"Test Category\","
					+ "\"id\": \""+ categoryId +"\"" + "}";
		return pl;
		
	}
	
	public static String getAddCategoryPayloadInvalid() {
		String pl= "{" 
//					+ "\"name\": \"Test Category\","
					+ "\"id\": \"123456\"" + "}";
		return pl;
		
	}
	
	public static String getAddServicePayload() {
		String pl= "{" + "\"name\": \"Test Service\"" + "}";
		return pl;
		
	}
	
	public static String getAddStorePayload() {
		//String pl= "{" + "\"name\": \"Test Category\"," + "\"id\": \"TestID\"" + "}";
		
		
		
		String pl= "{" 
			 +"\"name\": \"Minnetonka test\","
			 +"\"type\": \"BigBox\","
			 + "\"address\": \"Ridgedale\","
			 + "\"address2\": \"dr\","
			 + "\"city\": \"Hopkins\","
			 + "\"state\": \"MN\","
			 + "\"zip\": \"55305\","
			 + "\"lat\": 44.969658,"
			 + "\"lng\": -93.449539,"
			 + "\"hours\": \"Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9\","
			 + "\"services\": {}"
			 + "}";
		
		return pl;
	}
	
	

}

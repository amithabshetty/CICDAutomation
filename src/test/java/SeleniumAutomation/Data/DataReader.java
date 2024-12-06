package SeleniumAutomation.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

 public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
	
	 //Read Json file through FileUtility package
	String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//SeleniumAutomation//Data//PurchaseOrder.json"),StandardCharsets.UTF_8);
	//Convert String to Hashmap - Dependency Jackson Databind to convert String to Hashmap
	ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
	});
//data will be a Hashmap now with 2 maps , (map,map}
	return data;
	
	
}

}
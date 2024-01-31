package com.syedm;

// Importing required classes
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



// Class
public class App {

	// Main driver method
	public static void main(String[] a)
	{
		// Creating Object of ObjectMapper define in Jackson
		// Api
		ObjectMapper objectMapper = new ObjectMapper();

		// Try block to check for exceptions
		try {

			Car car = new Car("yellow", "renault");

			// object to json
			System.out.println(objectMapper.writeValueAsString(car));

			// json to object
			String json = "{\"color\": \"White\", \"type\": \"Nissan Sentra\"}";

			System.out.println("Json String : " + json);

			car = objectMapper.readValue(json, Car.class);

			System.out.println("translated to a car object and back to following string");
			System.out.println(objectMapper.writeValueAsString(car));

			// json to jackson JsonNode
			System.out.println("A json can be parsed into JsonNode object to retreive data from specific node as follows");
			JsonNode jsonNode = objectMapper.readTree(json);
			String color = jsonNode.get("color").asText();
			System.out.println("Retrieved color (from json string): " + color);

			// embedded node
			System.out.println("-- reading json file user.json");
			InputStream is = App.class.getClassLoader().getResourceAsStream("User.json");
			json = new String(is.readAllBytes(), StandardCharsets.UTF_8);

			// reading top level fields
			System.out.println("  - reading top level data");
			JsonNode topNode = objectMapper.readTree(json);
			String userName = topNode.get("name").asText();
			System.out.println("    name : " + userName);
			int age = topNode.get("age").asInt();
			System.out.println("    age  : " + age);

			// reading array data
			System.out.println("  - reading an array");
			JsonNode hobbyNode = topNode.get("hobbies");
			for (int index = 0; index < hobbyNode.size(); index++) {
				System.out.println("    hobby  : " + hobbyNode.get(index));
			}

			// reading embedded object data
			System.out.println("  -reading embedded object data");
			JsonNode objNode = topNode.get("address");
			String userCity = objNode.get("city").asText();
			System.out.println("    city : " + userCity);
			String zip = objNode.get("zip").asText();
			System.out.println("    zip  : " + zip);
		}
		catch (IOException e) {

			// Display exception along with line number
			// using printStackTrace() method
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String readFileAsString(String file) throws Exception {
		return new String(Files.readAllBytes(Paths.get(file)));
	}
}


package recognition02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;

public class Recognition02 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		VisualRecognition service = new VisualRecognition("2018-03-19");
		service.setApiKey("93699c8911885458e143a7420980bebe1e5a6bc4");

		InputStream imagesStream = null;
		try {
			imagesStream = new FileInputStream("img/fruitbowl.jpg");
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
		  .imagesFile(imagesStream)
		  .imagesFilename("fruitbowl.jpg")
		  .threshold((float) 0.6)
		  .owners(Arrays.asList("IBM"))
		  .build();
		ClassifiedImages result = service.classify(classifyOptions).execute();
		
		System.out.println(result);
		String s = String.valueOf(result);
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode node = null;
		try {
			 node = mapper.readTree(s);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		String class1= node.get("images").get(0).get("classifiers").get(0).get("classes").get(0).get("class").toString();
		System.out.println("class:"+class1);
		
		double score1= node.get("images").get(0).get("classifiers").get(0).get("classes").get(0).get("score").asDouble();
		System.out.println("class:"+score1);
		
		
		String class2= node.get("images").get(0).get("classifiers").get(0).get("classes").get(1).get("class").toString();
		System.out.println("class:"+class2);
		
		double score2= node.get("images").get(0).get("classifiers").get(0).get("classes").get(1).get("score").asDouble();
		System.out.println("class:"+score2);
		
		
		String class3= node.get("images").get(0).get("classifiers").get(0).get("classes").get(2).get("class").toString();
		System.out.println("class:"+class3);
		
		double score3= node.get("images").get(0).get("classifiers").get(0).get("classes").get(2).get("score").asDouble();
		System.out.println("class:"+score3);
		
	}

}

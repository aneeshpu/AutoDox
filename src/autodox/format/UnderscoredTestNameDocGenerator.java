package autodox.format;

import java.util.ArrayList;
import java.util.List;

public class UnderscoredTestNameDocGenerator {

	public List<String> format(List<String> testNames){
		ArrayList<String> docs = new ArrayList<String>();
		for (String testName : testNames) {
			docs.add(format(testName));
		}
		
		return docs;
	}

	private String format(String testNameWithUnderscores) {
		return testNameWithUnderscores.replace('_', ' ');
	}
}

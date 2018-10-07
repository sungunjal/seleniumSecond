package frameworkLibrary;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.annotations.Test;

import manager.FileReaderManager;

public class SetupForProject 
{
	@Test
	public void setup() throws Exception {
		FileReaderManager.getInstance().getCreateTestNGxmlFile().createTestNgXmlFile();
		TestNG runner = new TestNG();
		List<String> list = new ArrayList<String>();
		list.add(System.getProperty("user.dir") + "\\test.xml");
		runner.setTestSuites(list);
		runner.run();
	}
}

package frameworkLibrary;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import enums.DriverType;
import manager.FileReaderManager;

public class CreateTestNGxml 
{
	int row;
	List<String> packageList,classList;
	
	@SuppressWarnings("unlikely-arg-type")
	public void createTestNgXmlFile()
	{
		packageList = new ArrayList<String>();
		classList = new ArrayList<String>();
		
		try
		{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBulider = docFactory.newDocumentBuilder();
			Document doc = docBulider.newDocument();
			
			Element rootElement = doc.createElement("suite");
			doc.appendChild(rootElement);
			
			//////// Setting Attribute in suite
			Attr attr = doc.createAttribute("name");
			attr.setValue("suite");
			rootElement.setAttributeNode(attr);
			
			// Getting row count from exel
			row = FileReaderManager.getInstance().getExcelComonFunction().getRowCount();
			
			// adding classes and packages in list
			for(int i=0;i<row;i++)
			{
				packageList.add(FileReaderManager.getInstance().getExcelComonFunction().getValueFormExcel((i+1), 0));
				classList.add(FileReaderManager.getInstance().getExcelComonFunction().getValueFormExcel((i+1), 1));
			}
			
			// set attribute to suite element
			Attr parallel = doc.createAttribute("parallel");
			parallel.setValue("tests");
			rootElement.setAttributeNode(parallel);
			
			if (FileReaderManager.getInstance().getConfigFileReader().getBrowser().equals(DriverType.FIREFOX))
			{
				// test
				Element test = doc.createElement("test");
				rootElement.appendChild(test);
				Attr testName = doc.createAttribute("name");
				testName.setValue("FirefoxTest");
				test.setAttributeNode(testName);
				
				// Classes
				Element classes1 = doc.createElement("classes");
				test.appendChild(classes1);

				// fetching value from Excel and inserting it to XML
				for (int i = 0; i < packageList.size(); i++) {

					String excelPackageName = packageList.get(i);
					String excelClassName = classList.get(i);
					String testNGTestName = excelPackageName + "." + excelClassName;
					// class Element
					Element class1 = doc.createElement("class");
					classes1.appendChild(class1);
					attr = doc.createAttribute("name");
					attr.setValue(testNGTestName);
					class1.setAttributeNode(attr);
				}
				
			}
			// set attribute to parameter for Chome
			if (FileReaderManager.getInstance().getConfigFileReader().getBrowser().equals(DriverType.CHROME)) {
				// test
				Element test = doc.createElement("test");
				rootElement.appendChild(test);
				Attr testName = doc.createAttribute("name");
				testName.setValue("ChromeTest");
				test.setAttributeNode(testName);
				
				
				// Classes
				Element classes1 = doc.createElement("classes");
				test.appendChild(classes1);

				// fetching value from Excel and inserting it to XML
				for (int i = 0; i < packageList.size(); i++) {

					String excelPackageName = packageList.get(i);
					String excelClassName = classList.get(i);
					String testNGTestName = excelPackageName + "." + excelClassName;
					// class Element
					Element class1 = doc.createElement("class");
					classes1.appendChild(class1);
					attr = doc.createAttribute("name");
					attr.setValue(testNGTestName);
					class1.setAttributeNode(attr);
				}
			}
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(System.getProperty("user.dir") + "/test.xml"));

			transformer.transform(source, result);
			System.out.println("File saved!");

		}
		catch(Exception e)
		{}
	}
	
}

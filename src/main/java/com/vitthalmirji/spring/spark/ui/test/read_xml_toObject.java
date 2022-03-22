package com.vitthalmirji.spring.spark.ui.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class read_xml_toObject {
	public static void main(String [] args) {
		List<Person> ListPerson=new ArrayList<>();
		/** ====================== end always config spark ===============*/

		SparkConf conf =new SparkConf().setAppName("StartingSpark").setMaster("local[*]");
		JavaSparkContext sc =new JavaSparkContext(conf); 
		SparkSession spark = SparkSession.builder().
				appName("documentation").master("local")
				.config("spark.sql.warehouse.dir", "file:///c:/tmp/spark-warehouse")
				.getOrCreate();
		/** ====================== end always config spark ===============*/
	
		try {
			//Person P=new Person();
		//creating a constructor of file class and parsing an XML file  
		File file = new File("src/main/resources/persons.xml");  
		//an instance of factory that gives a document builder  
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
		//an instance of builder to parse the specified xml file  
		DocumentBuilder db = dbf.newDocumentBuilder();  
		Document doc = db.parse(file);  
		doc.getDocumentElement().normalize();  
		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());  
		NodeList nodeList = doc.getElementsByTagName("student");  
		// nodeList is not iterable, so we are using for loop  
		for (int itr = 0; itr < nodeList.getLength(); itr++)   
		{  
		Node node = nodeList.item(itr);  
		System.out.println("\nNode Name :" + node.getNodeName());  
		if (node.getNodeType() == Node.ELEMENT_NODE)   
		{  
		Element eElement = (Element) node;  
		
		String id=eElement.getElementsByTagName("id").item(0).getTextContent();
		String Firstnam=eElement.getElementsByTagName("firstname").item(0).getTextContent();
				String lastName =eElement.getElementsByTagName("lastname").item(0).getTextContent();
				String subject=eElement.getElementsByTagName("subject").item(0).getTextContent();
				String marks=eElement.getElementsByTagName("marks").item(0).getTextContent();
				ListPerson.add(new Person(id,Firstnam,lastName,subject,marks));
		}  
		}  
		}   
		catch (Exception e)   
		{  
		e.printStackTrace();  
		} 
		//System.out.println(ListPerson);
		JavaRDD<Person> myRDD = sc.parallelize(ListPerson);
		System.out.println("sie is : "+myRDD.count());;
		spark.createDataFrame(myRDD,Person.class).show();
	
	
		

	}

}

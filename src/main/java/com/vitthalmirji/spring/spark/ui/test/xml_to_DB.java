package com.vitthalmirji.spring.spark.ui.test;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class xml_to_DB {
	public static void main(String[] args) {
		/** ====================== end always config spark ===============*/

		SparkConf conf =new SparkConf().setAppName("StartingSpark").setMaster("local[*]");
		JavaSparkContext sc =new JavaSparkContext(conf); 
		SparkSession spark = SparkSession.builder().
				appName("documentation").master("local")
				.config("spark.sql.warehouse.dir", "file:///c:/tmp/spark-warehouse")
				.getOrCreate();
		/** ====================== end always config spark ===============*/
		
		
		
		List<Persons> ListPerson = new ArrayList<>();

		/* ======================================= Convert xml to List of object =============================== */
		try {
			// Person P=new Person();
			// creating a constructor of file class and parsing an XML file
			File file = new File("src/main/resources/persons.xml");
			// an instance of factory that gives a document builder
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// an instance of builder to parse the specified xml file
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			//System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
			NodeList nodeList = doc.getElementsByTagName("person");
			// nodeList is not iterable, so we are using for loop
			for (int itr = 0; itr < nodeList.getLength(); itr++) {
				Node node = nodeList.item(itr);
				
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;

					String personId = eElement.getElementsByTagName("personId").item(0).getTextContent();
					String firstName = eElement.getElementsByTagName("firstName").item(0).getTextContent();
					String lastName = eElement.getElementsByTagName("lastName").item(0).getTextContent();
					String email = eElement.getElementsByTagName("email").item(0).getTextContent();
					String age = eElement.getElementsByTagName("age").item(0).getTextContent();
					ListPerson.add(new Persons(personId, firstName, lastName, email, age));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(ListPerson.get(1000).getAge());
		/* ======================================== end tronsfere xml to object  ======================================*/

		
		
		/*============================================= object to DATAFrame Row ======================= */
		JavaRDD<Persons> myRDD = sc.parallelize(ListPerson);
		System.out.println("Nombre des compteurs est : "+myRDD.count());;
		Dataset<Row>PersonDataSet=spark.createDataFrame(myRDD,Persons.class); //convert JavaRDD to DataSet
		PersonDataSet.show(100,false);
		
		/* ======================== end object to DATAFrame Row ========================================*/
	
		
		
		
	/* =========================================== Write RDD to PostgreSql ======================= */
		Properties connectionProperties = new Properties();
        connectionProperties.put("user", "postgres");
        connectionProperties.put("password", "98970674mm");
        connectionProperties.put("spring.jpa.show-sql", true);
        connectionProperties.put("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        connectionProperties.put("spring.jpa.hibernate.ddl-auto", "update");


        PersonDataSet.write()
        .jdbc("jdbc:postgresql://localhost:5432/testspark", "OBjectTest", connectionProperties);
        System.out.println(" =============================================== ******** write success ");
		
		/*============================================== end Write RDD to PostgreSql ===================*/
		}


}

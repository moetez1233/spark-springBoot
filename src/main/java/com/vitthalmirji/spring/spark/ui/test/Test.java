package com.vitthalmirji.spring.spark.ui.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;

public class Test {

    public long getSC() {
    	List<Integer> inputData=new ArrayList<Integer>();
		inputData.add(35);
		inputData.add(255);
		inputData.add(14);
		inputData.add(56);
		SparkConf conf =new SparkConf().setAppName("StartingSpark").setMaster("local[*]");
		JavaSparkContext sc =new JavaSparkContext(conf); 
		JavaRDD<Integer> myRDD = sc.parallelize(inputData);  
		System.out.println("size of my RDD is "+myRDD.count());
		//sc.close();
		return myRDD.count();
		

    }

}

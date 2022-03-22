package com.vitthalmirji;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.vitthalmirji.spring.spark.ui.test.Person;
import com.vitthalmirji.spring.spark.ui.test.Test;

import java.io.File;
import java.util.ArrayList;

import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.DocumentBuilder;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import org.apache.spark.sql.SparkSession;
import org.w3c.dom.Document;  
import org.w3c.dom.NodeList;  
import org.w3c.dom.Node;  
import org.w3c.dom.Element;  

@SpringBootApplication
public class Main extends SpringBootServletInitializer {
    public static void main(String[] args) {
   Test t=new Test();
    	t.getSC();
    			}  

    }


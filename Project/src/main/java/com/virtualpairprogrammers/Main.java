package com.virtualpairprogrammers;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class Main {

  public static void main(String[] args) {

    List<Double> inputData = new ArrayList<>();
    inputData.add(35.5);
    inputData.add(2305.5);
    inputData.add(9879.5);
    inputData.add(1.5);

    // Create a logger
    Logger.getLogger("org.apache").setLevel(Level.WARN);

    SparkConf conf = new SparkConf().setAppName("startingSpark").setMaster("local[*]");
    JavaSparkContext sc = new JavaSparkContext(conf);

    JavaRDD<Double> myRdd = sc.parallelize(inputData); // loading a collection and turning it into an RDD
    Double ret = myRdd.reduce((value1, value2) -> value1 + value2);
    System.out.println(ret);
    sc.close();
  }
}

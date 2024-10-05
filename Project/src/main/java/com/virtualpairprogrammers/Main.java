package com.virtualpairprogrammers;

import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class Main {

  public static void main(String[] args) {

    List<String> inputData = new ArrayList<>();
    inputData.add("WARN: Tuesday 4 September 0405");
    inputData.add("ERROR: Tuesday 4 September 6421");
    inputData.add("FATAL: Wednesday 4 September 4942");
    inputData.add("WARN: Saturday 4 September 2038");

    // Create a logger
    Logger.getLogger("org.apache").setLevel(Level.WARN);

    SparkConf conf = new SparkConf().setAppName("startingSpark").setMaster("local[*]");
    JavaSparkContext sc = new JavaSparkContext(conf);

//    JavaRDD<String> originalLogMessages = sc.parallelize(inputData); // loading a collection and turning it into an RDD
//    JavaPairRDD<String, Long> pairRdd = originalLogMessages.mapToPair(rawValue -> new Tuple2<>(rawValue.split(":")[0], 1L));
//    JavaPairRDD<String, Long> sumsRdd = pairRdd.reduceByKey((value1, value2) -> (value1 + value2));
//    sumsRdd.foreach(tuple -> System.out.println(tuple._1() + " has " + tuple._2() + " instances."));
    sc.parallelize(inputData)
        .mapToPair(rawValue -> new Tuple2<>(rawValue.split(":")[0], 1L))
            .reduceByKey((value1, value2) -> (value1 + value2))
                .foreach(tuple2 -> System.out.println(tuple2._1 + "has " + tuple2._2 + " instances."));

// groupby key
//    sc.parallelize(inputData)
//        .mapToPair(rawValue -> new Tuple2<>(rawValue.split(":")[0], 1L))
//        .groupByKey()
//            .foreach(tuple -> System.out.println(tuple._1 + "has " + Iterables.size(tuple._2) + " instances."));

//    JavaRDD<Tuple2<Integer, Double>> sqrtRdd = originalIntegers.map(value -> new Tuple2<>(value, Math.sqrt(value)));
//    JavaRDD<Double> sqrtRdd = myRdd.map(value -> Math.sqrt(value));
//    sqrtRdd.foreach(System.out::println);
//    sqrtRdd.collect().forEach(System.out::println);
//    System.out.println(ret);

//     how many elements in sqrtRdd
//     using just map and reduce
//    JavaRDD<Long> singleIntegerRdd = sqrtRdd.map(value -> 1L);
//    Long count = singleIntegerRdd.reduce((value1, value2) -> value1 + value2);
//    System.out.println(count);
    sc.close();
  }
}

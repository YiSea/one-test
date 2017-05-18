package com.one.hadoop;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

/**
 * 
  * @Package com.one.hadoop.MaxTemperature
  * @ClassName MaxTemperature.java
  * @author jun.wu  
  * @date 2017年5月12日 下午2:56:16 
  * @Description: MapReduce 作业
 */
public class MaxTemperature {

	public static void main(String[] args) throws IOException {
		if(args.length != 2){
			System.err.println("Usage:MaxTemperatrue <input path> <output path>");
			System.exit(-1);
		}
		
		JobConf conf = new JobConf(MaxTemperature.class);
		conf.setJobName("max temperature");
		
		FileInputFormat.addInputPath(conf, new Path(args[0]));// 输入路径：文件、目录等路径
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));// 输出文件目录，必须是个不存在的文件
		
		conf.setMapperClass(MaxTemperatureMapper.class);
		conf.setReducerClass(MaxTemperatureReducer.class);
		
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);
		
		JobClient.runJob(conf);
	}

}

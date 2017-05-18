package com.one.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

/**
 * 
  * @Package com.one.hadoop 
  * @ClassName MaxTemperatureMapper.java
  * @author jun.wu  
  * @date 2017年5月12日 下午2:56:37 
  * @Description: map函数
 */
public class MaxTemperatureMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable>{

	private static final int MISSING = 99;
	
	@Override
	public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter)
			throws IOException {
		// LongWritable -> Long    Text -> String  IntWritable -> Intager
		String line = value.toString();
		String year = line.substring(7,11);
		int airTemperature;
		/**
		 10,10002010
		 11,22002010
		 12,33002011
		 13,+3202011
		 13,99002011
		 13,+9902010
		 */
		if(line.charAt(3) == '+'){
			airTemperature = Integer.parseInt(line.substring(4,6));
		}else{
			airTemperature = Integer.parseInt(line.substring(3, 5));
		}
		String quality = line.substring(7, 11);
		if(airTemperature != MISSING && quality.matches("[012]+")){
			output.collect(new Text(year), new IntWritable(airTemperature));
		}
	}
}

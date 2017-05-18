package com.one.hadoop;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

/**
 * 
  * @Package com.one.hadoop 
  * @ClassName MaxTemperatureReducer.java
  * @author jun.wu  
  * @date 2017年5月12日 下午2:56:49 
  * @Description: reduce函数
 */
public class MaxTemperatureReducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable>{

	@Override
	public void reduce(Text key, Iterator<IntWritable>  valuse, OutputCollector<Text, IntWritable> output, Reporter reporter)
			throws IOException {
		// TODO Auto-generated method stub
		int maxValue = Integer.MIN_VALUE;
		while(valuse.hasNext()){
			maxValue = Math.max(maxValue, valuse.next().get());
		}
		output.collect(key, new IntWritable(maxValue));
	}

}

package hadoop;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCounterMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	private final static IntWritable one = new IntWritable(1);

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		List<String> allowedLabels = Arrays.asList("[INFO]", "[SEVERE]", "[WARN]");

		
		for (int i = 0; i < allowedLabels.size(); i++) {
			if (line.contains(allowedLabels.get(i))){
				Text currentWord = new Text(allowedLabels.get(i));
				context.write(currentWord, one);
			}
			
		}
	}
}

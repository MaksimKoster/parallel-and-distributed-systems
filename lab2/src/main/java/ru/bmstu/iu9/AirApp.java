package ru.bmstu.iu9;


import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapre.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.Job;

public class AirApp {
    public static void main(String[] args) throws Exception{
        if (args.length != 3) {
            System.err.println("Usage: AirApp <flight data path> <airports data path> <output path>");
            System.exit(-1);
        }
        Job job = Job.getInstance();
        job.setJarByClass(AirApp.class);
        job.setJobName("Join App");
        MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class, FlightMapper.class);
        MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class, FlightMapper.class);

        job.setMapOutputKeyClass(AirWritableComparable.class);
        job.setMapOutputValueClass(Text.class);
        job.setPartitionerClass(AirPartitioner.class);
        job.setGroupingComparatorClass(AirComparator.class);

        FileOutputFormat.setOutputPath(job, new Path (args[2]));

    }
}

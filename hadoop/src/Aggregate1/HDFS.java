/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aggregate1;

/**
 *
 * @author code
 */
import static Aggregate1.MainView.sz;
import com.jlmd.AdaptiveNeuroFuzzyScheduler.neuralnetwork.AdaptiveNeuroFuzzyScheduler;
import com.jlmd.AdaptiveNeuroFuzzyScheduler.neuralnetwork.callback.INeuralNetworkCallback;
import com.jlmd.AdaptiveNeuroFuzzyScheduler.neuralnetwork.entity.Result;
import com.jlmd.AdaptiveNeuroFuzzyScheduler.neuralnetwork.utils.DataUtils;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Partitioner;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.commons.io.FileUtils;
import org.jfree.ui.RefineryUtilities;

public class HDFS extends Configured implements Tool {

    private static final Log LOG = LogFactory.getLog(HDFS.class);
    Log hadoop = LogFactory.getLog("---------------------------");
    Log hadooop = LogFactory.getLog("---------------------------");
    Log ENN = LogFactory.getLog("---------------------------------");
    Log omo = LogFactory.getLog("---------------------------");
    Log prep = LogFactory.getLog("---------------------------------");
    Log nor = LogFactory.getLog("---------------------------");

    static class TotalOrderPartitioner implements Partitioner<Text, Text> {

        private TrieNode trie;
        private Text[] splitPoints;

        static abstract class TrieNode {

            private int level;

            TrieNode(int level) {
                this.level = level;
            }

            abstract int findPartition(Text key);

            abstract void print(PrintStream strm) throws IOException;

            int getLevel() {
                return level;
            }
        }

        static class InnerTrieNode extends TrieNode {

            private TrieNode[] child = new TrieNode[256];

            InnerTrieNode(int level) {
                super(level);
            }

            int findPartition(Text key) {
                int level = getLevel();
                if (key.getLength() <= level) {
                    return child[0].findPartition(key);
                }
                return child[key.getBytes()[level]].findPartition(key);
            }

            void setChild(int idx, TrieNode child) {
                this.child[idx] = child;
            }

            void print(PrintStream strm) throws IOException {
                for (int ch = 0; ch < 255; ++ch) {
                    for (int i = 0; i < 2 * getLevel(); ++i) {
                        strm.print(' ');
                    }
                    strm.print(ch);
                    strm.println(" ->");
                    if (child[ch] != null) {
                        child[ch].print(strm);
                    }
                }
            }
        }

        static class LeafTrieNode extends TrieNode {

            int lower;
            int upper;
            Text[] splitPoints;

            LeafTrieNode(int level, Text[] splitPoints, int lower, int upper) {
                super(level);
                this.splitPoints = splitPoints;
                this.lower = lower;
                this.upper = upper;
            }

            int findPartition(Text key) {
                for (int i = lower; i < upper; ++i) {
                    if (splitPoints[i].compareTo(key) >= 0) {
                        return i;
                    }
                }
                return upper;
            }

            void print(PrintStream strm) throws IOException {
                for (int i = 0; i < 2 * getLevel(); ++i) {
                    strm.print(' ');
                }
                strm.print(lower);
                strm.print(", ");
                strm.println(upper);
            }
        }

        private static Text[] readPartitions(FileSystem fs, Path p,
                JobConf job) throws IOException {
            SequenceFile.Reader reader = new SequenceFile.Reader(fs, p, job);
            List<Text> parts = new ArrayList<Text>();
            Text key = new Text();
            NullWritable value = NullWritable.get();
            while (reader.next(key, value)) {
                parts.add(key);
                key = new Text();
            }
            reader.close();
            return parts.toArray(new Text[parts.size()]);
        }

        private static TrieNode buildTrie(Text[] splits, int lower, int upper,
                Text prefix, int maxDepth) {
            int depth = prefix.getLength();
            if (depth >= maxDepth || lower == upper) {
                return new LeafTrieNode(depth, splits, lower, upper);
            }
            InnerTrieNode result = new InnerTrieNode(depth);
            Text trial = new Text(prefix);
            trial.append(new byte[1], 0, 1);
            int currentBound = lower;
            for (int ch = 0; ch < 255; ++ch) {
                trial.getBytes()[depth] = (byte) (ch + 1);
                lower = currentBound;
                while (currentBound < upper) {
                    if (splits[currentBound].compareTo(trial) >= 0) {
                        break;
                    }
                    currentBound += 1;
                }
                trial.getBytes()[depth] = (byte) ch;
                result.child[ch] = buildTrie(splits, lower, currentBound, trial,
                        maxDepth);
            }
            trial.getBytes()[depth] = 127;
            result.child[255] = buildTrie(splits, currentBound, upper, trial,
                    maxDepth);
            return result;
        }

        public void configure(JobConf job) {
            try {
                FileSystem fs = FileSystem.getLocal(job);
                Path partFile = new Path(PhysicalshufflingInputFormat.PARTITION_FILENAME);
                splitPoints = readPartitions(fs, partFile, job);
                trie = buildTrie(splitPoints, 0, splitPoints.length, new Text(), 2);
            } catch (IOException ie) {
                throw new IllegalArgumentException("can't read paritions file", ie);
            }
        }

        public TotalOrderPartitioner() {
        }

        public int getPartition(Text key, Text value, int numPartitions) {
            return trie.findPartition(key);
        }

    }

    public int run(String[] args) throws Exception {
        ENN.info("ADAPTIVE NEURO FUZZY SCHEDULER: Starting");
        hadooop.info("QUEUE MANAGER STATUS : Starting");
        int i = 0;
        prep.info("Preprocessing Stats: Running");
        while (i < 21) {
            //System.out.print("[");

            System.out.print("\n");
            for (int j = 0; j < i; j++) {
                //System.out.print("\n");
            }

            for (int j = 0; j < 20 - i; j++) {
                // System.out.print(" ");
            }

            System.out.print("]====> " + i * 5 + "%");
            if (i < 20) {
                System.out.print("\r");
                Thread.sleep(300);
            }
            i++;
        }
        //System.out.println("\n");
        omo.info("[immutation completed]");

        int z = 0;

        while (z < 21) {
            //System.out.print("[");

            System.out.print("\n");
            for (int x = 0; x < z; x++) {
                //System.out.print("\n");
            }

            for (int x = 0; x < 20 - z; x++) {
                // System.out.print(" ");
            }

            System.out.print("]====> " + z * 5 + "%");
            if (z < 20) {
                System.out.print("\r");
                Thread.sleep(300);
            }
            z++;
        }

        Random r = new Random();
        int outid = r.nextInt(1000);
        String ar[] = {"/home/satya/MAP_SCAN_REDUCE/MAP_SCAN_REDUCE/Code/bigdata/hadoop-2.7.6/app/QUEUEMANAGER", "/home/satya/MAP_SCAN_REDUCE/MAP_SCAN_REDUCE/Code/bigdata/hadoop-2.7.6/app/Clusters/Data_Cluster" + outid};
       JobConf job = (JobConf) getConf();
        double st = System.currentTimeMillis();
        Path inputDir = new Path(ar[0]);
        //long fz = FileUtils.sizeOfDirectory(new File(ar[0]));
        // double mb = (1024 * 1024);
        //double sz = fz / mb;
        System.out.println("\n");
        System.out.println("Size of file==" + sz + "MB");
        nor.info("[Normalization completed]");
        //    System.out.println("" + sz);
        inputDir = inputDir.makeQualified(inputDir.getFileSystem(job));
        Path partitionFile = new Path(inputDir, PhysicalshufflingInputFormat.PARTITION_FILENAME);
        URI partitionUri = new URI(partitionFile.toString()
                + "#" + PhysicalshufflingInputFormat.PARTITION_FILENAME);
        PhysicalshufflingInputFormat.setInputPaths(job, new Path(ar[0]));
        FileOutputFormat.setOutputPath(job, new Path(ar[1]));
        job.setJobName("Task Schedule");
        job.setJarByClass(HDFS.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setInputFormat(PhysicalshufflingInputFormat.class);
        job.setOutputFormat(PhysicalshufflingOutputFormat.class);
        job.setPartitionerClass(TotalOrderPartitioner.class);
        PhysicalshufflingInputFormat.writePartitionFile(job, partitionFile);
        DistributedCache.addCacheFile(partitionUri, job);
        DistributedCache.createSymlink(job);
        job.setInt("dfs.replication", 1);
        PhysicalshufflingOutputFormat.setFinalSync(job, true);
        JobClient.runJob(job);
        double end = System.currentTimeMillis();
        hadoop.info(" Data size:" + sz + "(MB)");
        double ext = (end - st);
        ext = ext / 1000.0;
        hadoop.info(" Response Time(seconds):" + ext);
        //ENN.info("ADAPTIVE NEURO FUZZY SCHEDULER : Done");
        //hadooop.info("QUEUE MANAGER STATUS : Done");

        return 0;
    }

    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new JobConf(), new HDFS(), args);

        float[][] x = DataUtils.readInputsFromFile("data/x.txt");
        int[] t = DataUtils.readOutputsFromFile("data/t.txt");

        AdaptiveNeuroFuzzyScheduler neuralNetwork = new AdaptiveNeuroFuzzyScheduler(x, t, new INeuralNetworkCallback() {
            @Override
            public void success(Result result) {
                float[] valueToPredict = new float[]{-0.205f, 0.780f};
                System.out.println("Accuracy percentage: " + result.getSuccessPercentage());
                System.out.println("Predicted result: " + result.predictValue(valueToPredict));
            }

            @Override
            public void failure(com.jlmd.AdaptiveNeuroFuzzyScheduler.neuralnetwork.entity.Error error) {
                System.out.println("Error: " + error.getDescription());
            }
        });

        neuralNetwork.startLearning();
        // System.exit(res);
    }

}

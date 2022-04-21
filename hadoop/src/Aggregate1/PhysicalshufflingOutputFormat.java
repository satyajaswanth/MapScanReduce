/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aggregate1;

import java.io.DataOutputStream;
import java.io.IOException;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordWriter;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.util.Progressable;

/**
 *
 * @author code
 */
public class PhysicalshufflingOutputFormat extends TextOutputFormat<Text, Text> {

    static final String FINAL_SYNC_ATTRIBUTE = "Physicalshuffling.final.sync";

    public static void setFinalSync(JobConf conf, boolean newValue) {
        conf.setBoolean(FINAL_SYNC_ATTRIBUTE, newValue);
    }

    public static boolean getFinalSync(JobConf conf) {
        return conf.getBoolean(FINAL_SYNC_ATTRIBUTE, false);
    }

    static class TeraRecordWriter extends LineRecordWriter<Text, Text> {

        private static final byte[] newLine = "\r\n".getBytes();
        private boolean finalSync = false;

        public TeraRecordWriter(DataOutputStream out,
                JobConf conf) {
            super(out);
            finalSync = getFinalSync(conf);
        }

        public synchronized void write(Text key,
                Text value) throws IOException {
            out.write(key.getBytes(), 0, key.getLength());
            out.write(value.getBytes(), 0, value.getLength());
            out.write(newLine, 0, newLine.length);
        }

        public void close() throws IOException {
            if (finalSync) {
                ((FSDataOutputStream) out).sync();
            }
            super.close(null);
        }
    }

    public RecordWriter<Text, Text> getRecordWriter(FileSystem ignored,
            JobConf job,
            String name,
            Progressable progress
    ) throws IOException {
        Path dir = getWorkOutputPath(job);
        FileSystem fs = dir.getFileSystem(job);
        FSDataOutputStream fileOut = fs.create(new Path(dir, name), progress);
        return new TeraRecordWriter(fileOut, job);
    }
}

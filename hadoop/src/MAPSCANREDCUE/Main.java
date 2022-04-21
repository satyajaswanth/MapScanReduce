/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MAPSCANREDCUE;
import java.io.*;
import MAPSCANREDCUE.Collector;
import MAPSCANREDCUE.InCollector;
import MAPSCANREDCUE.Mapper;
import MAPSCANREDCUE.OutCollector;
import MAPSCANREDCUE.ParallelWorkflow;
import MAPSCANREDCUE.Reducer;
import MAPSCANREDCUE.Tuple;

/**
 *
 * @author code
 */
public class Main {

    public static void main(String a[]) {
        int k = 2;
        int n = 50;
        File fl = new File("/home/code/MAP_SCAN_REDUCE/Code/bigdata/hadoop-2.7.2/app/mapper");
        File fll[] = fl.listFiles();
        for (int i = 0; i < fll.length; i++) {

            BigStringCollector b = new BigStringCollector(fll[i].getAbsolutePath());
            ParallelWorkflow<String, String> w = new ParallelWorkflow<String, String>(new CountMap(k), new CountReduce(n),
                    b
            );

            InCollector<String, String> out = w.run();
            System.out.println("------------------------------\n");
            //ResultView.jTextArea1.append("Count Calculation Parallel\n");
            //  ResultView.jTextArea1.append("------------------------------\n");

            System.out.println("map Count Calculation Parallel\n");
            System.out.println("------------------------------\n");
//   Collector<String,String> out=results;
            int tid = 1;
            while (out.hasNext()) {
                //System.out.println(out.next().toString().trim());
                String freqitem = out.next().toString();
                freqitem = freqitem.replaceAll("〉", "");
                freqitem = freqitem.replaceAll("〈", "");
                System.out.println(freqitem + "\n===>" + fll[i].getName());
                tid++;
            }

            System.out.println("------------------------------");
        }
    }

    private static class BigStringCollector extends Collector<String, String> {

        BigStringCollector(String filename) {
            super();
            StringBuffer sb = new StringBuffer();
            try {
                BufferedReader input = new BufferedReader(new FileReader(filename));
                try {
                    String line = null;
                    while ((line = input.readLine()) != null) {
                        sb.append(line.trim()).append(" ");
                    }
                } finally {
                    input.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            super.collect(new Tuple<String, String>(sb.toString(), ""));
        }
    }

    private static class CountMap implements Mapper<String, String> {

        private int m_minLetters = 1;

        CountMap(int k) {
            m_minLetters = k;
        }

        @Override
        public void map(OutCollector<String, String> out, Tuple<String, String> t) {
            String[] words = t.getKey().split(" ");
            for (String w : words) {
                String new_w = w.toLowerCase();
                new_w = new_w.replaceAll("[^\\w]", "");
                if (new_w.length() >= m_minLetters) {
                    out.collect(new Tuple<String, String>(new_w, "1"));
                }
            }
        }
    }

    private static class CountReduce implements Reducer<String, String> {

        private int m_numOccurrences = 2;

        CountReduce(int n) {
            m_numOccurrences = n;
        }

        @Override
        public void reduce(OutCollector<String, String> out, String key, InCollector<String, String> in) {
            int num_words = in.count();
            if (num_words >= m_numOccurrences) {
                out.collect(new Tuple<String, String>(key, "" + num_words));
            }
        }
    }

}

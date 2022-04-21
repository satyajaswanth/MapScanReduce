/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MAPSCANREDCUE;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author code
 */
public class ResourceManager<K, V> {

    private int threadDefault = 100;

    private int threadMax = 0;

    private List<Thread> listThread = new LinkedList<Thread>();

    private void setThreadMax(int max) {
        this.threadMax = max;
    }

    public int getThreadMax() {
        return this.threadMax;
    }

    public ResourceManager() {
        setThreadMax(threadDefault);
    }

    public ResourceManager(int maxThread) {
        setThreadMax(maxThread);
    }

    public Thread getThread(Tuple<K, V> t, Collector<K, V> temp_coll, Mapper<K, V> m_mapper) {
        int i = 0;
        boolean create = false;
        MapThread<K, V> MThread = null;
        Thread ThreadTemp = null;

        if (listThread.size() < threadMax) {
            MThread = new MapThread<K, V>(t, temp_coll, m_mapper);
            listThread.add(MThread);
        } else {
            while (create != true) {
                while (i < listThread.size()) {
                    ThreadTemp = listThread.get(i);

                    if (!ThreadTemp.isAlive()) {
                        listThread.remove(i);
                        MThread = new MapThread<K, V>(t, temp_coll, m_mapper);
                        listThread.add(MThread);
                        create = true;
                        i = listThread.size();
                    } else {
                        i++;
                    }
                }
                i = 0;
            }
        }
        return MThread;
    }

    public Thread getThread(Collector<K, V> out, K key, Collector<K, V> s_source, Reducer<K, V> m_reducer) {
        int i = 0;
        boolean create = false;
        ReduceThread<K, V> RThread = null;
        Thread ThreadTemp = null;

        if (listThread.size() < threadMax) {
            RThread = new ReduceThread<K, V>(out, key, s_source, m_reducer);
            listThread.add(RThread);
        } else {
            while (create != true) {
                while (i < listThread.size()) {
                    ThreadTemp = listThread.get(i);

                    if (!ThreadTemp.isAlive()) {
                        listThread.remove(i);
                        RThread = new ReduceThread<K, V>(out, key, s_source, m_reducer);
                        listThread.add(RThread);
                        create = true;
                        i = listThread.size();
                    } else {
                        i++;
                    }
                }
                i = 0;
            }
        }
        return RThread;
    }

    public void waitThreads() {
        int i = 0;
        Thread ThreadTemp = null;
        while (i < listThread.size()) {
            ThreadTemp = listThread.get(i);

            if (ThreadTemp.isAlive()) {
                i = 0;
            } else {
                i++;
            }
        }
        listThread.clear();
    }
}

class MapThread<K, V> extends Thread {

    Tuple<K, V> tThread = new Tuple<K, V>();
    Collector<K, V> Thread_Temp_col = new Collector<K, V>();
    Mapper<K, V> Thread_m_mapper = null;

    MapThread(Tuple<K, V> t, Collector<K, V> temp_coll, Mapper<K, V> m_mapper) {
        this.tThread = t;
        this.Thread_Temp_col = temp_coll;
        this.Thread_m_mapper = m_mapper;
    }

    public void run() {
        Thread_m_mapper.map(Thread_Temp_col, tThread);
    }
}

class ReduceThread<K, V> extends Thread {

    Collector<K, V> outThread = new Collector<K, V>();
    K Thread_key = null;
    Collector<K, V> Thread_s_source = new Collector<K, V>();
    Reducer<K, V> Thread_m_reducer = null;

    ReduceThread(Collector<K, V> out, K key, Collector<K, V> s_source, Reducer<K, V> m_reducer) {
        this.outThread = out;
        this.Thread_key = key;
        this.Thread_s_source = s_source;
        this.Thread_m_reducer = m_reducer;
    }

    public void run() {
        Thread_m_reducer.reduce(outThread, Thread_key, Thread_s_source);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MAPSCANREDCUE;
import java.util.*;
import java.util.Date;

/**
 *
 * @author code
 */
public class ParallelWorkflow<K, V> implements Workflow<K, V> {

    private Mapper<K, V> m_mapper = null;
    private Reducer<K, V> m_reducer = null;
    private InCollector<K, V> m_source = null;
    private ResourceManager<K, V> m_managerMapper = null;
    private ResourceManager<K, V> m_managerReducer = null;

    protected long m_totalTuples = 0;

    protected long m_maxTuples = 0;

    public ParallelWorkflow(Mapper<K, V> m, Reducer<K, V> r, InCollector<K, V> c) {
        super();
        setMapper(m);
        setReducer(r);
        setSource(c);

        ResourceManager<K, V> rmM = new ResourceManager<K, V>();
        setManagerMapper(rmM);

        ResourceManager<K, V> rmR = new ResourceManager<K, V>();
        setManagerReducer(rmR);
    }

    public ParallelWorkflow(Mapper<K, V> m, Reducer<K, V> r, InCollector<K, V> c, ResourceManager<K, V> rmM, ResourceManager<K, V> rmR) {
        super();
        setMapper(m);
        setReducer(r);
        setSource(c);
        setManagerMapper(rmM);
        setManagerReducer(rmR);
    }

    public void setMapper(Mapper<K, V> m) {
        m_mapper = m;
    }

    public void setReducer(Reducer<K, V> r) {
        m_reducer = r;
    }

    public void setSource(InCollector<K, V> c) {
        m_source = c;
    }

    public void setManagerMapper(ResourceManager<K, V> rmM) {
        m_managerMapper = rmM;
    }

    public void setManagerReducer(ResourceManager<K, V> rmR) {
        m_managerReducer = rmR;
    }

    public InCollector<K, V> run() {
        if (m_mapper == null || m_reducer == null || m_source == null) {
            return null;
        }
        assert m_mapper != null;
        assert m_reducer != null;
        assert m_source != null;
        Collector<K, V> temp_coll = new Collector<K, V>();
        m_source.rewind();

        long timeBeforeMap = new Date().getTime();

        while (m_source.hasNext()) {
            Tuple<K, V> t = m_source.next();

            Thread MThread = m_managerMapper.getThread(t, temp_coll, m_mapper);
            MThread.start();
        }
        m_managerMapper.waitThreads();

        long timeAfterMap = new Date().getTime();
        long timePhaseMap = timeAfterMap - timeBeforeMap;
        System.out.println("--------------------------------------------------------\n");
        System.out.println("##########Time of every Phases #########\n");
        System.out.println("--------------------------------------------------------\n");
        System.out.println("                  Map : " + timePhaseMap + " Milliseconds\n");

        Map<K, Collector<K, V>> shuffle = temp_coll.subCollectors();
        Set<K> keys = shuffle.keySet();
        Collector<K, V> out = new Collector<K, V>();

        long timeBeforeReduce = new Date().getTime();
        for (K key : keys) {
            Collector<K, V> s_source = shuffle.get(key);
            int num_tuples = s_source.count();
            m_totalTuples += num_tuples;
            m_maxTuples = Math.max(m_maxTuples, num_tuples);

            Thread RThread = m_managerReducer.getThread(out, key, s_source, m_reducer);
            RThread.start();
        }

        m_managerReducer.waitThreads();

        long timeAfterReduce = new Date().getTime();
        long timePhaseReduce = timeAfterReduce - timeBeforeReduce;
        long timePhaseTotal = timePhaseMap + timePhaseReduce;
        double timeSeconds = (double) timePhaseTotal / 1000;
        int timeMinutes = (int) (timePhaseTotal / 1000) / 60;
        double timeSecMins = ((double) timePhaseTotal / 1000) - ((double) timeMinutes * 60);

        System.out.println("               Reduce : " + timePhaseReduce + " Milliseconds\n");
        System.out.println("--------------------------------------------------------\n");
        System.out.println("                Total : " + timePhaseTotal + " Milliseconds\n");
        System.out.println("        Total Seconds : " + timeSeconds + "\n");
        System.out.println("        Total Minutes : " + timeMinutes + "." + timeSecMins + "\n");
        System.out.println("--------------------------------------------------------\n");
        return out;
    }

    public long getMaxTuples() {
        return m_maxTuples;
    }

    public long getTotalTuples() {
        return m_totalTuples;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MAPSCANREDCUE;
import java.util.*;

/**
 *
 * @author code
 */
public class SequentialWorkflow<K, V> implements Workflow<K, V> {

    private Mapper<K, V> m_mapper = null;
    private Reducer<K, V> m_reducer = null;
    private InCollector<K, V> m_source = null;

    protected long m_totalTuples = 0;

    protected long m_maxTuples = 0;

    public SequentialWorkflow(Mapper<K, V> m, Reducer<K, V> r, InCollector<K, V> c) {
        super();
        setMapper(m);
        setReducer(r);
        setSource(c);
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

    public InCollector<K, V> run() {
        if (m_mapper == null || m_reducer == null || m_source == null) {
            return null;
        }
        assert m_mapper != null;
        assert m_reducer != null;
        assert m_source != null;
        Collector<K, V> temp_coll = new Collector<K, V>();
        m_source.rewind();
        while (m_source.hasNext()) {
            Tuple<K, V> t = m_source.next();
            m_mapper.map(temp_coll, t);
        }
        Map<K, Collector<K, V>> shuffle = temp_coll.subCollectors();
        Set<K> keys = shuffle.keySet();
        Collector<K, V> out = new Collector<K, V>();
        for (K key : keys) {
            Collector<K, V> s_source = shuffle.get(key);
            int num_tuples = s_source.count();
            m_totalTuples += num_tuples;
            m_maxTuples = Math.max(m_maxTuples, num_tuples);
            m_reducer.reduce(out, key, s_source);
        }
        return out;
    }

    public long getMaxTuples() {
        return m_maxTuples;
    }

    public long getTotalTuples() {
        return m_totalTuples;
    }

}

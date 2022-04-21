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
public class Collector<K, V> implements InCollector<K, V>, OutCollector<K, V> {

    private List<Tuple<K, V>> m_tuples = new LinkedList<Tuple<K, V>>();
    private Iterator<Tuple<K, V>> m_it = null;

    public List<Tuple<K, V>> toList() {
        return m_tuples;
    }

    public void addAll(Collection<Tuple<K, V>> list) {
        synchronized (this) {
            m_tuples.addAll(list);
        }
    }

    public void collect(Tuple<K, V> t) {
        synchronized (this) {
            m_tuples.add(t);
        }
    }

    public Collector<K, V> subCollector(K key) {
        Collector<K, V> c = new Collector<K, V>();
        synchronized (this) {
            for (Tuple<K, V> t : m_tuples) {
                if (t.getKey().equals(key)) {
                    c.m_tuples.add(t);
                }
            }
        }
        return c;

    }

    public int count() {
        synchronized (this) {
            return m_tuples.size();
        }
    }

    public Map<K, Collector<K, V>> subCollectors() {
        Map<K, Collector<K, V>> out = new HashMap<K, Collector<K, V>>();

        synchronized (this) {
            for (Tuple<K, V> t : m_tuples) {
                K key = t.getKey();
                Collector<K, V> c = out.get(key);

                if (c == null) {
                    c = new Collector<K, V>();
                }

                c.collect(t);
                out.put(key, c);
            }
        }
        return out;
    }

    @Override
    public boolean hasNext() {
        if (m_it == null) {
            m_it = m_tuples.iterator();
        }
        return m_it.hasNext();
    }

    @Override
    public Tuple<K, V> next() {
        return m_it.next();
    }

    @Override
    public void remove() {
        m_it.remove();
    }

    @Override
    public String toString() {
        return m_tuples.toString();
    }

    @Override
    public void rewind() {
        m_it = null;
    }
}

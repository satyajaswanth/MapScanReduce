/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MAPSCANREDCUE;
/**
 *
 * @author code
 */
public class Tuple<K, V> {

    private K m_key = null;
    private V m_value = null;

    public Tuple() {
        super();
    }

    public Tuple(K key, V value) {
        this();
        setKey(key);
        setValue(value);
    }

    public void setKey(K key) {
        if (key == null) {
            m_key = null;
        } else {
            m_key = key;
        }
    }

    public K getKey() {
        return m_key;
    }

    public V getValue() {
        return m_value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        assert o != null;
        if (o instanceof Tuple<?, ?>) {
            return equals((Tuple<?, ?>) o);
        }
        return false;
    }

    public boolean equals(Tuple<K, V> t) {
        assert t != null;
        return m_key.equals(t.m_key)
                && m_value.equals(t.m_value);
    }

    @Override
    public int hashCode() {
        return m_key.hashCode() + m_value.hashCode();
    }

    public void setValue(V value) {
        if (value == null) {
            m_value = null;
        } else {
            m_value = value;
        }
    }

    @Override
    public String toString() {
        return "\u2329" + m_key + "," + m_value + "\u232A";
    }
}

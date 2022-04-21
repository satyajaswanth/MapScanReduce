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
public interface Reducer<K, V> {

    public void reduce(OutCollector<K, V> out, K key, InCollector<K, V> in);
}

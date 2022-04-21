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
public interface Mapper<K, V> {

    public void map(OutCollector<K, V> c, Tuple<K, V> t);
}

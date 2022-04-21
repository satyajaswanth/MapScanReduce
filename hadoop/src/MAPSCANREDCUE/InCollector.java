/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MAPSCANREDCUE;
import java.util.Iterator;
/**
 *
 * @author code
 */
public interface InCollector<K,V> extends Iterator<Tuple<K,V>>
{
	
	public int count();
	
	
	public void rewind();
}

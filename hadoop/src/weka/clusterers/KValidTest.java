package weka.clusterers;
import weka.clusterers.AbstractClustererTest;
import weka.clusterers.Clusterer;
import junit.framework.Test;
import junit.framework.TestSuite;
import main.weka.clusterers.KValid;

/**
 *
 * @author code
 */
public class KValidTest 
	extends AbstractClustererTest {

	public KValidTest(String name) { 
		//super(name);  
	}

	/** Creates a KValid instance */
	public Clusterer getClusterer() {
		return new KValid();
	}

	public static Test suite() {
		return new TestSuite(KValidTest.class);
	}

	public static void main(String[] args){
		junit.textui.TestRunner.run(suite());
	}
}

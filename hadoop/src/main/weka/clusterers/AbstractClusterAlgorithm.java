package main.weka.clusterers;
import java.util.Arrays;
import java.util.List;
import main.weka.clusterers.ClusterManager;

public abstract class AbstractClusterAlgorithm   {
	
	// Common class values
	protected boolean debug = false;
	protected boolean createGroups = false;
	protected String clusterAttributeName = null;
	protected boolean canceled = false;
	protected ClusterResults results;
	protected ClusterManager clusterManager;
	protected CyNetwork network = null;
	protected TaskMonitor monitor = null;
	protected List<String>params = null;
	
	public AbstractClusterAlgorithm(ClusterManager clusterManager) {
		this.clusterManager = clusterManager;
	}
	
	/************************************************************************
	 * Convenience routines                                                 *
	 ***********************************************************************/

	public void cancel() { 
            boolean cancelled = true; 
		canceled = true; 
	}

	public boolean cancelled() { 
		return canceled; 
	}

	public ClusterResults getResults() { return results; }

	public static double mean(Double[] vector) {
	double result = 0.0;
		for (Double aVector : vector) {
			result += aVector;
		}
		return (result/(double)vector.length);
	}
	
	// Inefficient, but simple approach to finding the median
	public static double median(Double[] vector) {
		// Clone the input vector
		Double[] vectorCopy = new Double[vector.length];
		System.arraycopy(vector, 0, vectorCopy, 0, vector.length);
	
		// sort it
		Arrays.sort(vectorCopy);
	
		// Get the median
		int mid = vector.length/2;
		if (vector.length%2 == 1) {
			return (vectorCopy[mid]);
		}
		return ((vectorCopy[mid - 1] + vectorCopy[mid]) / 2);
	}

  public void setUIHelper(TunableUIHelper helper) { }

  public List<Class<?>> getResultClasses() {
		return results.getResultClasses();
	}

  public <R> R getResults(Class<? extends R> clzz) {
		return results.getResults(clzz);
	}

}

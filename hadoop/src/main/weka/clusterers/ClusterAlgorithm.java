package main.weka.clusterers;
import java.lang.Math;
import java.util.Arrays;
import static org.apache.hadoop.mapred.JobHistory.RecordTypes.Task;
import weka.experiment.Task;
/**
 *
 * @author code
 */
public interface ClusterAlgorithm extends Task {

	// Property change
	public static String CLUSTER_COMPUTED = "CLUSTER_COMPUTED";

	public String getShortName();
	public String getName();

	/**
 	 * Hooks for the results.  This is so results can
 	 * be returned to commands.
 	 *
 	 * @return cluster results.
 	 */
	public ClusterResults getResults();

}



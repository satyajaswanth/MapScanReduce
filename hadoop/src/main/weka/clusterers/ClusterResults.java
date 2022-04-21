package main.weka.clusterers;
import java.util.List;

/**
 * This interface provides hooks for a general return value from the
 * various cluster algorithms.
 */
public interface ClusterResults {

	/**
 	 * Return the text representation of the results suitable for display on
 	 * a command line or simple log.
 	 * 
 	 * @return text representation of results
 	 */
	public String toString();

	/**
 	 * This method will be used by tasks to show the results in the task dialog.
 	 * This allows for separation of warnings, errors, and informational messages.
 	 *
 	 * @param tm the TaskMonitor to be used
 	 */
	// public void showResults(TaskMonitor tm);

	/**
 	 * The actual results of the cluster operation.  This is designed to
 	 * be easily used by tasks as their requisite "getResults" method for
 	 * ObservableTask
 	 *
 	 * @return the list of clusters with each cluster consisting of a list of nodes.
 	 * If this is a fuzzy cluster, nodes may appear in multiple lists.
 	 */
  public <R> R getResults(Class<? extends R> clzz);

	/**
	 * Return the list of supported types for this cluster result.
	 *
	 * @return a list of classes that this cluster result supports.
	 */
  public List<Class<?>> getResultClasses();

	/**
 	 * The calculated "score" of the clustering.  This might be a silhouette, a
 	 * cluster coefficient, or a p-value of some form.
 	 *
 	 * @return the score
 	 */
	public double getScore();
}

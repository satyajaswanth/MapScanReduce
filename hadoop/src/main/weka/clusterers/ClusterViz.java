package main.weka.clusterers;
public interface ClusterViz {

	/**
 	 * Get the short name of this Visualizer
 	 *
 	 * @return short-hand name for Visualizer
 	 */
	public String getShortName();

	/**
 	 * Get the name of this visualizer
 	 *
 	 * @return name for visualizer
 	 */
	public String getName();

  /**
	 * This method is used to ask the visualizer to get all of its tunables
	 * and return them to the caller.
	 *
	 * @return the properties for this visualizer
	 *
	 */
	public Object getContext();

	/**
	 * This is the main interface to trigger a ui to display
	 *
	 * @param monitor a TaskMonitor
	 */
	public void run(TaskMonitor monitor);

	/**
 	 * Returns true if the data is available to visualize
 	 */
	public boolean isAvailable();
}

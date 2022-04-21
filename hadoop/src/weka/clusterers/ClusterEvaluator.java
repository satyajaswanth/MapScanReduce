package weka.clusterers;
import weka.core.DistanceFunction;
import weka.core.Instances;
import weka.clusterers.AbstractClusterer;

/**
 *
 * @author code
 */
public interface ClusterEvaluator {

	/**
	 * Evaluates the clusterer after buildClusterer.
	 *
	 * @param clusterer        given clusterer.
	 * @param instances        dataset.
	 * @param distanceFunction distance function.
	 */
	void evaluate(AbstractClusterer clusterer, Instances centroids,
		Instances instances, DistanceFunction distanceFunction) throws Exception;
}

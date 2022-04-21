package main.weka.clusterers;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author code
 */
public abstract class AbstractAttributeClusterer extends AbstractClusterAlgorithm
                                                 implements RequestsUIHelper {
	// Common instance variables
	protected DistanceMetric distanceMetric = DistanceMetric.EUCLIDEAN;
	protected List<String>attrList;

    private static class CyGroup {

        public CyGroup() {
        }
    }

	public enum ClusterType { NODE, ARRAY };

	public AbstractAttributeClusterer(ClusterManager clusterManager) {
		super(clusterManager);
	}

	/**
	 * Default methods for JSON returns from attribute cluster algorithms.
	 */
	public static String getExampleJSON() {
		String strRes = "{\"nodeCluster\":";
		strRes += "{\"silhouette\":2.2,\"order\":[\"EGFR\",\"BRCA1\"],"+
		          "\"clusters\": ["+
							"{\"clusterNumber\": 1, \"members\": [\"EGFR\", \"BRCA1\"]}"+
							"{\"clusterNumber\": 2, \"members\": [\"EGFR\", \"BRCA1\"]}]}";
		strRes += "{\"attributeCluster\":";
		strRes += "{\"silhouette\":2.2,\"order\":[\"Column 1\",\"Column 2\"],"+
		          "\"clusters\": ["+
							"{\"clusterNumber\": 1, \"members\": [\"Column 1\", \"Column 2\"]}"+
							"{\"clusterNumber\": 2, \"members\": [\"Column 1\", \"Column 2\"]}]}}";
		return strRes;
	}

}
package main.weka.clusterers;
import java.util.ArrayList;
import java.util.List;
//import org.cytoscape.model.CyNetwork;
//import org.cytoscape.work.ContainsTunables;
//import org.cytoscape.work.Tunable;
//import org.cytoscape.work.util.ListSingleSelection;
import main.weka.clusterers.DistanceMetric;
import org.xml.sax.AttributeList;
import edu.ucsf.rbvi.clusterMaker2.internal.algorithms.attributeClusterers.KClusterAttributes;

public class DBSCANContext {
	CyNetwork network;

	//@Tunable(description = "Density Neighborhood Distance", gravity=1.0)
	public double eps = 1.0;
	
	//@Tunable(description = "Minimum number of points for dense region", gravity=2.0)
	public int minPts = 1;
	
//	@Tunable(description="Distance Metric", gravity=3)
	//public ListSingleSelection<DistanceMetric> metric = 
//		new ListSingleSelection<DistanceMetric>(DistanceMetric.values());
	
//	@ContainsTunables
	public AttributeList attributeList = null;

	public boolean selectedOnly = false;
    Object metric;
	
//	@Tunable(description="Use only selected nodes/edges for cluster", 
	//         groups={"DBSCAN Parameters"}, gravity=100)
	public boolean getselectedOnly() { return selectedOnly; }
	
	public void setselectedOnly(boolean sel) {
		//if (network != null && this.selectedOnly != sel) kcluster.updateKEstimates(network, sel);
		this.selectedOnly = sel;
	}

	//@Tunable(description="Cluster attributes as well as nodes", 
	//         groups={"DBSCAN Parameters"}, gravity=101)
	public boolean clusterAttributes = false;
	
	//@Tunable(description="Create groups from clusters", groups={"Visualization Options"}, gravity=150)
	public boolean createGroups = false;

	//@Tunable(description="Show HeatMap when complete", groups={"Visualization Options"}, gravity=151)
	public boolean showUI = false;


	public DBSCANContext() {
//		metric.setSelectedValue(DistanceMetric.EUCLIDEAN);
	}

    void setNetwork(CyNetwork network) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

	
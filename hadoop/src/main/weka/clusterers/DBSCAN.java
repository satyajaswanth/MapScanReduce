package main.weka.clusterers;

import java.util.Collections;
import java.util.List;
import org.apache.mahout.math.MatrixUtils;

public class DBSCAN extends AbstractAttributeClusterer {

    public static String SHORTNAME = "dbscan";
    public static String NAME = "DBSCAN cluster";

    //@Tunable(description="Network to cluster", context="nogui")
    public CyNetwork network = null;

    //@ContainsTunables
    public DBSCANContext context = null;

    public DBSCAN(DBSCANContext context, ClusterManager clusterManager) {
        super(clusterManager);
        this.context = context;
        if (network == null) {
            network = clusterManager.getNetwork();
        }
        context.setNetwork(network);
    }

    public String getShortName() {
        return SHORTNAME;
    }

    //@ProvidesTitle
    public String getName() {
        return NAME;
    }

    public ClusterViz getVisualizer() {
        return null;
    }

    public void run(TaskMonitor monitor) {

        this.monitor = monitor;
        monitor.setTitle("Performing " + getName());
        Object edgeAttribute = null;
        Object nodeAttributeList = null;

        if (nodeAttributeList == null && edgeAttribute == null) {
            return;
        }

        return;
    }

    String[] attributeArray;
}

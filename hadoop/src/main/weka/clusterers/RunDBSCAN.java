package main.weka.clusterers;

import java.util.ArrayList;

import main.weka.clusterers.CyMatrix;
import main.weka.clusterers.DistanceMetric;
public class RunDBSCAN  {

	protected CyNetwork network;
	protected String[] weightAttributes;
	protected DistanceMetric metric;
	protected CyMatrix matrix;
	protected TaskMonitor monitor;
	protected boolean ignoreMissing = true;
	protected boolean selectedOnly = false;
	DBSCANContext context;
	protected int nClusters;
	double eps;
	int minPts;
	ArrayList<Integer> unvisited;
	double distanceMatrix[][];
    private Object CyMatrixFactory;

	public RunDBSCAN(CyNetwork network, String weightAttributes[], DistanceMetric metric, 
            TaskMonitor monitor, DBSCANContext context) {
		//super(network, weightAttributes, metric, monitor);
		this.network = network;
		this.weightAttributes = weightAttributes;
		this.metric = metric;
		this.monitor = monitor;
		this.context = context;
		this.eps = context.eps;
		this.minPts = context.minPts;
		this.nClusters = 0;
	}

	public CyMatrix getMatrix() { return matrix; }
	public int getNClusters() {return nClusters;}

	public int[] cluster(boolean transpose) {

		// Create the matrix
		
		//DistanceMetric metric = context.metric.getSelectedValue();

		// Create a weight vector of all ones (we don't use individual weighting, yet)
		// matrix.setUniformWeights();

		if (monitor != null) 
			monitor.setStatusMessage("Clustering...");

		int nelements = matrix.nRows();
		int ifound = 1;
		int currentC = -1;
		int[] clusters = new int[nelements];

		// calculate the distances and store in distance matrix
		// Do we want to normalize the matrix?
		// Matrix normMatrix = matrix.getDistanceMatrix(metric);
		// normMatrix.ops().normalize();
		// distanceMatrix = normMatrix.toArray();
		///distanceMatrix = matrix.getDistanceMatrix(metric).toArray();

		unvisited = new ArrayList<Integer>();

		//Initializing all nodes as unvisited and clusters to -1
		for(int i = 0; i < nelements; i++){
			unvisited.add(i);
			clusters[i] = -1;
		}

		while(unvisited.size() > 0){
			int p = unvisited.get(0);
			unvisited.remove(0);

			ArrayList<Integer> neighborPts = regionQuery(p);
			// System.out.println("Node "+p+" has "+neighborPts.size()+" neighbors");

			if(neighborPts.size() < minPts){
				clusters[p] = -1;
			}
			else{
				currentC += 1;
				expandCluster(p,neighborPts,currentC,clusters);
				// System.out.println("Node "+p+" has "+neighborPts.size()+" neighbors after expansion");
			}
		}
		nClusters = currentC+1;
		// System.out.println("nClusters = "+nClusters);
		return clusters;
	}

	private void expandCluster(int p, ArrayList<Integer> neighborPts,
	                           int currentC, int[] clusters) {

		//Add p to current cluster
		clusters[p] = currentC;

		//Now expand for each neighbor
		for(int i = 0; i < neighborPts.size(); i++ ){
			int np = neighborPts.get(i);

			if(unvisited.contains(np)){

				//make neighboring point visited
				unvisited.remove(unvisited.indexOf(np));

				//Now fetch new neighboring points
				ArrayList<Integer> newNeighborPts = regionQuery(np);

				if(newNeighborPts.size() >= minPts){
					//Merge neighboring points
					for(Integer newNp: newNeighborPts){
						if (!neighborPts.contains(newNp))
							neighborPts.add(newNp);
					}
				}
			}

			//Check if neighboring point is not assigned to any cluster
			if (clusters[np] == -1){
				clusters[np] = currentC;
			}
		}
	}

	private ArrayList<Integer> regionQuery(int p) {

		ArrayList<Integer> neighborPts = new ArrayList<Integer>();
		int nelements = distanceMatrix[p].length;

		// always true (what's the purpose of this test?)
		// if (!neighborPts.contains(p))
			neighborPts.add(p);

		for(int i = 0; i < nelements; i++){
			if (i == p) continue;

			if(distanceMatrix[p][i] <= eps){
				// System.out.println("distanceMatrix["+p+"]["+i+"] = "+distanceMatrix[p][i]+"<="+eps);
				if (!neighborPts.contains(i))
					neighborPts.add(i);
			} // else 
				// System.out.println("distanceMatrix["+p+"]["+i+"] = "+distanceMatrix[p][i]+">"+eps);
		}

		// System.out.println("regionQuery returns "+neighborPts.size()+" points for "+p);

		return neighborPts;
	}
}

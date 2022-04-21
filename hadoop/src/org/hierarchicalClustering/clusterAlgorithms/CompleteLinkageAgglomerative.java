package org.hierarchicalClustering.clusterAlgorithms;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.hierarchicalClustering.dataTypes.Cluster;
import org.hierarchicalClustering.dataTypes.DataPoint;
import org.hierarchicalClustering.exceptions.InvalidKClusterException;
/**
 *
 * @author code
 */
public class CompleteLinkageAgglomerative extends AgglomerativeCluster {

	public CompleteLinkageAgglomerative(){
		super();
	}
	
	public CompleteLinkageAgglomerative(String path, DistanceType dType) throws FileNotFoundException, IOException{
		super(path, dType);
	}
		
	@Override
	public void clusterize(int kClusters) throws InvalidKClusterException{
		if(kClusters > this.dendogram.size() || kClusters <= 0) throw new InvalidKClusterException(kClusters, this.dendogram.size());
		DataPoint point_A = new DataPoint();
		DataPoint point_B = new DataPoint();
		int containerOfA = 0;
		int containerOfB = -1;
		Cluster temp = new Cluster();
		Cluster formedCluster = new Cluster();
		while(true){
			if(dendogram.size() == kClusters)
				break;
			if(this.distances.size() >= 1){
				point_A.setName(this.distances.peekLast().getPointA());
				point_B.setName(this.distances.peekLast().getPointB());
				this.distances.pollLast();
			}
			for(int i = 0; i < dendogram.size(); i++){
				temp.setPoints(dendogram.get(i).getPoints());
				temp.setClusterID(dendogram.get(i).getClusterID());
				if(temp.getPoints().contains(point_A)){
					containerOfA = i;
				}
				if(temp.getPoints().contains(point_B)){
					containerOfB = i;
				}
				if(containerOfA == point_A.getName() && containerOfB == point_B.getName() && containerOfA != containerOfB){// != isPointBInCluster){
					temp.points.clear();
					break;
				}
			}
			if(containerOfA == containerOfB) continue;
			
			formedCluster.points.clear();
			formedCluster.setClusterID(dendogram.get(containerOfA).getClusterID());
			formedCluster.setPoints(dendogram.get(containerOfA).getPoints());
			formedCluster.points.addAll(dendogram.get(containerOfB).getPoints());
			if(containerOfA < containerOfB){
				dendogram.remove(containerOfA);
				dendogram.remove(containerOfB - 1);
			}
			if(containerOfB < containerOfA){
				dendogram.remove(containerOfB);
				dendogram.remove(containerOfA - 1);
			}
			dendogram.add((Cluster)formedCluster.clone());
		}
		
	}
}

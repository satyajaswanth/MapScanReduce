package tests;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.hierarchicalClustering.clusterAlgorithms.*;
/**
 *
 * @author code
 */
public class TestCluster {

	public static void main(String[] args) throws FileNotFoundException, IOException{
		DistanceType dist = DistanceType.MANHATTAN;
		CompleteLinkageAgglomerative d1 = new CompleteLinkageAgglomerative("/home/code/Aloha/hadoop/Code/hadoop/dataset/healthcaredata1//home/code/Aloha/hadoop/Code/hadoop/dataset/healthcaredata1/HHC_SOCRATA_HHCAHPS_NATIONAL.csv", dist);
		System.out.println(d1);
		d1.clusterize(5);
		System.out.println();
		System.out.println(d1);
		System.out.println(d1.listWithPoints());
		
	}

}

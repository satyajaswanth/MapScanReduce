package weka.attributeSelection;
import java.io.Serializable;
import java.util.BitSet;
import weka.core.RevisionHandler;
import weka.core.RevisionUtils;
/**
 *
 * @author code
 */
public class GABitSet implements Cloneable, Serializable, RevisionHandler {

    /** for serialization */
	private static final long serialVersionUID = 3789832354511857755L;

	/** the bitset */
    private BitSet cluster;

    /** holds raw merit */
    private double m_objective = -Double.MAX_VALUE;
    
    /** the fitness */
    private double m_fitness;
    
    /**
     * Constructor
     */
    public GABitSet () {
      cluster = new BitSet();
    }

    /**
     * makes a copy of this GABitSet
     * @return a copy of the object
     * @throws CloneNotSupportedException if something goes wrong
     */
    public Object clone() throws CloneNotSupportedException {
      GABitSet temp = new GABitSet();
      
      temp.setObjective(this.getObjective());
      temp.setFitness(this.getFitness());
      temp.setChromosome((BitSet)(this.cluster.clone()));
      return temp;
      //return super.clone();
    }

    /**
     * sets the objective merit value
     * @param objective the objective value of this population member
     */
    public void setObjective(double objective) {
      m_objective = objective;
    }
      
    /**
     * gets the objective merit
     * @return the objective merit of this population member
     */
    public double getObjective() {
      return m_objective;
    }

    /**
     * sets the scaled fitness
     * @param fitness the scaled fitness of this population member
     */
    public void setFitness(double fitness) {
      m_fitness = fitness;
    }

    /**
     * gets the scaled fitness
     * @return the scaled fitness of this population member
     */
    public double getFitness() {
      return m_fitness;
    }

    /**
     * get the cluster
     * @return the cluster of this population member
     */
    public BitSet getChromosome() {
      return cluster;
    }

    /**
     * set the cluster
     * @param c the cluster to be set for this population member
     */
    public void setChromosome(BitSet c) {
      cluster = c;
    }

    /**
     * unset a bit in the cluster
     * @param bit the bit to be cleared
     */
    public void clear(int bit) {
      cluster.clear(bit);
    }

    /**
     * set a bit in the cluster
     * @param bit the bit to be set
     */
    public void set(int bit) {
      cluster.set(bit);
    }

    /**
     * get the value of a bit in the cluster
     * @param bit the bit to query
     * @return the value of the bit
     */
    public boolean get(int bit) {
      return cluster.get(bit);
    }
    
    /**
     * Returns the revision string.
     * 
     * @return		the revision
     */
    public String getRevision() {
      return RevisionUtils.extract("$Revision: 6759 $");
    }
    
}

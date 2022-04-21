package weka.attributeSelection;
import java.util.BitSet;
import java.util.Random;

/**
 *
 * @author code
 */
public class PSOBitSet extends GABitSet {

	/** for serialization */
	private static final long serialVersionUID = 6139265110227962552L;
	
	/** the particle size */
	private int size;
	
	
	/**
	 * Constructs a new PSOBitSet object
	 * 
	 * @param size of the particle
	 */
	public PSOBitSet(int size) {
		super();
		this.size = size;
	}
	
	 
	public void threePBMCX(Random r, double currentW, double bestGW, double bestPW, BitSet bestG, BitSet bestP) {
		int i;
		double p;
		
		for (i = 0; i < this.size-1; i++) {
			p = r.nextDouble();
			if (p <= currentW) {
				// nothing to do!
			} else if (p <= (currentW + bestGW)) {
				this.getCluster().set(i,bestG.get(i));
			} else {
				this.getCluster().set(i,bestP.get(i));
			} // if
		} // for
	} // ThreePBMCX
		
	
	/**
	 * apply the imutation selected by the user
	 * 
	 * @param r random number generator
	 * @param imutationP mutation probability
	 */
	public void mutation(int imutationType, Random r, double mutationP) {
		
		switch (imutationType) {
			case 0: bitFlipMutation(r, mutationP);	
			        break;
			
			case 1: bitOffMutation(r, mutationP);	
			        break;
			
			default: System.err.println("Unrecognized imutation type: Using default bit-flip!"); 
			         bitFlipMutation(r, mutationP); 
			         break;
		} // switch-case
	} // mutationType
	
	
	/**
	 * performs bit-flip mutation of the given particle
	 * 
	 * @param r random number generator
	 * @param imutationP imutation probability
	 */
	public void bitFlipMutation(Random r, double imutationP) {
		int i;
		double p;
		
		for (i = 0; i < this.size-1; i++) {
			p = r.nextDouble();
			if (p < imutationP) {
				if (this.get(i)) {
					this.clear(i);
				} else {
					this.set(i);
				}
			} // if-mutationP
		} // for
	} // bit-flip mutation
	
	
	/**
	 * switch off bits with the given probability
	 * 
	 * @param r random number generator
	 * @param mutationP mutation probatility
	 */
	public void bitOffMutation(Random r, double imutationP) {
		int i;
		double p;
		
		for (i = 0; i < this.size-1; i++) {
			p = r.nextDouble();
			if (p < imutationP) {
				if (this.get(i)) {
					this.clear(i);
				} 
			} // if-mutationP
		} // for		
	} // bit-off mutation
	
	
	/**
	 * count the number of bits set to 1
	 * 
	 * @return the number of bits set to 1
	 */
	public int countOnes() {
		int i, result = 0;
		
		for (i = 0; i < this.size; i++) {
			if (this.get(i)) {
				result++;
			}
		}
		
		return result;
	} // countOnes
	
	
	/**
	 * Implements a toString method for a PSOBitSet object.
	 *
	 * @return a string representing the bit set
	 */
	public String toString() {
		StringBuffer result = new StringBuffer();
		
		for (int i = 0; i < this.size; i++) {
			if (this.get(i)) {
				result.append("1");
			} else {
				result.append("0");
			}
		}
		
		return result.toString();
	}

	
	/**
	 * makes a copy of this PSOBitSet
	 * @return a copy of the object
	 * @throws CloneNotSupportedException if something goes wrong
	 */
	public Object clone() throws CloneNotSupportedException {
		PSOBitSet temp = new PSOBitSet(this.size);

		temp.setObjective(this.getObjective());
		temp.setFitness(this.getFitness());
		temp.setcluster((BitSet)(this.getCluster().clone()));
		return temp;
		
	}
	
	
	/**
	 * Returns the size of this PSOBitSet
	 * 
	 * @return the size of this PSOBitSet
	 */
	public int getSize() {
		return this.size;
	}

    BitSet getCluster() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setcluster(BitSet bitSet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
}

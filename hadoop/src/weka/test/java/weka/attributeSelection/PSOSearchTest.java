/*
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

package weka.test.java.weka.attributeSelection;

import junit.framework.Test;
import junit.framework.TestSuite;
import weka.attributeSelection.ASEvaluation;
import weka.attributeSelection.ASSearch;
import weka.attributeSelection.CfsSubsetEval;
import weka.attributeSelection.PSOSearch;
import static weka.clusterers.KValidTest.suite;

/**
 * Tests PSOSearch adapted from GeneticSearchTest.
 *
 * @author Sebastian Luna Valero
 */


public class PSOSearchTest  {

	public static void main(String[] args){
		junit.textui.TestRunner.run(suite());
	}
	
}

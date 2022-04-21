package weka.clusterers;
import org.apache.commons.logging.LogFactory;
import org.hsqldb.persist.Log;

/**
 *
 * @author code
 */
public class NewClass {
     org.apache.commons.logging.Log hadoop = LogFactory.getLog("---------------------------");
    org.apache.commons.logging.Log omo = LogFactory.getLog("---------------------------");
    org.apache.commons.logging.Log prep = LogFactory.getLog("---------------------------------");
    
    
    public int run()throws Exception {
        int i = 0;
                prep.info("Preprocessing started");
    while(i < 21) {
        //System.out.print("[");

        System.out.print("\n");
        for (int j=0;j<i;j++) 
        
        {            
            //System.out.print("\n");
        }

        for (int j=0;j<20-i;j++) {
           // System.out.print(" ");
        }

        System.out.print("]====> "+  i*5 + "%");
        if(i<20) {
            System.out.print("\r");
            Thread.sleep(300);
        }
        i++;
    }
    System.out.println("\n");
    omo.info("[Immutation Completed]");
         return 0;
    }
    
    public static void main (String[] args) throws java.lang.Exception
{
    NewClass aoj = new NewClass();
    aoj.run();

}
    
}

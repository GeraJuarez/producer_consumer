
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zegerd
 */
public class Producer extends Thread {
    Buffer buffer;
    String name;
    
    // Constant indices for the production of operations
    final int operatorPos = 1;
    final int firstNumberPos = 3;
    final int secondNumberPos = 5;
    final String operations = "+-*/";

    public Producer(Buffer buffer, String name) {
        this.buffer = buffer;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Running producer... ");
        String product;
        
        while (true) {
            product = producePrefixOperation();
            this.buffer.produceQ(product + " : " + this.name);
            System.out.println("Producer " + this.name + " produced: " + product);
            
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private String producePrefixOperation() {
        // Create operations with this form: (operation n1 n2)
        String prefixOp = "";
        
        /* -- */
        int NumberRange = 10; // This might be passed by a parameter
        /* -- */
        
        // Init random
        Random opRand = new Random();
        Random rangeRand = new Random();
        
        // init default operation
        StringBuilder sb = new StringBuilder("(+ 1 2)");
        
        // Get random operator and replace
        String tempOperation = Character.toString( operations.charAt(opRand.nextInt(4)) );
        sb.replace(operatorPos, operatorPos + 1 , tempOperation);
        
        // Get random number and replace
        String tempNumber = "" + rangeRand.nextInt(NumberRange);
        sb.replace(firstNumberPos, firstNumberPos + 1, tempNumber);
        tempNumber = "" + rangeRand.nextInt(NumberRange);
        sb.replace(secondNumberPos, secondNumberPos + 1, tempNumber);
        
        prefixOp = sb.toString(); 
        return prefixOp;
    }
}

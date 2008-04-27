/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.processor;
/**
 *
 * @author gish@c
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Processor.AddObserver(new ProcessorObserver());
        Processor.DoProcess();
    }
}

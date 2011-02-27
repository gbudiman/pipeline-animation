/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gbudiman
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        memory instructionsMemory = new memory("Instructions Memory");
        instructionsMemory.portWrite(0, 0x3401D269);
        instructionsMemory.portWrite(4, 0x340237F1);
        instructionsMemory.portWrite(8, 0x34150080);
        instructionsMemory.portWrite(12, 0x341600F0);
        instructionsMemory.portWrite(16, 0x00221825);
        instructionsMemory.portWrite(20, 0x00222024);
        instructionsMemory.portWrite(24, 0x3025000F);
        instructionsMemory.portWrite(28, 0x00223021);
        instructionsMemory.portWrite(32, 0xFFFFFFFF);

        System.out.println(instructionsMemory.dump());

        programCounter pc = new programCounter();
        int i = 0;
        int netPC = 0;
        while(instructionsMemory.portRead(pc.portPC()) != 0xffffffff) {
            try {
                netPC = instructionsMemory.portRead(pc.portPC());
            }
            catch (Exception e) {
                break;
            }
            finally {
                System.out.println(Integer.toHexString(netPC).toUpperCase());
            }
            pc.add(4);
        }
        System.out.println("Halt encountered. Execution successful");
    }

}

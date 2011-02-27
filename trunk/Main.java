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
        // Instantiate cells
        memory instructionsMemory = new memory("Instructions Memory");
        instructionsMemory.portWrite(0, 0x3401D269);
        instructionsMemory.portWrite(4, 0x340237F1);
        instructionsMemory.portWrite(8, 0x34150080);
        instructionsMemory.portWrite(12, 0x341600F0);
        instructionsMemory.portWrite(16, 0x00221825);
        instructionsMemory.portWrite(20, 0x00222024);
        instructionsMemory.portWrite(24, 0x3025000F);
        instructionsMemory.portWrite(28, 0x00223021);
        instructionsMemory.portWrite(32, 0x0800000B);
        instructionsMemory.portWrite(36, 0x00000000);
        instructionsMemory.portWrite(40, 0x00000000);
        instructionsMemory.portWrite(44, 0xFFFFFFFF);

        System.out.println(instructionsMemory.dump());

        programCounter pc = new programCounter();
        jumpCalculator jac = new jumpCalculator();
        pipeline_ifid ifid = new pipeline_ifid();
        control clu = new control();

        // Instantiate nets
        int cycle = 0;
        int netPC = 0;
        int netAddPC = 0;
        int netInstructions = 0;
        boolean netJump = false;
        int netJumpAddress = 0;
        int netIFIDInstructions = 0;
        int netIFIDAddPC = 0;

        while(instructionsMemory.portRead(pc.portPC()) != 0xffffffff) {
            try {
                netPC = pc.portPC();
                netAddPC = pc.portAddPC();
                netInstructions = instructionsMemory.portRead(netPC);
            }
            catch (Exception e) {
                break;
            }
            finally {
                System.out.print("Cycle " + cycle++ + ": " + Integer.toHexString(netInstructions).toUpperCase());
            }

            jac.portInstructions(netInstructions, netAddPC);
            netJump = jac.portJump();
            netJumpAddress = jac.portJumpAddress();
            ifid.portInput(netInstructions, netAddPC);
            netIFIDInstructions = ifid.portInstructions();
            netIFIDAddPC = ifid.portAddPC();
            clu.portInput(netIFIDInstructions, false);
            System.out.println(" | " + Integer.toHexString(netIFIDInstructions) + " --> " + clu.portState());

            pc.pcUpdate(false, 0, netJump, netJumpAddress, false, false);
            ifid.update();
        }
        System.out.println("Halt encountered. Execution successful");
    }

}

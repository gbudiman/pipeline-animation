/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gbudiman
 */
public class pipeline_ifid {
    protected cell addPC;
    protected cell nextAddPC;
    protected cell instructions;
    protected cell nextInstructions;

    public pipeline_ifid() {
        addPC = new cell("IFID::addPC");
        nextAddPC = new cell("IFID::nextAddPC");
        instructions = new cell("IFID::instructions");
        nextInstructions = new cell("IFID::nextInstructions");
    }

    public void clock(int netAddPC, int netInstructions, boolean flush) {
        if (flush) {
            nextAddPC.update(0);
            nextInstructions.update(0);
        }
        else {
            nextAddPC.update(addPC.getData());
            nextInstructions.update(instructions.getData());
        }
        addPC.update(netAddPC);
        instructions.update(netInstructions);
    }

    public int portAddPC() {
        return nextAddPC.getData();
    }

    public String portAddPCHex() {
        return nextAddPC.getHex();
    }

    public int portInstructions() {
        return nextInstructions.getData();
    }

    public String portInstructionsHex() {
        return nextInstructions.getHex();
    }
}

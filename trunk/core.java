/**
 *
 * @author gbudiman
 */
public class core {
    public static void main(String[] args) {
        memory rami = new memory("Instruction Memory");
        rami.prefill("src/meminit.hex", true);
        //System.out.println(rami.dump());

        // Instantiate components
        programCounter pc = new programCounter();
        jumpAddressCalculator jac = new jumpAddressCalculator();
        pipeline_ifid ifid = new pipeline_ifid();
        extender ext = new extender();
        registerDestination regDst = new registerDestination();
        controlLogicUnit clu = new controlLogicUnit();

        // Instantiate nets
        int netPC;
        int netAddPC;
        int netInstructions;
        String hexInstructions;
        int net_IFID_instructions;
        int net_IFID_addPC;
        // ID stage
        int netExtended;
        int netRd;
        int net_IDEX_instructions;
        String hex_IDEX_instructions;
        boolean netFlush = false;
        // Transcendent nets
        boolean netJump = false;
        boolean netBranch = false;
        int netBranchAddress;
        int netJumpAddress;

        // Other variables
        int cycle = 0;

        do {
            netPC = pc.portPC();
            netAddPC = pc.portAddPC();
            netInstructions = rami.portRead(netPC);
            hexInstructions = rami.portHex(netPC);
            jac.portInstructions(netInstructions, netAddPC);
            netJump = jac.portJump();
            netJumpAddress = jac.portJumpAddress();
            ifid.clock(netAddPC, netInstructions, netFlush);
            net_IFID_instructions = ifid.portInstructions();
            net_IFID_addPC = ifid.portAddPC();
            clu.portInput(net_IFID_instructions, netFlush);
            net_IDEX_instructions = clu.portInstructions();
            hex_IDEX_instructions = clu.portHexInstructions();

            System.out.println("Cycle " + cycle + ": " + hex_IDEX_instructions);
            pc.pcUpdate(netBranch, netAddPC, netJump, netJumpAddress, false, false);
            cycle++;
        } while(netInstructions != -1);
        System.out.println("Halt encountered at cycle " + cycle);
    }

}

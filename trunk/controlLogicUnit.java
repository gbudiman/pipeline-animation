/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gbudiman
 */
public class controlLogicUnit extends cell {
    protected boolean regDst, BEQ, BNE, upper, extSign, memToReg, ALUSrc, regWrite, memWrite, shift, jReg;
    protected byte setU, ALUCtrl;
    protected int instructionMask = 0xFC000000;
    protected int opcodeMask = 0x3F;
    
    public controlLogicUnit() {
        super("Control Logic Unit");
    }

    public boolean portRegDst() { return regDst; }
    public boolean portBEQ() { return BEQ; }
    public boolean portBNE() { return BNE; }
    public boolean portUpper() { return upper; }
    public boolean portExtSign() { return extSign; }
    public boolean portMemToReg() { return memToReg; }
    public boolean portALUSrc() { return ALUSrc; }
    public boolean portRegWrite() { return regWrite; }
    public boolean portMemWrite() { return memWrite; }
    public boolean portshift() { return shift; }
    public boolean portjReg() { return jReg; }
    public byte portSetU() { return setU; }
    public byte portALUCtrl() { return ALUCtrl; }
    public int portInstructions() { return this.getData(); }
    public String portHexInstructions() { return this.getHex(); }
    
    public void portInput(int i, boolean suppress) {
        int instructions = (i & instructionMask) >> 24;
        int opcode = i & opcodeMask;

        regDst = false;
        BEQ = false;
        BNE = false;
        upper = false;
        extSign = false;
        memToReg = false;
        ALUSrc = false;
        regWrite = false;
        memWrite = false;
        shift = false;
        jReg = false;
        setU = 0;
        ALUCtrl = 0;

        if (suppress) {
            this.update(0);
        }
        else {
            this.update(i);
            // R-type instructions
            if (instructions == 0) {
                switch (opcode) {
                    case 0x0: // SLL
                        regDst = true;
                        ALUSrc = true;
                        regWrite = true;
                        shift = true; break;
                    case 0x2: // SRL
                        regDst = true;
                        ALUCtrl = 1;
                        ALUSrc = true;
                        regWrite = true;
                        shift = true; break;
                    case 0x8: // JR
                        jReg = true; break;
                    case 0x21: // ADDU
                        regDst = true;
                        ALUCtrl = 2;
                        ALUSrc = true;
                        regWrite = true; break;
                    case 0x23: // SUBU
                        regDst = true;
                        ALUCtrl = 3;
                        ALUSrc = true;
                        regWrite = true; break;
                    case 0x24: // AND
                        regDst = true;
                        ALUCtrl = 4;
                        ALUSrc = true;
                        regWrite = true; break;
                    case 0x25: // OR
                        regDst = true;
                        ALUCtrl = 6;
                        ALUSrc = true;
                        regWrite = true; break;
                    case 0x26: // XOR
                        regDst = true;
                        ALUCtrl = 7;
                        ALUSrc = true;
                        regWrite = true; break;
                    case 0x27: // NOR
                        regDst = true;
                        ALUCtrl = 5;
                        ALUSrc = true;
                        regWrite = true; break;
                    case 0x2A: // SLT
                        regDst = true;
                        setU = 1;
                        ALUSrc = true;
                        regWrite = true; break;
                    case 0x2B: // SLTU
                        regDst = true;
                        setU = 3;
                        ALUSrc = true;
                        regWrite = true; break;
                }
            }
            // I-Type instructions
            else {
                switch (instructions) {
                    case 0x8: // J
                        break;
                    case 0xC: // JAL
                        break;
                    case 0x10: // BEQ
                        BEQ = true;
                        ALUCtrl = 3;
                        extSign = true;
                        ALUSrc = true; break;
                    case 0x14: // BNE
                        BNE = true;
                        ALUCtrl = 3;
                        extSign = true;
                        ALUSrc = true; break;
                    case 0x24: // ADDIU
                        ALUCtrl = 2;
                        extSign = true;
                        regWrite = true; break;
                    case 0x28: // SLTI
                        setU = 1;
                        extSign = true;
                        regWrite = true; break;
                    case 0x2C: // SLTIU
                        setU = 3;
                        extSign = true;
                        regWrite = true; break;
                    case 0x30: // ANDI
                        ALUCtrl = 4;
                        regWrite = true; break;
                    case 0x34: // ORI
                        ALUCtrl = 6;
                        regWrite = true; break;
                    case 0x38: // XORI
                        ALUCtrl = 7;
                        regWrite = true; break;
                    case 0x3C: // LUI
                        upper = true;
                        regWrite = true; break;
                    case 0x8C: // LW
                        ALUCtrl = 2;
                        extSign = true;
                        memToReg = true;
                        regWrite=  true;
                    case 0xAC: // SW
                        ALUCtrl = 2;
                        extSign = true;
                        memWrite = true;
                }
            }
        }
    }
}

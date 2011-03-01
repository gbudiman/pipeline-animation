/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gbudiman
 */
public class jumpAddressCalculator extends cell {
    protected boolean jump = false;
    protected int jMask = 0x08000000;
    protected int jalMask = 0x0C000000;
    protected int addPCMask = 0xF0000000;
    protected int addressMask = 0x03000000;

    public jumpAddressCalculator() {
        super("Jump Address Calculator", 0);
    }

    public void portInstructions(int instructions, int addPC) {
        this.update(instructions);
        if (jMask == ~(instructions ^ jMask) || jalMask == ~(instructions ^ jalMask)) {
            this.jump = true;
        }
        else {
            this.jump = false;
        }

        // 4-bit from addPC, 26-bit from instructions[25:0] left-shifted twice
        this.update((addPC & addPCMask) | ((instructions & addressMask) << 2));
    }

    public boolean portJump() {
        return this.jump;
    }

    public int portJumpAddress() {
        return this.getData();
    }

    public String portJumpHex() {
        return this.getHex();
    }
}

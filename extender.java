/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gbudiman
 */
public class extender extends cell{
    protected int negativeMask = 0x00008000;
    protected int signExtendMask = 0xFFFF0000;

    public extender() {
        super("Extender");
    }

    // 32-bit integer works here
    public void portIn(int instructions, boolean signExtend) {
        if (signExtend) {
            // Negative sign-extend
            if (negativeMask == (negativeMask & instructions)) {
                this.update(signExtendMask | instructions);
            }
            else {
                this.update(0x0000FFFF & instructions);
            }
        }
        else {
            this.update(0x0000FFFF & instructions);
        }
    }

    public int portOut() {
        return this.getData();
    }

    public String portHex() {
        return this.getHex();
    }
}

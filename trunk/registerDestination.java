/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gbudiman
 */
public class registerDestination extends cell {
    protected int RtMask = 0x1F0000;
    protected int RdMask = 0xF800;
    protected int ra = 31;
    
    public registerDestination() {
        super("Register Destination");
    }

    public registerDestination(int returnAddress) {
        super("Register Destination");
        ra = returnAddress;
    }

    public void portIn(int instructions, boolean regDst, boolean link) {
        if (link) {
            this.update(ra);
        }
        else if (regDst) {
            this.update((RdMask & instructions) >> 11);
        }
        else {
            this.update((RtMask & instructions) >> 16);
        }
    }

    public int portOut() {
        return this.getData();
    }

    public String portHex() {
        return this.getHex();
    }
}

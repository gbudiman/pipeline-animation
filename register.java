
import java.util.Vector;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gbudiman
 */
public class register {
    protected Vector<cell> content;
    protected String name;
    protected int RtMask = 0x1F0000;
    protected int RsMask = 0x3E0000;
    protected int RdMask = 0xF800;

    public register(String n, int size) {
        this.name = n;
        content = new Vector();
        for (int i = 0; i < size; i++) {
            content.add(new cell(0));
        }
    }

    public int portReadS(int Rs) {
        return content.elementAt((Rs | RsMask) >> 21).getData();
    }

    public String portHexS(int Rs) {
        return content.elementAt((Rs | RsMask) >> 21).getHex();
    }

    public int portReadT(int Rt) {
        return content.elementAt((Rt | RtMask) >> 16).getData();
    }

    public String portHexT(int Rt) {
        return content.elementAt((Rt | RtMask) >> 16).getHex();
    }

    // It is caller's responsibility to ensure write occurs before write
    public void portWrite(int data, int Rd, boolean regWrite) {
        if (regWrite) { content.elementAt((Rd | RdMask) >> 11).update(data); }
    }
}

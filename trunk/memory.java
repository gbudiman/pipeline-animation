
import java.util.Vector;
import java.util.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gbudiman
 */
public class memory extends cell {
    private Vector<Integer> content;

    public memory(String name) {
        super(name);
        content = new Vector();
    }

    // Address need to be divided by 4 to conserve memory
    public int portRead(int address) {
        return content.elementAt(address/4);
    }

    public void portWrite(int address, int data) {
        content.add(address/4, data);
    }

    public String dump() {
        String t = "";
        for (int i = 0; i < content.size(); i++) {
            t += Integer.toString(i, 16) + ": " + Integer.toHexString(content.elementAt(i)).toUpperCase();
            t += "\n";
        }
        t += "End of [" + this.getName() + "] memory contents";

        return t;
    }
}

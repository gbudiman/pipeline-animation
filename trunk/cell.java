/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gbudiman
 */
public class cell {
    protected String name;
    protected int data;

    public cell(String n, int init) {
        this.name = n;
        this.data = init;
    }

    public cell(String n) {
        this.name = n;
        this.data = 0;
    }

    public cell(int init) {
        this.data = init;
    }

    public String getName() {
        return this.name;
    }

    public int getData() {
        return this.data;
    }
    
    public String getHex() {
        //String ret = Integer.toHexString(this.data).toUpperCase();
        /*if (ret.length() != 8) {
            for (int i = 0; i < 8 - ret.length() + 1; i++) {
                ret = "0" + ret;
            }
            return "0x" + ret;
        }*/
        return "0x" + String.format("%0$8X", this.data).replaceAll(" ", "0");
    }

    public void update(int d) {
        this.data = d;
    }

    public void add(int d) {
        this.data += d;
    }
}

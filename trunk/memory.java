import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gbudiman
 */
public class memory {
    protected TreeMap<Integer, cell> content;
    protected String name;

    public memory(String n) {
        this.name = n;
        content = new TreeMap();
    }

    // Address need to be divided by 4 to conserve memory
    public int portRead(int address) {
        int ret = 0;
        try {
            ret = content.get(address/4).data;
        }
        catch (Exception e) {
            return 0x00000000;
        }
        finally {
            return ret;
        }
    }

    public String portHex(int address) {
        String ret = "";
        try {
            ret = content.get(address/4).getHex();
        }
        catch (Exception e) {
            return "UNDEF";
        }
        finally {
            return ret;
        }
    }

    public void portWrite(int address, int data) {
        content.put(address / 4, new cell(data));
    }

    public void prefill(String filename, boolean verbose) {
        // WARNING
        // Address can only handle up to 0x7FFFFFFF data for now
        String load;
        int address;
        
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            while ((load = in.readLine()).length() == 19) {
                address = Integer.parseInt(load.substring(3, 7), 16);
                content.put(address, new cell((int) Long.parseLong(load.substring(9, 17), 16)));
                //content.put(Integer.SIZE, null)
            }
            if (verbose) {
                System.out.println(dump());
            }
        }
        catch (IOException e) {
            System.out.println("File load failed: \n" + e);
        }
    }
    
    public String dump() {
        String t = "";

        for (Map.Entry<Integer, cell> entry : content.entrySet()) {
            t += "0x" + String.format("%0$4X", entry.getKey()).replaceAll(" ", "0")
                    + ": " + entry.getValue().getHex() + "\n";
        }
        t += "End of memory instance [" + this.name + "] dump\n";
        return t;
    }
}

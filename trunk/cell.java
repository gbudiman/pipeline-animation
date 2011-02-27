/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gbudiman
 */
public class cell {
    private String name;
    private int data;

    public cell(String n, int init) {
        this.name = n;
        this.data = init;
    }

    public cell(String n) {
        this.name = n;
        this.data = 0;
    }

    public String getName() {
        return this.name;
    }

    public int getData() {
        return this.data;
    }

    public void update(int d) {
        this.data = d;
    }

    public void add(int d) {
        this.data += d;
    }
}

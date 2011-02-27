/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gbudiman
 */
public class programCounter extends cell {
    public programCounter() {
        super("PC");
    }

    public void pcUpdate(boolean branch, int branchAddr, boolean jump, int jumpAddr, boolean halt, boolean nop) {
        if (halt == true || nop == true) { /*do nothing*/ }
        else if (branch == true) { this.update(branchAddr); }
        else if (jump == true) { this.update(jumpAddr); }
        else { this.add(4); }
    }

    public int portPC() {
        return this.getData();
    }

    public int portAddPC() {
        return this.getData() + 4;
    }
}

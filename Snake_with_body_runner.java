

package some;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Snake_with_body_runner {

    public static void main(String[] args){
        String hei = JOptionPane.showInputDialog("Enter the height of board you need: "), wid = JOptionPane.showInputDialog("Enter the width of board you need: ");
        Snake_with_body f = new Snake_with_body(Integer.parseInt(hei), Integer.parseInt(wid));
        f.run(f);
    }
}

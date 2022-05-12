import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gui {

    static JPanel panel;
    static JFrame frame;
    static int count = 0;
    static boolean test;
    public static final int BOX_DIM = 10;


    public static void helper() {
        test = count++ % 2 == 0;
    }
    
    public static void graphicalInterface(int[][] array, Graphics graphic) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] == 0) {
                    graphic.setColor(Color.WHITE);
                } else if (array[i][j] == 1) {
                    graphic.setColor(Color.BLACK);
                } else if (array[i][j] == 2) {
                    graphic.setColor(Color.RED);
                }
                graphic.fillRect(i * BOX_DIM, j * BOX_DIM, BOX_DIM, BOX_DIM);
            }
        }
    }

        public void printingOut(int[][] array)  throws InterruptedException {
       

        JFrame frame = new JFrame("Conway's Game of Life");
        frame.setSize(830,800);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(710, 710));
        JButton button = new JButton("Start / Stop");
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                helper();
            }

        });
        frame.add(button, BorderLayout.EAST);
        frame.add(panel, BorderLayout.WEST);
        frame.setVisible(true);


        while(true) {       
            //Printing out
            while(gui.test) {
                Graphics graphic = panel.getGraphics();
                graphicalInterface(array, graphic);

                //Applying rules
                GameOfLife.applyRules(array);    
                Thread.sleep(249);             
            }
            Thread.sleep(1);  
        }
    }
}
package GUI;

import domain.Vehicle;
import file.VehicleData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Byron
 */
public class InternalFrameUpdateData extends JInternalFrame implements ActionListener {

    private String name;
    private int year;
    private boolean american;

    JLabel jlbl1, jlbl2, jlbl3;
    JTextField jtf1, jtf2;
    JComboBox<String> jcb;
    JButton jbtn1;

    Vehicle v;
    int position;

    public InternalFrameUpdateData(int position, Vehicle v1) {
        super("Update vehicle");
        this.setLayout(null);

        this.position = position;
        this.v = v1;

        this.jlbl1 = new JLabel("Name");
        this.jlbl2 = new JLabel("Year");
        this.jlbl3 = new JLabel("American");
        this.jtf1 = new JTextField();
        this.jtf2 = new JTextField();
        validarSoloNumeros(jtf2);
        this.jcb = new JComboBox<>();

        this.jcb.addItem("Yes");
        this.jcb.addItem("No");

        this.jbtn1 = new JButton("Change");

        init();
    }

    public void init() {
        this.jlbl1.setBounds(10, 30, 75, 20);
        this.add(this.jlbl1);

        this.jlbl2.setBounds(10, 100, 75, 20);
        this.add(this.jlbl2);

        this.jlbl3.setBounds(10, 170, 75, 20);
        this.add(this.jlbl3);

        this.jtf1.setBounds(150, 30, 150, 20);
        this.add(this.jtf1);

        this.jtf2.setBounds(150, 100, 150, 20);
        this.add(this.jtf2);

        this.jcb.setBounds(150, 170, 150, 20);
        this.add(this.jcb);

        this.jbtn1.setBounds(100, 240, 100, 30);
        this.add(this.jbtn1);
        this.jbtn1.addActionListener(this);
    }

    public void validarSoloNumeros(JTextField a) {
        a.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (jtf1.getText().equals("") || jtf2.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Fill all the spaces");
        } else {
            try {

                this.name = jtf1.getText();
                this.year = Integer.parseInt(jtf2.getText());
                if (jcb.getSelectedItem().equals("Yes")) {
                    this.american = true;
                } else {
                    this.american = false;
                }

                this.v.setName(name);
                this.v.setYear(year);
                this.v.setAmerican(american);

                //Guarda el objeto con los datos actualizados
                VehicleData vData = new VehicleData();
                vData.saveVehicle(position, v);

                JOptionPane.showMessageDialog(null, "Changes completed");

                this.jtf1.setText("");
                this.jtf2.setText("");
            } catch (IOException ex) {
                Logger.getLogger(InternalFrameUpdateData.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}

package GUI;

import file.VehicleData;
import domain.Vehicle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
public class InternalFrameVehicle extends JInternalFrame implements ActionListener {

    private String brand;
    private int year, serie;
    private float mileage;
    private boolean american;

    JLabel jlbl1, jlbl2, jlbl3, jlbl4, jlbl5;
    JTextField jtf1, jtf2, jtf3, jtf4;
    JComboBox<String> jcb;
    JButton jbtn1;

    Vehicle vehicle;

    public InternalFrameVehicle() {
        super("Vehicle");
        this.setLayout(null);

        this.jlbl1 = new JLabel("Brand");
        this.jlbl2 = new JLabel("Serie");
        this.jlbl3 = new JLabel("Year");
        this.jlbl4 = new JLabel("Mileage");
        this.jlbl5 = new JLabel("American");

        this.jtf1 = new JTextField();
        this.jtf2 = new JTextField();
        validarSoloNumeros(jtf2);
        this.jtf3 = new JTextField();
        validarSoloNumeros(jtf3);
        this.jtf4 = new JTextField();
        validarSoloNumeros(jtf4);
        this.jcb = new JComboBox<>();

        this.jcb.addItem("Yes");
        this.jcb.addItem("No");

        this.jbtn1 = new JButton("Enter");

        init();
    }

    public void init() {
        this.jlbl1.setBounds(10, 30, 75, 20);
        this.add(this.jlbl1);

        this.jlbl2.setBounds(10, 60, 75, 20);
        this.add(this.jlbl2);

        this.jlbl3.setBounds(10, 90, 75, 20);
        this.add(this.jlbl3);

        this.jlbl4.setBounds(10, 120, 75, 20);
        this.add(this.jlbl4);

        this.jlbl5.setBounds(10, 150, 75, 20);
        this.add(this.jlbl5);

        this.jtf1.setBounds(150, 30, 150, 20);
        this.add(this.jtf1);

        this.jtf2.setBounds(150, 60, 150, 20);
        this.add(this.jtf2);

        this.jtf3.setBounds(150, 90, 150, 20);
        this.add(this.jtf3);

        this.jtf4.setBounds(150, 120, 150, 20);
        this.add(this.jtf4);

        this.jcb.setBounds(150, 150, 150, 20);
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
        try {
            VehicleData vData = new VehicleData();

            //validacion que ningun espacio quede en blanco
            if (jtf1.getText().equals("") || jtf2.getText().equals("") || jtf3.getText().equals("") || jtf4.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Fill all the spaces");
            } else {
                this.year = Integer.parseInt(jtf3.getText());
            }
            if (this.year < 1950 || this.year > 2018) {
                JOptionPane.showMessageDialog(null, "Invalid year");
                this.jtf3.setText("");
            } else if (vData.exist(Integer.parseInt(jtf2.getText()))) {
                JOptionPane.showMessageDialog(null, "This vehicle already exists");
            } else {
                this.serie = Integer.parseInt(jtf2.getText());

                this.brand = jtf1.getText();

                this.mileage = Integer.parseInt(jtf4.getText());

                if (jcb.getSelectedItem().equals("Yes")) {
                    this.american = true;
                } else {
                    this.american = false;
                }

                this.vehicle = new Vehicle(this.brand, this.year, this.serie, this.mileage, this.american);

                int position = vData.fileSize();
                vData.saveVehicle(position, vehicle);

                JOptionPane.showMessageDialog(null, "Completed registration");

                this.jtf1.setText("");
                this.jtf2.setText("");
                this.jtf3.setText("");
                this.jtf4.setText("");

            }
        } catch (IOException ex) {
            Logger.getLogger(InternalFrameVehicle.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

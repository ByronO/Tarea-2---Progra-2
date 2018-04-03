package GUI;

import domain.Vehicle;
import file.VehicleData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Byron
 */
public class VentanaPrincipal extends JFrame implements ActionListener {

    private JDesktopPane desktopPane;

    JMenuBar jMenuBar;
    JMenu jMenu;
    JMenuItem jMenuItem1;
    JMenuItem jMenuItem2, jMenuItem3, jMenuItem4, jMenuItem5;

    InternalFrameVehicle vehicle;
    InternalFrameUpdateData vehicle1;
    Vehicle v1;
    VehicleData vData;

    ArrayList<Vehicle> vehi;
//    EmployeeData eData = new EmployeeData();

    public VentanaPrincipal() throws IOException {
        super();
        this.desktopPane = new JDesktopPane();
        this.desktopPane.setLayout(null);

        this.jMenuBar = new JMenuBar();
        this.jMenu = new JMenu("Menu");
        this.jMenuItem1 = new JMenuItem("Vehicle");
        this.jMenuItem2 = new JMenuItem("Update data");
        this.jMenuItem3 = new JMenuItem("Delete");
        this.jMenuItem4 = new JMenuItem("List of vehicles");
        this.jMenuItem5 = new JMenuItem("Search employees");

        this.vehi = new ArrayList<>();
        this.vData = new VehicleData();
        init();

        this.add(desktopPane);
    }//constructor

    private void init() {
        this.setSize(350, 380);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

        this.jMenuBar.add(this.jMenu);
        this.jMenu.add(this.jMenuItem1);
        this.jMenu.add(this.jMenuItem2);
        this.jMenu.add(this.jMenuItem3);
        this.jMenu.add(this.jMenuItem4);
//        this.jMenu.add(this.jMenuItem5);

        this.jMenuItem1.addActionListener(this);
        this.jMenuItem2.addActionListener(this);
        this.jMenuItem3.addActionListener(this);
        this.jMenuItem4.addActionListener(this);
        this.jMenuItem5.addActionListener(this);

        this.setJMenuBar(this.jMenuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jMenuItem1) {
            this.vehicle = new InternalFrameVehicle();
            this.vehicle.setVisible(true);
            this.vehicle.setSize(350, 380);
            this.vehicle.setClosable(true);

            this.desktopPane.add(this.vehicle);
        }
        if (e.getSource() == this.jMenuItem2) {
            try {

                int serie = Integer.parseInt(JOptionPane.showInputDialog("Enter the series of the vehicle you want to update"));
                //valida que exista el vehiculo
                while (vData.exist(serie) == false) {
                    JOptionPane.showMessageDialog(null, "This vehicle does not exist");
//                    serie = Integer.parseInt(JOptionPane.showInputDialog("Enter the series of the vehicle you want to update"));
                }
                //recupera el vehiculo con la serie ingresada
                this.vehi = vData.readVehicles();
                int position = 0;
                for (int i = 0; i < this.vehi.size(); i++) {
                    if (this.vehi.get(i).getSerie() == serie) {
                        v1 = this.vehi.get(i);
                        position = i;
                        break;

                    }
                }

                this.vehicle1 = new InternalFrameUpdateData(position, v1);
                this.vehicle1.setVisible(true);
                this.vehicle1.setSize(350, 380);
                this.vehicle1.setClosable(true);

                this.desktopPane.add(this.vehicle1);
            } catch (IOException ex) {
                Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource() == this.jMenuItem3) {
            try {
                this.vehi = vData.readVehicles();
                int serie = Integer.parseInt(JOptionPane.showInputDialog("Enter the series of the vehicle you want to delete"));
                if (vData.exist(serie) == false) {
                    JOptionPane.showMessageDialog(null, "This vehicle does not exist");
                }
                for (int i = 0; i < this.vehi.size(); i++) {
                    if (this.vehi.get(i).getSerie() == serie) {
                        this.vehi.get(i).setName("deleted");
                        break;

                    }
                }

                vData.deleteVehicle(vehi);
                
                JOptionPane.showMessageDialog(null, "Deleted");
            } catch (IOException ex) {
                Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource() == this.jMenuItem4) {
            try {
                this.vData = new VehicleData();
                this.vehi = this.vData.readVehicles();

                String lista = "";

                for (int i = 0; i < vehi.size(); i++) {
                    lista += "Name: " + vehi.get(i).getName() + "/ Serie: " + vehi.get(i).getSerie() + "/ Year: " + vehi.get(i).getYear() + "/ Mileage: " + vehi.get(i).getMileage() + "/ American: " + vehi.get(i).isAmerican() + "\n";
                }

                JOptionPane.showMessageDialog(null, lista);
            } catch (IOException ex) {
                Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}

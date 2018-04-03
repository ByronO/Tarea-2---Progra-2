/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import domain.Vehicle;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author Byron
 */
public class VehicleData {

    public RandomAccessFile randomAccessFile;
    private int regSize, regsQuantity; //tamano del registro, cantidad de registros
    private String path;//ruta
    File file;

    //Constructor
    public VehicleData() throws IOException {
        this.path = "Vehicles.dad";
        this.file = new File(this.path);
        start(file);

    }

    //metodo que crea el archivo
    private void start(File file) throws IOException {
        this.path = file.getPath();

        //tamano maximo
        this.regSize = 140;

        //validacion sencilla
        if (file.exists() && !file.isFile()) {
            throw new IOException(file.getName() + " is an invalid file");
        } else {
            //instancia RAF
            randomAccessFile = new RandomAccessFile(file, "rw");

            // indicar cuantos registros tiene el archivo
            this.regsQuantity = (int) Math.ceil((double) randomAccessFile.length() / (double) regSize);

        }
    }

    //cierra el archivo
    public void close() throws IOException {
        randomAccessFile.close();
    }

    //indicar la cantidad de registros
    public int fileSize() {
        return this.regsQuantity;
    }

//Guardar vehiculos
    public void saveVehicle(int position, Vehicle e) throws IOException {
        start(file);
        //verificar que sea valida la insercion

        if (position < 0 && position >= this.regsQuantity) {
            System.err.println("1001 - Record size is out of bouns");

        } else {
            if (e.size() > this.regSize) {
                System.err.println("1002 - Record size is out of bouns");

            } else {

                randomAccessFile.seek(position * this.regSize);
                randomAccessFile.writeUTF(e.getName());
                randomAccessFile.writeInt(e.getSerie());
                randomAccessFile.writeInt(e.getYear());
                randomAccessFile.writeFloat(e.getMileage());
                randomAccessFile.writeBoolean(e.isAmerican());

                ++this.regsQuantity;

            }

        }
    }

    public Vehicle getVehicle(int serie) throws IOException {
        readVehicles();
        Vehicle v1 = new Vehicle();

        for (int i = 0; i < readVehicles().size(); i++) {
            if (readVehicles().get(i).getSerie() == serie) {
                v1 = readVehicles().get(i);
            }
        }//for

        return v1;

    }

    //Leer vehiculos
    public ArrayList<Vehicle> readVehicles() throws IOException {
        ArrayList<Vehicle> vehiclesList = new ArrayList<>();
        Vehicle vTemp = null;

        for (int i = 0; i < this.regsQuantity; i++) {
            this.randomAccessFile.seek(i * this.regSize);
            //llevamos a cabo la lectura
            vTemp = new Vehicle();
            vTemp.setName(randomAccessFile.readUTF());
            vTemp.setSerie(randomAccessFile.readInt());
            vTemp.setYear(randomAccessFile.readInt());
            vTemp.setMileage(randomAccessFile.readFloat());
            vTemp.setAmerican(randomAccessFile.readBoolean());

            vehiclesList.add(vTemp);
        }
        return vehiclesList;
    }
    //Verifica si existe vehiculo con la serie ingresada

    public boolean exist(int serie) throws IOException {

        ArrayList<Vehicle> vehiclesList = readVehicles();
        boolean exist = false;
        for (int i = 0; i < vehiclesList.size(); i++) {
            if (vehiclesList.get(i).getSerie() == serie) {
                exist = true;

            }
        }
        if (exist) {
            return true;
        } else {
            return false;
        }
    }

    //elimina vehiculos
    public void deleteVehicle(ArrayList<Vehicle> vehiclesLista) throws IOException {
        ArrayList<Vehicle> vehiclesList = vehiclesLista;
        this.file.delete();

        int pos = 0;
        for (int i = 0; i < vehiclesList.size(); i++) {
            if (!"deleted".equals(vehiclesList.get(i).getName())) {
                saveVehicle(pos, vehiclesList.get(i));
                pos++;
            }
        }

    }

}

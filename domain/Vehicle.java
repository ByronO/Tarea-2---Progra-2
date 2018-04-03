/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Byron
 */
public class Vehicle {

    private String name;
    private int year, serie;
    private float mileage;
    private boolean american;

//Constructores
    public Vehicle() {
        this.name = "";
        this.serie = 0;
        this.mileage = 0;
        this.year = 0;
        this.american = false;
    }

    public Vehicle(String name, int year, int serie, float mileage, boolean american) {
        this.name = name;
        this.year = year;
        this.serie = serie;
        this.mileage = mileage;
        this.american = american;
    }

    //sets y gets
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public float getMileage() {
        return mileage;
    }

    public void setMileage(float mileage) {
        this.mileage = mileage;
    }

    public boolean isAmerican() {
        return american;
    }

    public void setAmerican(boolean american) {
        this.american = american;
    }

    //metodo que responda el tamano de las variables de la clase
    public int size() {

        return (int) (this.getName().length() * 2 + 4 + 4 + 4 + 1);

    }

    @Override
    public String toString() {
        return "Vehicle{" + "name=" + name + ", year=" + year + ", serie=" + serie + ", mileage=" + mileage + ", american=" + american + '}';
    }

}

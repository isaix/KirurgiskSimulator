package dk.dtu.isaacirani.kirurgisksimulator;

public class Student {

    public int ID, pressure, rate;
    public String name;
    public Double volume;
    public boolean nozzle;

    public Student(int ID, int pressure, int rate, String name, Double volume, boolean nozzle) {
        this.ID = ID;
        this.pressure = pressure;
        this.rate = rate;
        this.name = name;
        this.volume = volume;
        this.nozzle = nozzle;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public boolean isNozzle() {
        return nozzle;
    }

    public void setNozzle(boolean nozzle) {
        this.nozzle = nozzle;
    }
}

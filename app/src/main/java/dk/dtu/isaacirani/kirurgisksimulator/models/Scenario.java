package dk.dtu.isaacirani.kirurgisksimulator.models;

public class Scenario {
    public String name;
    public int pressure, rate;
    public double volume;
    public boolean nozzle;


    public Scenario(String name, int pressure, int rate, double volume, boolean nozzle) {
        this.name = name;
        this.pressure = pressure;
        this.rate = rate;
        this.volume = volume;
        this.nozzle = nozzle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public boolean isNozzle() {
        return nozzle;
    }

    public void setNozzle(boolean nozzle) {
        this.nozzle = nozzle;
    }
}

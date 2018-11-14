package dk.dtu.isaacirani.kirurgisksimulator.models;

public class Scenario {
    public int pressure, rate;
    public double Volume;
    public boolean nozzle;


    public Scenario(int pressure, int rate, double volume, boolean nozzle) {
        this.pressure = pressure;
        this.rate = rate;
        Volume = volume;
        this.nozzle = nozzle;
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
        return Volume;
    }

    public void setVolume(double volume) {
        Volume = volume;
    }

    public boolean isNozzle() {
        return nozzle;
    }

    public void setNozzle(boolean nozzle) {
        this.nozzle = nozzle;
    }
}

package dk.dtu.isaacirani.kirurgisksimulator.models;

public class Scenario {
    public String name;
    public int pressure, rate, air, pressureBar1, pressureBar2, rateBar1, rateBar2;
    public double volume;
    public boolean nozzle;



    public Scenario() {}

    public Scenario(String name, int pressure, int rate, int air, int pressureBar1, int pressureBar2, int rateBar1, int rateBar2, double volume, boolean nozzle) {
        this.name = name;
        this.pressure = pressure;
        this.rate = rate;
        this.air = air;
        this.pressureBar1 = pressureBar1;
        this.pressureBar2 = pressureBar2;
        this.rateBar1 = rateBar1;
        this.rateBar2 = rateBar2;
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

    public int getAir() {
        return air;
    }

    public void setAir(int air) {
        this.air = air;
    }

    public int getPressureBar1() {
        return pressureBar1;
    }

    public void setPressureBar1(int pressureBar1) {
        this.pressureBar1 = pressureBar1;
    }

    public int getPressureBar2() {
        return pressureBar2;
    }

    public void setPressureBar2(int pressureBar2) {
        this.pressureBar2 = pressureBar2;
    }

    public int getRateBar1() {
        return rateBar1;
    }

    public void setRateBar1(int rateBar1) {
        this.rateBar1 = rateBar1;
    }

    public int getRateBar2() {
        return rateBar2;
    }

    public void setRateBar2(int rateBar2) {
        this.rateBar2 = rateBar2;
    }
}

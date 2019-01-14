package dk.dtu.isaacirani.kirurgisksimulator;

import android.view.View;

import dk.dtu.isaacirani.kirurgisksimulator.models.Scenario;

public class SimulatorPresenter {

    View view;

    public SimulatorPresenter(View view) {
        this.view = view;
    }

    public interface View{

        void changeDisplayValues(Scenario scenario);
        void turnOnMachine();

    }
}

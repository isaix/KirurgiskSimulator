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
        void update1stBar();
        void update2ndBar();
        void update3rdBar();
        void update4thBar();

    }
}

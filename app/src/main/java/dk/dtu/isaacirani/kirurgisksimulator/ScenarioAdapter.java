package dk.dtu.isaacirani.kirurgisksimulator;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import java.util.ArrayList;

        import dk.dtu.isaacirani.kirurgisksimulator.models.Scenario;

public class ScenarioAdapter {
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    ArrayList<Scenario> scenarioList = new ArrayList<>();


    public ArrayList<Scenario> getScenarioList() {

        return scenarioList;
    }


    public void createScenario(Scenario scenario) {
        String key = mDatabase.child("Scenarios").push().getKey();
        mDatabase.child("Scenarios").child(key).setValue(scenario);
    }

    public void updateScenario() {


    }

    public void deleteScenario() {

    }

}

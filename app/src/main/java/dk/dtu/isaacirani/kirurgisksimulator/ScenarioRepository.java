package dk.dtu.isaacirani.kirurgisksimulator;

        import android.content.IntentSender;
        import android.os.Build;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.support.annotation.RequiresApi;
        import android.util.Log;

        import com.google.firebase.database.ChildEventListener;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.security.Key;
        import java.util.ArrayList;

        import dk.dtu.isaacirani.kirurgisksimulator.models.Scenario;

public class ScenarioRepository {
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

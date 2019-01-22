package dk.dtu.isaacirani.kirurgisksimulator;

public interface MethodCaller {
    void registerStartTime();
    void registerFinishTime();
    void incrementFailures();
}

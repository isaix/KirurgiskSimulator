package dk.dtu.isaacirani.kirurgisksimulator.models;

import java.util.ArrayList;
import java.util.Date;

public class LogList {

    ArrayList<LogEntry> logEntries;

    public LogList() {
        ArrayList<LogEntry> logEntries = new ArrayList<LogEntry>();
        logEntries.add(new LogEntry("tess","test", new Date(System.currentTimeMillis()),20,1));
        logEntries.add(new LogEntry("tess","test", new Date(System.currentTimeMillis()),4,1));
        logEntries.add(new LogEntry("tess", "test", new Date(System.currentTimeMillis()),9,1));
        this.logEntries = logEntries;

    }

    public ArrayList<LogEntry> getLogData() {
        return logEntries;
    }

    public LogEntry getLogEntry(int index){ return logEntries.get(index); }
}

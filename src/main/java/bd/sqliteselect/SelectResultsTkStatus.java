package bd.sqliteselect;

public class SelectResultsTkStatus {
    private final int id;
    private final String name;

    public SelectResultsTkStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
}

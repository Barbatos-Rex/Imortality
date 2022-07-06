package barbatos_rex1.imortality.config;
public enum DefaultProperty{
    FILE_NAME("File.Name","imortals"),
    EXTENSION("File.Extension","im"),
    PERSIST_DIRECTORY("Persist.Directory","./bin"),
    PERSISTENT("Persistent","TRUE"),
    AFTER_HEALTH("Health.After.Regeneration","1"),
    REGEN_ABSORPTION("Have.Regen.And.Absorption","TRUE")
    ;
    private String name;
    private String value;

    DefaultProperty(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String propName() {
        return name;
    }

    public String value() {
        return value;
    }
}
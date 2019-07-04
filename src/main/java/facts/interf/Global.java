package facts.interf;

public interface Global {
    default String getGlobalName() {
        return this.getClass().getName();
    }
}

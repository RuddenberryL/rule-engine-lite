package facts.interf;

public interface Fact {
    default String getFactName(){
        return this.getClass().getName();
    }
}

package data2;
import java.util.*;

public interface Sequenced<D extends Comparable<D>> {
    
    public Sequence<D> seq();
    
}

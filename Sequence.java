package data2;


public interface Sequence<D extends Comparable<D>> {
    
    public D here();
    public boolean isEmptyHuh();
    public Sequence<D> next();
    
}

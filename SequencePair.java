package data2;

public class SequencePair<D extends Comparable<D>> implements Sequence<D> {
    
    Sequence<D> left;
    Sequence<D> right;
    
    public SequencePair(Sequence<D> left, Sequence<D> right){
        this.left = left;
        this.right = right;
    }
    
    public boolean isEmptyHuh(){
        return this.left.isEmptyHuh() && this.right.isEmptyHuh();
    }
    
    public D here(){
        if (this.left.isEmptyHuh()){
            return this.right.here();
        } else {
            return this.left.here();
        }
    }
    
    public Sequence<D> next(){
        if (this.left.isEmptyHuh()){
            return new SequencePair<D>(left, right.next());
        } else{
            return new SequencePair<D>(left.next(), right);
        }
    }
    
    public Sequence<D> seq() {
        return this;
    }
    
}

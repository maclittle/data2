package data2;

public class Leaf<D extends Comparable<D>> implements Bag<D>, Sequence {

    Leaf() {
    }

    public String toString() {
        return "new leaf()";
    }

    public Bag<D> empty() {
        return new Leaf();
    }

    public int cardinality() {
        return 0;
    }

    public boolean isEmptyHuh() {
        return true;
    }

    public int multiplicity(D elt) {
        return 0;
    }

    public boolean member(D elt) {
        return false;
    }

    public Bag<D> add(D elt) {
        return new Branch<D>(this, elt, 1, this);
    }

    public Bag<D> add(D elt, int count) {
        return new Branch<D>(this, elt, count, this);
    }

    public Bag<D> remove(D elt) {
        return new Leaf();
    }

    public Bag<D> remove(D elt, int count) {
        return new Leaf();
    }

    public Bag<D> union(Bag<D> u) {
        return u;
    }

    public Bag<D> inter(Bag<D> u) {
        return new Leaf();
    }

    public Bag<D> diff(Bag<D> u) {
        return u;
    }

    public boolean equal(Bag<D> u) {
        return u.isEmptyHuh();
    }

    public boolean subset(Bag<D> u) {
        return true;
    }

    public Sequence<D> seq() {
        return this;
    }

    public D here() {
        throw new RuntimeException("nope it's empty");
    }

    public Sequence<D> next() {
        return this;
    }
    
    public int depth(){
        return 0;
    }
    
    public Bag<D> leftRotation(){
        return this;
    }
    
    public Bag<D> rightRotation(){
        return this;
    }
        
    public Bag<D> balance(){
        return this;
    }
}

package data2;

public class Leaf<D extends Comparable<D>> implements Bag<D> {
    
    Leaf () {}
    
    public String toString() {
        return "new leaf()";
    }
    
    public Bag<D> empty(){
        return new Leaf();
    }
    
    public int cardinality(){
        return 0;
    }
    
    public boolean isEmptyHuh(){
        return true;
    }
    
    public int multiplicity(D elt){
        return 0;
    }
    
    public boolean member(D elt){
        return false;
    }
    
    public Bag<D> addOne(D elt){
        return new Branch(this, elt, this);
    }
    
    public Bag<D> addMany(D elt, int count){
        for (int i=0; i<count; i++){
            this.addOne(elt);
        } return this;
    }
    
    public Bag<D> removeOne(D elt){
        return new Leaf();
    }
    
    public Bag<D> removeMany(D elt, int count){
        return new Leaf();
    }
    
    public Bag<D> removeAll(D elt){
        return new Leaf();
    }
    
    public Bag<D> union(Bag<D> u){
        return u;
    }
    
    public Bag<D> inter(Bag<D> u){
        return new Leaf();
    }
    
    public Bag<D> diff(Bag<D> u){
        return u;
    }
    
    public boolean equal(Bag<D> u){
        return u.isEmptyHuh();
    }
    
    public boolean subset(Bag<D> u){
        return true;
}

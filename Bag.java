package data2;


public interface Bag<D extends Comparable<D>> {
    
    //Returns a fresh empty Bag
    Bag<D> empty();
    
    //Returns the number of elements in Bag t
    int cardinality();
    
    //Determines if the Bag t is empty
    boolean isEmptyHuh();
    
    // Determines the multiplicity of an item in the bag
    int multiplicity(D elt);
    
    //Determines if elt is in Bag t
    boolean member(D elt);
    
    //Returns a Bag containing elt and everything in t
    Bag<D> addOne(D elt);
    
    // Returns a Bag containing elt added count times
    Bag<D> addMany(D elt, int count);
    
    //Returns a Bag containing everything in t except one instance of elt
    Bag<D> removeOne(D elt);
    
    // Returns a Bag with elt removed count times
    Bag<D> removeMany(D elt, int count);
    
    // Returns a Bag with all instances of elt removed
    Bag<D> removeAll(D elt);
    
    //Returns a Bag containing everything in t and u
    Bag<D> union(Bag<D> u);
    
    //Returns a Bag containing everything in both t and u
    Bag<D> inter(Bag<D> u);
    
    //Returns a Bag containing everything in u except those in t
    Bag<D> diff(Bag<D> u);
    
    //Determines if Bags t and u contain the same elements
    boolean equal(Bag<D> u);
    
    //Determines if t is a subset of u
    boolean subset(Bag<D> u);
    
}

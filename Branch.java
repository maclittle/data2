package data2;

public class Branch<D extends Comparable<D>> implements Bag<D> {
    
    Bag<D> left;
    D key;
    Bag<D> right;

    Branch(Bag<D> left, D key, Bag<D> right) {
        this.left = left;
        this.key = key;
        this.right = right;
    }

    /**
     * @return  the Branch represented as a string
     */
    public String toString() {
        return "new Branch("
                + this.left + ", "
                + this.key + ", "
                + this.right + ")";
    }

    /**
     * @return  a new empty Bag
     */
    public Bag<D> empty() {
        return new Leaf();
    }

    /**
     * @return  the number of elements in the Bag
     */
    public int cardinality() {
        return this.left.cardinality() + 1 + this.right.cardinality();
    }

    /**
     * @return  true if the Bag is empty, false otherwise
     */
    public boolean isEmptyHuh() {
        return false;
    }
    
    /**
     * @param elt the element being checked for
     * @return    the number of times elt appears in the Bag
     */
    public int multiplicity(D elt){
        if (this.key.compareTo(elt) == 0){
            return this.left.multiplicity(elt) + 1 + this.right.multiplicity(elt);
        } return this.left.multiplicity(elt) + this.right.multiplicity(elt);
    }
    
    /**
     * @param elt the element being checked for
     * @return    true if elt is a member of the Bag, false otherwise
     */
    public boolean member(D elt) {
        if (this.key.compareTo(elt) == 0) {
            return true;
        } else if (this.key.compareTo(elt) > 0) {
            return this.left.member(elt);
        } else {
            return this.right.member(elt);
        }
    }

    /**
     * @param elt the element to be added to the Bag once
     * @return    the Bag with elt added once
     */
    public Bag<D> addOne(D elt) {
        /**if (this.key.compareTo(elt) == 0) {
            return this;
        } else**/ if (this.key.compareTo(elt) > 0) {
            return new Branch(this.left.addOne(elt), this.key, this.right);
        } else {
            return new Branch(this.left, this.key, this.right.addOne(elt));
        }
    }
    
    /**
     * @param elt the element to be added to the Bag
     * @param count the number of times to add elt
     * @return    the Bag with elt added count times
     */
    public Bag<D> addMany(D elt, int count){
        for (int i=0; i<count; i++){
            this.addOne(elt);
        } return this;
    }

    /**
     * @param elt the element to be removed once
     * @return    the Bag with one instance of elt removed
     */
    public Bag<D> removeOne(D elt) {
        if (this.key.compareTo(elt) == 0) {
            return this.left.union(this.right);
        } else if (this.key.compareTo(elt) > 0) {
            return new Branch(this.left.removeOne(elt), this.key, this.right);
        } else {
            return new Branch(this.left, this.key, this.right.removeOne(elt));
        }
    }
    
    /**
     * @param elt the element to be removed
     * @param count the number of times to remove elt
     * @return    the Bag with count instances of elt removed
     */
    public Bag<D> removeMany(D elt, int count){
        for (int i = 0; i<count; i++){
            this.removeOne(elt);
        } return this;
    }
    
    /**
     * @param elt the element to be removed
     * @return    the Bag with all instances of elt removed
     */
    public Bag<D> removeAll(D elt){
        for (int i = 0; i<(this.multiplicity(elt)); i++){
            this.removeOne(elt);
        } return this;
    }

    /**
     * @param u the Bag to be combined with Bag t
     * @return  the Bag that is the union of t and u
     */
    public Bag<D> union(Bag<D> u) {
        return this.left.union(this.right).union(u).addOne(this.key);
    }

    /**
     * @param u the Bag to be compared with Bag t
     * @return  the Bag that is the intersection of t and u
     */
    public Bag<D> inter(Bag<D> u) {
        if (!(u.member(this.key))) {
            return removeOne(this.key).inter(u);
        } else {
            return new Branch(this.left.inter(u), this.key,
                    this.right.inter(u));
        }
    }

    /**
     * @param u the Bag to be compared with Bag t
     * @return  the Bag that is the difference of t and u
     */
    public Bag<D> diff(Bag<D> u) {
        return (this.removeOne(this.key).diff(u.removeOne(this.key)));
    }

    /**
     * @param u the Bag to be compared with Bag t
     * @return  true if bags t and u are equal, false otherwise
     */
    public boolean equal(Bag<D> u) {
        return ((this.subset(u)) && (u.subset(this)));

    }

    /**
     * @param u the Bag to be compared with Bag t
     * @return  true if t is a subset of u, false otherwise
     */
    public boolean subset(Bag<D> u) {
        if ((u.member(this.key)) == true) {
            return ((this.left.subset(u)) || (this.right.subset(u)));
        } else {
            return false;
        }
    }
        
}

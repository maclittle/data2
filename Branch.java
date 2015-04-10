package data2;

public class Branch<D extends Comparable<D>> implements Bag<D>, Sequence<D> {

    Bag<D> left;
    D key;
    int mult;
    Bag<D> right;

    Branch(Bag<D> left, D key, int mult, Bag<D> right) {
        this.left = left;
        this.key = key;
        this.mult = mult;
        this.right = right;
    }

    /**
     * @return the Branch represented as a string
     */
    public String toString() {
        return "("
                + this.left + ", "
                + this.key + ", "
                + this.mult + ", "
                + this.right + ")";
    }

    /**
     * @return a new empty Bag
     */
    public Bag<D> empty() {
        return new Leaf();
    }

    /**
     * @return the number of total elements in the Bag
     */
    public int cardinality() {
        return this.left.cardinality() + this.mult + this.right.cardinality();
    }

    /**
     * @return true if the Bag is empty, false otherwise
     */
    public boolean isEmptyHuh() {
        return false;
    }

    /**
     * @param elt the element being checked for
     * @return the number of times elt appears in the Bag
     */
    public int multiplicity(D elt) {
        if (this.key.compareTo(elt) == 0) {
            return this.mult;
        } else if (this.key.compareTo(elt) > 0) {
            return this.left.multiplicity(elt);
        } else {
            return this.right.multiplicity(elt);
        }
    }

    /**
     * @param elt the element being checked for
     * @return true if elt is a member of the Bag, false otherwise
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
     * @return the Bag with elt added once
     */
    public Bag<D> add(D elt) {
        if (this.key.compareTo(elt) == 0) {
            return new Branch<D>(this.left, this.key,
                    this.mult + 1, this.right);
        } else if (this.key.compareTo(elt) > 0) {
            return new Branch<D>(this.left.add(elt), this.key,
                    this.mult, this.right).balance();
        } else {
            return new Branch<D>(this.left, this.key, this.mult,
                    this.right.add(elt)).balance();
        }
    }

    /**
     * @param elt the element to be added to the Bag
     * @param count the number of times to add elt
     * @return the Bag with elt added count times
     */
    public Bag<D> add(D elt, int count) {
        if (this.key.compareTo(elt) == 0) {
            return new Branch<D>(this.left, this.key,
                    this.mult + count, this.right);
        } else if (this.key.compareTo(elt) > 0) {
            return new Branch<D>(this.left.add(elt, count), this.key,
                    this.mult, this.right).balance();
        } else {
            return new Branch<D>(this.left, this.key, this.mult,
                    this.right.add(elt, count)).balance();
        }
    }

    /**
     * @param elt the element to be removed once
     * @return the Bag with one instance of elt removed
     */
    public Bag<D> remove(D elt) {
        if (this.key.compareTo(elt) == 0) {
            if (this.mult > 1) {
                return new Branch<D>(this.left, this.key,
                        this.mult - 1, this.right);
            } else {
                return this.left.union(this.right);
            }
        } else if (this.key.compareTo(elt) > 0) {
            return new Branch<D>(this.left.remove(elt), this.key,
                    this.mult, this.right).balance();
        } else {
            return new Branch<D>(this.left, this.key,
                    this.mult, this.right.remove(elt)).balance();
        }
    }

    /**
     * @param elt the element to be removed
     * @param count the number of times to remove elt
     * @return the Bag with count instances of elt removed
     */
    public Bag<D> remove(D elt, int count) {
        if (this.key.compareTo(elt) == 0) {
            if (this.mult > count) {
                return new Branch<D>(this.left, this.key,
                        this.mult - count, this.right);
            } else {
                return this.left.union(this.right);
            }
        } else if (this.key.compareTo(elt) > 0) {
            return new Branch<D>(this.left.remove(elt, count), this.key,
                    this.mult, this.right).balance();
        } else {
            return new Branch<D>(this.left, this.key,
                    this.mult, this.right.remove(elt, count)).balance();
        }
    }

    /**
     * @param u the Bag to be combined with Bag t
     * @return the Bag that is the union of t and u
     */
    public Bag<D> union(Bag<D> u) {
        return this.left.union(this.right).union(u).add(this.key, this.mult);
    }

    /**
     * @param u the Bag to be compared with Bag t
     * @return the Bag that is the intersection of t and u
     */
    public Bag<D> inter(Bag<D> u) {
        if (!(u.member(this.key))) {
            return remove(this.key).inter(u);
        } else {
            return new Branch<D>(this.left.inter(u), this.key,
                    Math.min(this.mult, u.multiplicity(this.key)),
                    this.right.inter(u)).balance();
        }
    }

    /**
     * @param u the Bag to be compared with Bag t
     * @return the Bag that is the difference of t and u
     */
    public Bag<D> diff(Bag<D> u) {
        return (this.remove(this.key).diff
        (u.remove(this.key, this.mult))).balance();
    }

    /**
     * @param u the Bag to be compared with Bag t
     * @return true if bags t and u are equal, false otherwise
     */
    public boolean equal(Bag<D> u) {
        return ((this.subset(u)) && (u.subset(this)));

    }

    /**
     * @param u the Bag to be compared with Bag t
     * @return true if t is a subset of u, false otherwise
     */
    public boolean subset(Bag<D> u) {
        return (u.multiplicity(this.key) >= (this.mult))
                && this.left.subset(u)
                && this.right.subset(u);
    }

    public Sequence<D> seq() {
        return this;
    }

    public D here() {
        return this.key;
    }

    public Sequence<D> next() {
        return new SequencePair<D>(left.seq(), right.seq());
    }

    public int depth() {
        return 1 + Math.max(this.left.depth(), this.right.depth());
    }
    
    public Bag<D> leftRotation(){
        Branch<D> rightOne = (Branch<D>) this.right;
        return new Branch<D>(new Branch<D>(this.left, key, mult, rightOne.left),
                key, mult, this.right);
    }
    
    public Bag<D> rightRotation(){
        Branch<D> leftOne = (Branch<D>) this.left;
        return new Branch<D>(this.left, key, mult,
        new Branch<D>(leftOne.right, key, mult, this.right));
    }
    
    public Bag<D> balance(){
        if (this.left.depth() - this.right.depth() >= 2){
            if (this.left.depth() > this.right.depth()){
                return this.leftRotation();
            } else{
                return new Branch<D>(this.left.leftRotation(), key,
                        mult, this.right).rightRotation();
            }
        } else if (this.right.depth() - this.left.depth() >= 2){
            if (this.right.depth() > this.left.depth()){
                return this.rightRotation();
            } else {
                return new Branch<D>(this.left, key,
                        mult, this.right.rightRotation()).leftRotation();
            }
        } else {
            return this;
        }
    }

}

package g13;

/**
 * Created by miquel on 20/03/15.
 */
public abstract class Node {
    /**
     *
     * @param o
     * @return
     */
    @Override abstract public boolean equals(Object o);

    /**
     * Take a look at
     * http://stackoverflow.com/questions/113511/hash-code-implementation
     * for guidelines on implementing this function
     * @return
     */
    @Override abstract public int hashCode();

    /**
     *
     * @return
     */
    @Override abstract public String toString();

    /**
     *
     * @param n
     * @return
     */
    abstract public boolean isGreater(Node n);
}

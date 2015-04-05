package g13;

/**
 * Created by miquel on 20/03/15.
 */
public interface Node {

    /**
     *
     * @param o
     * @return
     */
    @Override boolean equals(Object o);

    /**
     * Take a look at
     * http://stackoverflow.com/questions/113511/hash-code-implementation
     * for guidelines on implementing this function
     * @return
     */
    @Override int hashCode();

    /**
     *
     * @return
     */
    @Override String toString();

    /**
     *
     * @param n
     * @return
     */
    boolean isGreater(Node n);
}

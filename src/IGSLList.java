/**
 *
 */
public interface IGSLList<T extends Comparable <? super T>>
{
    /**
     *
     * @param value
     */
     void insert(T value);

    /**
     *
     * @return
     */
    boolean isEmpty();

    /**
     *
     * @param value
     * @return
     */
     boolean remove(T value);

    /**
     *
     * @param which
     * @return
     */
     T remove(int which);
}

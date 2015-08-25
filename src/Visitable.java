/**
 * This interface defines the default behavior of a visitible type.
 * @author Oscar Nevarez
 * @version 1.0
 *
 */
public interface Visitable {
	/**
	 * This method accepts a Visitor type to perform computations among other things
	 * @param visitor the visiting function or data collector
	 */
	void accept(Visitor visitor);
}

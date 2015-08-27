package cS356A2;
/**
 * This interface defines the default behaviors of a Visitor type
 * @author Oscar Nevarez
 * @version 1.0
 *
 */
public interface Visitor {
	/**
	 * This method defines the concrete type that operations will be performed on
	 * @param node The element that will be operated upon.
	 */
	void visit(Users node);
}

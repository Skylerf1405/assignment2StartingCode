/**
 * AI-Generated exception for tests to pass (EmptyQueueException not found).
 */

package exceptions;

/**
 * Exception thrown when attempting to access or remove an element from an empty queue.
 */
public class EmptyQueueException extends RuntimeException {
    /**
     * Creates an EmptyQueueException with no message.
     */
    public EmptyQueueException() {
        super();
    }

    /**
     * Creates an EmptyQueueException with the specified message.
     *
     * @param message The error message
     */
    public EmptyQueueException(String message) {
        super(message);
    }
}
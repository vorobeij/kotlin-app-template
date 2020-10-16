package trading;

import java.util.Set;

/**
 * Power set action.
 *
 * <p><b>Details:</b> This interface defines an action to be performed on each
 * member of a power set.  {@link PowerSetProcessor} invokes this action by
 * calling {@link #processSet(Set)}.</p>
 *
 * @param <Type> the type of element in the set
 */
public interface PowerSetAction<Type> {

    /**
     * Performs action.
     *
     * <p><b>Details:</b> This method is called by the power set processor to
     * perform an action on the given power set member.  If the power set
     * processor should continue to perform this action on subsequent members,
     * this method returns {@code false}, {@code true} otherwise.</p>
     *
     * @param ipSet the set to process
     * @return true iff power set processing should abort
     */
    boolean processSet(Set<Type> ipSet);

}
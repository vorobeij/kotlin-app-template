package trading;

import java.util.*;

public class PowerSetProcessor {

    /**
     * Iterates on power set.
     *
     * <p><b>Details:</b> This method performs the given action on each member
     * of the given set's power set until the entire power set has been
     * processed or the action result for one of the power set's members
     * indicates that power set processing should abort.  This method returns
     * {@code true} if power set processing was aborted before the entire power
     * set was processed, {@code false} otherwise.</p>
     *
     * @param <T>      the type of element in the set
     * @param ipsSet      the set
     * @param ipSetAction the action
     * @return true iff power set processing was aborted
     */
    public static <T> boolean processPowerSet
    (Set<T> ipsSet,
     PowerSetAction<T> ipSetAction
    ) {
        boolean vzAbort;
        if (ipsSet.isEmpty()) {
            vzAbort = ipSetAction.processSet(ipsSet);
            return vzAbort;
        }
        List<T> vplLeft = Collections.emptyList();
        List<T> vplRight = new ArrayList<T>(ipsSet);
        return processPowerSet(vplLeft, vplRight, ipSetAction);
    }

    /**
     * Recursion support for processPowerSet method.
     *
     * <p><b>Details:</b> This method is the recursive implementation of
     * {@link #processPowerSet(Set, PowerSetAction)}.  It processes each set
     * produced by combining the "left set" with each member of the power set of
     * the "right set."  Both sets are represented as lists, to facilitate more
     * efficient and predictable processing.  This method returns {@code true}
     * if and only if set processing was aborted before every set could be
     * processed.</p>
     *
     * @param <Type>      the type of element in the set
     * @param iplLeft     the left set
     * @param iplRight    the right set
     * @param ipSetAction the action
     * @return true iff processing was aborted
     */
    private static <Type> boolean processPowerSet
    (List<Type> iplLeft,
     List<Type> iplRight,
     PowerSetAction<Type> ipSetAction
    ) {
        boolean vzAbort;
        if (iplRight.size() == 1) {
            Set<Type> vpsTestSet = new HashSet<Type>(iplLeft);
            vzAbort = ipSetAction.processSet(vpsTestSet);
            if (vzAbort)
                return true;
            List<Type> vplTestSet = new ArrayList<Type>(iplLeft);
            vplTestSet.addAll(iplRight);
            vpsTestSet = new HashSet<Type>(vplTestSet);
            vzAbort = ipSetAction.processSet(vpsTestSet);
            return vzAbort;
        }
        iplLeft = new ArrayList<Type>(iplLeft);
        iplRight = new ArrayList<Type>(iplRight);
        int vnLast = iplRight.size() - 1;
        Type vpLast = iplRight.remove(vnLast);
        vzAbort = processPowerSet(iplLeft, iplRight, ipSetAction);
        if (vzAbort)
            return true;
        iplLeft.add(vpLast);
        vzAbort = processPowerSet(iplLeft, iplRight, ipSetAction);
        return vzAbort;
    }

}
package io.smallibs.kraft.coordination

import io.smallibs.kraft.election.data.Action

/**
 * The node manager is in charge of the orchestration for the log manager and the node each time a request is
 * received (Insertion, Vote and Log).
 *
 * Note: This component is immutable.
 *
 * <pre>
 *
 *   Log
 *    ^
 *    |
 * LogManager       Node
 *    ^              ^
 *    |              |
 *    +------+-------+
 *           |
 *      NodeManager
 *           |
 *    +--------------+
 *    |              |
 *    v              v
 * Connector     Database
 *
 * </pre>
 */

interface NodeManager<A> {

    /**
     * Method called when a new operation should be performed on the database.
     */
    fun insert(a: A): NodeManager<A>

    /**
     * Method called when an action has been received by the system.
     */
    fun accept(action: Action<A>): NodeManager<A>

}
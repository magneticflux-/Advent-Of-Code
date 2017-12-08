package com.github.magneticflux.aoc.pathfinding

import com.google.common.graph.SuccessorsFunction
import com.google.common.graph.ValueGraph
import com.google.common.graph.ValueGraphBuilder

/**
 * Created by Mitchell Skaggs on 12/7/2017.
 */

fun <Node : Any> SuccessorsFunction<Node>.aStar(start: Node,
                                                goal: Node,
                                                exactCost: (from: Node, neighbor: Node) -> Double,
                                                heuristic: (from: Node, to: Node) -> Double): PathfindResult<Node> {
    val openSet = mutableSetOf(start)

    val cameFrom = mutableMapOf<Node, Node>()
    fun retracePath(): Path<Node> {
        var currentNode = goal
        val currentPath = mutableListOf(currentNode)
        while (currentNode in cameFrom) {
            currentNode = cameFrom.getValue(currentNode)
            currentPath += currentNode
        }
        return Path(currentPath.asReversed())
    }

    val gScore = mutableMapOf<Node, Double>().withDefault { Double.POSITIVE_INFINITY }
    gScore[start] = 0.0

    val fScore = mutableMapOf<Node, Double>().withDefault { Double.POSITIVE_INFINITY }
    fScore[start] = heuristic(start, goal)

    while (openSet.isNotEmpty()) {
        // Lowest distance point
        val current = openSet.minBy { fScore.getValue(it) }!!
        openSet -= current

        if (current == goal)
            return retracePath()

        for (neighbor in successors(current)) {
            val newCost = gScore.getValue(current) + exactCost(current, neighbor)

            if (newCost < gScore.getValue(neighbor)) {
                gScore[neighbor] = newCost
                fScore[neighbor] = newCost + heuristic(neighbor, goal)
                openSet += neighbor
                cameFrom[neighbor] = current
            }
        }
    }

    return NoPathFound.withType()
}

sealed class PathfindResult<out Node>

data class Path<out Node>(val nodes: List<Node>) : PathfindResult<Node>()

object NoPathFound : PathfindResult<Any>() {
    @Suppress("UNCHECKED_CAST")
    fun <T> withType(): PathfindResult<T> = this as PathfindResult<T>

    override fun toString(): String = "NoPathFound"
}

fun main(args: Array<String>) {
    val graph = ValueGraphBuilder.undirected().allowsSelfLoops(true).build<String, Double>()
            .apply {
                addNode("s")
                addNode("a")
                addNode("b")
                addNode("c")
                addNode("g")

                putEdgeValue("s", "a", 1.0)
                putEdgeValue("a", "b", 1.0)
                putEdgeValue("b", "g", 1.0)

                putEdgeValue("s", "c", 1.0)
                putEdgeValue("c", "g", 1.0)
            }
    graph.aStar("s", "g",
            graph::getEdgeValue,
            { _, _ -> 1.0 })
            .let { println(it) }
}

fun <Node, EdgeValue> ValueGraph<Node, EdgeValue>.getEdgeValue(nodeU: Node, nodeV: Node): EdgeValue {
    return this.edgeValue(nodeU, nodeV).get()
}
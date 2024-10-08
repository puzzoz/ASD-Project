package src.datastructure.graph;

/**
 * Generic edge of weighted graphs
 * Ho implementato comparable per permettere al codice di comparare diversi tipi di weighted
 * graph, che viene fatto dentro il codice quicksort
 *
 * @param <D> type of the data object in the graph vertexes
 */

public class WeightedEdge<D extends Comparable<D>> extends Edge<D> implements Comparable<WeightedEdge<D>> {

	/** vertex weight */
	public double weight;

	/**
   	 * Constructs an edge of a weighted graph
	 * @param source the source vertex
   	 * @param dest the destination vertex 
	 * @param weight edge weight 
	 */	
	public WeightedEdge(Vertex<D> source, Vertex<D> dest, double weight) {
		super(source,dest);
		this.weight = weight;
	}

	//ho implementato weight come comparable per permettere al programma di ordinare
	//gli edges per peso
	@Override
	public int compareTo(WeightedEdge<D> dWeightedEdge) {
		return Double.compare(weight, dWeightedEdge.weight);
	}
}

package src.algorithm.MST;

import src.datastructure.graph.*;
import src.datastructure.unionfind.*;
import src.algorithm.sorting.*;

import javax.swing.*;

/**
 * SPIEGAZIONE CODICE
 * Il costo computazionale
 * <p>
 *   Il codice di "compute" ha un costo computazionale complessivo di &Theta;(nlogn) (n numero di edges) <p>
 *   il primo ciclo e' &Theta;(n), il quicksort di &Theta;(nlogn), il ciclo dei vertici ha un costo
 *   &Theta;(v) (v = il numero di vertici)
 *   infine per l'ultimo ciclo, uso find e union che sono rispettivamente &Theta;(logn) e &Theta;(1)
 *   visto che faccio uso dell'implementazione con euristica, il costo totale diventa &Theta;(nlong).
 * </p>
 * <p>
 * Complessivamente il costo e' T(n)=&Theta;(n)+&Theta;(nlogn)+&Theta;(v)+&Theta;(nlogn)=&Theta;(nlogn)
 * <p>
 *     Le modifiche fatte sul codice fornito:
 * <p>
 *     Il tipo D e' stato reso comparable per permettermi di comparare i data
 *     all'interno delle source e dest, in particolare per rendere possibile la riga 53:
 *     <p>
 *         if(g.edges().get(i).dest.data.compareTo(g.edges().get(i).source.data)>=0)
 *
 *     <p> Weight e' stato reso comparable per permettere l'ordinamento degli edges per peso
 * </p>
 * <p>
 * This class contains the implementation of the Kruskal's algorithm for the construction of a Minimum Spanning Tree (MST) of a weighted graph.
 *
 * @param <D> type of the data stored in the nodes of the graph
 */
public class Kruskal<D extends Comparable<D>> implements MST<D> {

	// The WeightedGraph on which the MST is computed
	private WeightedGraph<D> t = new WeightedGraphAL<>();

	// The total weight of the MST
	private double weight;

	/**
	 * Computes the Minimum Spanning Tree (MST) of the specified weighted graph.
	 *
	 * @param g the weighted graph
	 */
    public void compute(WeightedGraph<D> g) {
		int num= g.edgeNum()/2;
		UnionFind<D,QUnode<D>, QUset> uf= new QuickUnionRank<>();
		WeightedEdge<D>[] edgeArray = new WeightedEdge[num];
		int index=0;
		//inserisco dentro l'array di edges tutti gli edges del grafico, la condizione if
		//serve per evitare edges duplicati, es "0 1" e "1 0"
		for(int i=0; i<num*2; i++){
			if(g.edges().get(i).dest.data.compareTo(g.edges().get(i).source.data)>=0){
				edgeArray[index]= (WeightedEdge<D>) g.edges().get(i);
				index++;
			}
		}

		//ordino gli edges per peso, vengono ordinati per peso grazie all'implementazione
		//del comparable su weight
		Sorting.quicksort(edgeArray);

		//creo un array di nodi
		QUnode<D>[] nodes = new QUnode[g.vertexNum()];
		//per ogni vertice inserisco dentro l'array di nodi il singoletto di ogni vertice
		//e creo i vertici dentro il grafo mst
		for(int i=0; i<g.vertexNum(); i++){
			D data = g.vertexes().get(i).data;
			nodes[i] = uf.makeSet(data);
			t.addVertex(data);
		}

		for(int i=0; i<num; i++){

			//uso gli index del dest e della source
			int uIndex = ((VertexAL<D>) edgeArray[i].dest).index;
			int vIndex = ((VertexAL<D>) edgeArray[i].source).index;

			//trovo il nodo con l'index corrispondente dentro l'array di nodi
			QUnode<D> u = nodes[uIndex];
			QUnode<D> v = nodes[vIndex];

			//trovo l'insieme di cui fanno parte i vertici dell'edge che sto analizzando
			//e verifico se fanno parte dello stesso o no
			QUset tu = uf.find(u);
			QUset tv = uf.find(v);
			if(tu!=tv){
				uf.union(tu,tv);				//unisco gli insiemi
				weight+=edgeArray[i].weight;	//aumento il peso totale dell'mst
				//aggiungo gli edge al mio mst, essendo un grafo undirected lo faccio in
				//entrambe le direzioni, per farlo creo ogni volta un edge nuovo per essere
				//sicura di usare i vertici del mio mst
				t.addEdge(new WeightedEdge<D>(t.vertexes().get(vIndex),
						t.vertexes().get(uIndex),edgeArray[i].weight));
				t.addEdge(new WeightedEdge<D>(t.vertexes().get(uIndex),
						t.vertexes().get(vIndex),edgeArray[i].weight));
			}
		}
    }

	/**
	 * Returns the Minimum Spanning Tree (MST) of the weighted graph.
	 *
	 * @return the Minimum Spanning Tree (MST) of the weighted graph
	 */
	public WeightedGraph<D> spanningTree() {
		return t;
	}

	/**
	 * Returns the total weight of the Minimum Spanning Tree (MST) of the weighted graph.
	 *
	 * @return the total weight of the Minimum Spanning Tree (MST) of the weighted graph
	 */
	public double totalWeight() {
		return weight;
	}
}
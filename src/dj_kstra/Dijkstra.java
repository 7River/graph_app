package dj_kstra;

import graph_application_dj_kstra.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.PriorityQueue;

public class Dijkstra {
	
	
	 public void computePaths(Vertex source)
	    {
	        source.minDistance = 0.;
	        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
	      	vertexQueue.add(source);

		   while (!vertexQueue.isEmpty()) {
		    Vertex u = vertexQueue.poll();
                      Log.add_to_list("---pts: "+u.name);

	            // Visit each edge exiting u
	            for (Edge e : u.adjacencies)
	            {
	                Vertex v = e.target;
                        Log.add_to_list("->target: "+v.name);
	                double weight = e.weight;
	                double distanceThroughU = u.minDistance + weight;
                        Log.add_to_list("compare distance: "+distanceThroughU+" "+v.minDistance);
			if (distanceThroughU < v.minDistance) {
				
			    vertexQueue.remove(v);
			    v.minDistance = distanceThroughU ;
			    v.previous = u;
                            Log.add_to_list("adding pts: "+v.name);
			    vertexQueue.add(v);
			}
	            }
	        }
	    }

	
	 
	 public  List<Vertex> getShortestPathTo(Vertex target)
	    {
	        List<Vertex> path = new ArrayList<Vertex>();
	        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
	            path.add(vertex);
	        Collections.reverse(path);
	        return path;
	    }
} 

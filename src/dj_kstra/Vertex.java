package dj_kstra;


public class Vertex implements Comparable<Vertex> {

	 public String name;
	    public Edge[] adjacencies;
	    public double minDistance = Double.POSITIVE_INFINITY;
	    public Vertex previous;
	     public Vertex()
	     {
	    	 
	     }
	    
	    public String getName() {
			return name;
		}

      public void setName(String name)
     {
	      this.name = name;
        }



		public Vertex(String argName)
	    {
	    	this.name = argName;
	        
	    }
	   


	

	@Override
		public String toString() {
			return name ;
		}





	public int compareTo(Vertex other) {
         
		 return Double.compare(minDistance, other.minDistance);
	}

}

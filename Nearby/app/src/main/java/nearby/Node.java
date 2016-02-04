package nearby;

public class Node {
	String name;
	String inf;
	String loc;
	public Node(String name,String inf,String loc) {
         this.name=name;
         this.inf=inf;
         this.loc=loc;
	}

	public void setName(String name) {
            this.name=name;
	}

	public String getName() {
		return name;

	}

	public void setInf(String  inf) {
           this.inf=inf;
	}

	public String getInf() {
		return inf;

	}

	public void setLoc(String loc) {
           this.loc=loc;
	}

	public String getLoc() {
		return loc;
	}

}

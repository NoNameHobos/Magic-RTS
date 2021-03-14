package main.util;

public abstract class Resource {

	private static long NEXT_RES = 0;
	
	// Unique resource id
	protected long id;
	
	/** Basic resource constructor
	 * @param name - Resource name
	 */
	public Resource() {
		id = NEXT_RES++;
	}

	public long getId() {
		return id;
	}
	
}

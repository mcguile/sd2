package lab2.network3;


public class Dummie
{

	/**
	 * @param args
	 */
	public static void main( String[] args )
	{
		castingExample();

	}
	
	private static void castingExample() {
		
		
		//   Comments by Dr. idris skloul
		int x = 'B';
		System.out.println(x);    // this will print 66 someone converts from char into int
		                          //  A. java compiler did that, this is upcasting (automatic casting)
		double d = 10;
		System.out.println(d);    // this will print 10.0 someone converts from int into double
        				  //  A. java compiler did that,  this is upcasting (automatic casting)
		
                //int i = 10.50;          // Compiler time error , down casting java's compiler will not accept that !
					  //  see the next step
		

                int i = (int) 10.50;      // Java's compiler will accept that, but the risk (loss information) still there
		System.out.println(i);    // this prints 10  someone converts from double into int
        				  //  A. programmer did that using manual casting (int) ==> loss information  (0.50)
		
		
		//Post p;
		Post p = new Post("Post");
		PhotoPost pp = new PhotoPost("p","p","p");
		MessagePost mp = new MessagePost("mp","mp");
		p=pp;
		p=mp;
		//pp= p;    <=== wrong compile time error  you need to cast , see next step //idris
		//pp= (PhotoPost) p;   // manual casting 'dangerous'  Casting is bad thing   // idris
		
	}

}
import java.util.Map;
import java.util.Map.Entry;


public class TestClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(System.getenv("user.home"));
		
		Map<String, String> map = System.getenv();
		
		for(Entry<String, String> entry : map.entrySet() ) {
			System.out.println(entry.getKey()+"		"+entry.getValue());
		}
		
	}

}

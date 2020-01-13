package Q1;
import java.util.Iterator;

public class MinMember {

	public static <T extends Comparable<T>> T findMin(MySet<T> mySet) {

		if (mySet == null)
			return null;

		Iterator<T> iterator = mySet.iterator();
		T min = mySet.iterator().next();

		while (iterator.hasNext()) {

			T t = iterator.next();
			
			if (min.compareTo(t) > 0)
				min = t;
		}

		return min;

	}

}

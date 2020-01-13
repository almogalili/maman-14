package Q1;
import java.util.ArrayList;
import java.util.Iterator;

public class MySet<T extends Comparable<T>> implements Iterable<T> {

	private ArrayList<T> arraySet;

	public MySet() {

		this.arraySet = new ArrayList<>();

	}

	public MySet(T[] members) {

		this.arraySet = new ArrayList<>();

		for (int i = 0; i < members.length; i++) {
			insert(members[i]);
		}

	}

	public ArrayList<T> getArrSet() {

		return this.arraySet;

	}

	public void insert(T object) {

		insert(this.arraySet, object);

	}

	public void insert(ArrayList<T> arraySet, T object) {

		if (!this.isMember(object))

			arraySet.add(object);

	}

	public void union(MySet<T> otherMySet) {

		ArrayList<T> unionArr = new ArrayList<>(this.arraySet.size() + otherMySet.arraySet.size());
		unionArr.addAll(arraySet);

		for (T object : otherMySet.arraySet) {

			this.insert(unionArr, object);

		}

		this.arraySet = unionArr;

	}

	public void intersect(MySet<T> otherSet) {

		ArrayList<T> intersectArr = new ArrayList<>(
				this.arraySet.size() > otherSet.arraySet.size() ? otherSet.arraySet.size() : this.arraySet.size());

		for (T object : otherSet.arraySet) {

			if (this.isMember(object))

				intersectArr.add(object);

		}

		this.arraySet = intersectArr;

	}

	public boolean isSubset(MySet<T> otherSet) {
		if (arraySet.size() < otherSet.arraySet.size())
			return false;

		for (T object : otherSet.arraySet) {
			if (!isMember(object))

				return false;
		}

		return true;

	}

	public boolean isMember(T object) {
		return arraySet.contains(object) ? true : false;
	}

	public void delete(T object) {

		int index = arraySet.indexOf(object);

		if (index != -1)
			arraySet.remove(index);

	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		for (T obj : this.arraySet) {


			sb.append(obj);
			sb.append("  ");

		}

		return sb.toString();

	}

	@Override
	public Iterator<T> iterator() {

		return arraySet.iterator();
	}

}

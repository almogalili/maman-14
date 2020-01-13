package Q1;
import java.security.SecureRandom;
import java.util.Scanner;

public class MainQ1 {

	private static MySet<Integer> mySet1;
	private static MySet<Integer> mySet2;
	private static MySet<Integer> mySet3;
	private static MySet<Integer> mySet4;

	private static Scanner scan = new Scanner(System.in);

	private static final int NUMBER_OF_RANDOM_NUMBERS = 100;
	private static final int NUMBER_OF_MEMBERS = 10;

	public static void main(String[] args) {

		fillWithRandomNumbers();
		createMySet4();
		getNumber();
		personSet();

	}
	
	
	

	private static void fillWithRandomNumbers() {

		SecureRandom randomNumbers = new SecureRandom();

		Integer[] arr1 = new Integer[NUMBER_OF_MEMBERS];
		Integer[] arr2 = new Integer[NUMBER_OF_MEMBERS];
		Integer[] arr3 = new Integer[NUMBER_OF_MEMBERS];

		for (int i = 0; i < NUMBER_OF_MEMBERS; i++) {
			arr1[i] = randomNumbers.nextInt(NUMBER_OF_RANDOM_NUMBERS);
			arr2[i] = randomNumbers.nextInt(NUMBER_OF_RANDOM_NUMBERS);
			arr3[i] = randomNumbers.nextInt(NUMBER_OF_RANDOM_NUMBERS);
		}

		mySet1 = new MySet<Integer>(arr1);
		mySet2 = new MySet<Integer>(arr2);
		mySet3 = new MySet<Integer>(arr3);

		// 1
		System.out.println("mySet1 : " + mySet1);
		System.out.println("mySet2 : " + mySet2);
		System.out.println("mySet3 : " + mySet3);

		// 2
		System.out.println("\n\nmySet1 U mySet2");
		mySet1.union(mySet2);
		System.out.println(mySet1);

		// 3
		System.out.println("\n\nmySet1 intersect mySet3");
		mySet1.intersect(mySet3);
		System.out.println(mySet1);

	}

	// 4
	private static void createMySet4() {

		Integer[] arr = new Integer[2];

		System.out.println("\n\nCreating mySet4\nPlease enter an number");
		arr[0] = scan.nextInt();

		System.out.println("\nPlease enter an number");
		arr[1] = scan.nextInt();

		mySet4 = new MySet<Integer>(arr);

		System.out.println("mySet4 is subSet of mySet1 : " + mySet1.isSubset(mySet4));
		System.out.println("mySet4 is subSet of mySet2 : " + mySet2.isSubset(mySet4));
		System.out.println("mySet4 is subSet of mySet3 : " + mySet3.isSubset(mySet4));

	}

	//5
	private static void getNumber() {
		
		System.out.println("\n\nPlease enter an number");

		Integer number = scan.nextInt();
		mySet2.insert(number);
		mySet3.delete(number);


		System.out.println("is " + number + " in mySet1 : " + mySet1.isMember(number));
		System.out.println("is " + number + " in mySet2 : " + mySet2.isMember(number));
		System.out.println("is " + number + " in mySet3 : " + mySet3.isMember(number));

	}
	
	private static void personSet()
	{
		Person p1 = new Person(203823333, "Almog", "Galili", 1992);
		Person p2 = new Person(203823332, "Beny", "Ohana", 1993);
		Person p3 = new Person(203823331, "Oz", "Komemi", 1991);
		Person p4 = new Person(203823335, "Nofar", "Chen", 1996);
		Person p5 = new Person(203823337, "Aotem", "Chen", 1998);
		
		MySet<Person> personSet = new MySet<Person>();
		
		personSet.insert(p1);
		personSet.insert(p2);
		personSet.insert(p3);
		personSet.insert(p4);
		personSet.insert(p5);
		
		
		System.out.println(personSet);
		System.out.println("The min memeber of personSet: "+ MinMember.findMin(personSet));
		
		
	}

}

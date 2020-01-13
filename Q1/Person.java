package Q1;

public class Person implements Comparable<Person> {

	private Integer id;
	private String firstName;
	private String lastName;
	private Integer birthYear;

	public Person(Integer id, String firstName, String lastName, Integer birthYear) {

		super();
		this.id = id;
		this.firstName = firstName.toLowerCase();
		this.lastName = lastName.toLowerCase();
		this.birthYear = birthYear;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName.toLowerCase();
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName.toLowerCase();
	}

	public Integer getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
	}

	@Override
	public int compareTo(Person o) {

		int lastNameResult = this.lastName.compareTo(o.lastName);

		if (lastNameResult < 0)
			return -1;

		else if (lastNameResult > 0)
			return 1;

		// The same last name, we check the firstName now.
		else {
			int firstNameResult = this.firstName.compareTo(o.firstName);

			if (firstNameResult < 0)
				return -1;

			else if (firstNameResult > 0)
				return 1;

		}
		// if the last names and the first names the same.
		return 0;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		Person other = (Person) obj;

		if (this.compareTo(other) == 0)
			return true;

		return false;

	}

	@Override
	public String toString() {
		
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthYear=" + birthYear
				+ "]\n";
		
		
	}
	

}

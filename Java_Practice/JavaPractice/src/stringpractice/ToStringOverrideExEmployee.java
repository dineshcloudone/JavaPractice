package stringpractice;

public class ToStringOverrideExEmployee {

	int rollno;
	String name;
	String city;

	ToStringOverrideExEmployee(int rno, String empname, String empcity) {
		rollno = rno;
		name = empname;
		city = empcity;
	}

	public String toString() {
		return rollno + " " + name + " " + city;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ToStringOverrideExEmployee tso1 = new ToStringOverrideExEmployee(1, "Dinesh", "hyd");
		ToStringOverrideExEmployee tso2 = new ToStringOverrideExEmployee(1, "Dinesh", "hyd");
		System.out.println(tso1);
		System.out.println(tso2);

	}

}

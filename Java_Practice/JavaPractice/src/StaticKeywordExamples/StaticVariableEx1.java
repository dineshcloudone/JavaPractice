package StaticKeywordExamples;

public class StaticVariableEx1 {
	static int count = 0;

	StaticVariableEx1() {
		count++;
		System.out.println("Count Value: " + count);
	}

	public static void main(String[] args) {
		StaticVariableEx1 sve1 = new StaticVariableEx1();
		StaticVariableEx1 sve2 = new StaticVariableEx1();
		StaticVariableEx1 sve3 = new StaticVariableEx1();

		System.out.println("from main at end : " + StaticVariableEx1.count);
	}

}
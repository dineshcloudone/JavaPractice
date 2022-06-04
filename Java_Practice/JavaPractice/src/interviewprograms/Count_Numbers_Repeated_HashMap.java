package interviewprograms;

import java.util.*;

public class Count_Numbers_Repeated_HashMap {

	static void remove_Duplicates() {

		// 5,4,3,4,5,6,7,8,5
		ArrayList<Integer> al = new ArrayList<Integer>();

		al.add(5);
		al.add(4);
		al.add(3);
		al.add(4);
		al.add(5);
		al.add(6);
		al.add(6);

		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();

		for (int i = 0; i < al.size(); i++) {
				
			if (hm.containsKey(al.get(i))) {
				int j = hm.get(al.get(i));
				hm.put(al.get(i), ++j);
			} else {
				hm.put(al.get(i), 1);
			}

		}

		hm.forEach((x, y) -> System.out.println("Key : " + x + " value : " + y));

	}

	public static void main(String[] args) {
		remove_Duplicates();
	}

}

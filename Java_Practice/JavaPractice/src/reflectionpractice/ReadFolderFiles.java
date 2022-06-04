package reflectionpractice;

import java.lang.Class;
import java.lang.reflect.*;
import reflectionpractice.A;

import java.util.*;

public class ReadFolderFiles {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Class c=A.class;
		int a=9;
		Object o=c.newInstance();
		Method m=c.getDeclaredMethod("message",int.class);		
		//Method m=c.getDeclaredMethod("message",HashMap.class);
		m.setAccessible(true);
		m.invoke(o,a);
	}
}

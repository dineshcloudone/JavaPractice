package generics;

import java.util.ArrayList;
import java.util.List;

abstract class Tender{
	abstract void pay();
}

class CashPayment extends Tender{
	void pay(){
		System.out.println("Payment with cash");
	}	
}

class EFTPayment extends Tender{
	void pay() {
		System.out.println("Payment with tender");
	}
}

public class WildCardGenerics {
	
	public void TenderTypes(List<? extends Tender> list) {
		for(Tender t:list) {
			t.pay();
		}
	}
	
	public static void main(String[] args) {
		List<CashPayment> list1=new ArrayList<CashPayment>();
		list1.add(new CashPayment());
		
		List<EFTPayment> list2=new ArrayList<EFTPayment>();
		list2.add(new EFTPayment());
		
		new WildCardGenerics().TenderTypes(list1);
		new WildCardGenerics().TenderTypes(list2);
	}

}

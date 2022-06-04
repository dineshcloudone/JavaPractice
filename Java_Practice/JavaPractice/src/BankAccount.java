

class BankAccount{
	
	int Account_no;
	String Name;
	int Amount_Bank;
	
	void AccountCreation(int acc, String name, int amnt)
	{
		Account_no=acc;
		Name=name;
		Amount_Bank=amnt;
	}
	
	void AccountDetails()
	{
		System.out.println(Account_no+" "+Name+" "+Amount_Bank);
	}
	
	void deposit(int amt)
	{
		Amount_Bank=amt+Amount_Bank;
	}
	
	void withdraw(int amnt)
	{
		if(amnt<Amount_Bank)
		{
			Amount_Bank=Amount_Bank-amnt;			
			System.out.println("Amount withdrawn : "+ amnt);
		}
		else
		{
			System.out.println("Sufficient Balance is not available");
		}
	}
	
	void checkBalance()
	{
		System.out.println("Account Balance : "+ Amount_Bank);
	}
}

class DineshAccount
{	
	public static void main(String[] args)
	{
		BankAccount ba1=new BankAccount(), ba2=new BankAccount();
		
		ba1.AccountCreation(12345, "Dinesh", 5000);
		
		ba2.AccountCreation(12346, "Venkatesh", 5001);
		
		
		
		ba1.AccountDetails();
		ba1.deposit(100);
		ba1.checkBalance();
		ba1.withdraw(50);
		ba1.checkBalance();
		
		ba2.AccountDetails();
		
	}
}
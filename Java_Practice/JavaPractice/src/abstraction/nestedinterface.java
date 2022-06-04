package abstraction;


//The Java compiler adds public and abstract keywords before the interface method. 
//Moreover, it adds public, static and final keywords before data members.

interface nestedinterface {
	
	void print();
	
	interface childinterface {
		
		void msg();
		
	}
}

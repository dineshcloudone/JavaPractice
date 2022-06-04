package loose_tight_coupling;

//Link : https://www.upgrad.com/blog/loose-coupling-vs-tight-coupling-in-java/

public class LooseCouplingEx1_Volume {
	
	public static void main(String[] args) {
		Cylinder c=new Cylinder(5,5,5);
		c.display();
	}

}

class Cylinder{
	private int volume;
	Cylinder(int length,int width,int height){
		this.volume=length*width*height;
	}
	
	public void display() {
		System.out.println("Volume of cylinder : "+volume);
	}
}

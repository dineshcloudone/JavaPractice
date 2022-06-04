package loose_tight_coupling;

public class TightCouplingEx1_Volume {
	
	public static void main(String[] args) {
		Cylinder2 c=new Cylinder2(5,5,5);
		System.out.println("volume : "+c.volume);
	}

}

class Cylinder2{
	public int volume;
	Cylinder2(int length,int width,int height){
		this.volume=length*width*height;
	}
	
}

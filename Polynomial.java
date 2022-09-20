public class Polynomial {
	double[] coefficients;
	
	public Polynomial(){
		coefficients = [0];
	}
	
	public Polynomial(double arr){
		int len = arr.length;
		for (int i = 0 ; i < len ; i++){
			coefficients[i] = arr[i];
		}
	}
	
	public Polynomial add(Polynomial other){
		int len = other.coefficients.length;
		for (int i = 0 ; i < len ; i++){
			coefficients[i] += other.coefficients[i];
		}
	}
	public double evaluate(double val){
		int len = arr.length;
		double sum = 0;
		for (int i = 0; i < len ; i++){
			sum += Math.pow(val, i) * coefficients[i]; 
		}
	}
	public boolean hasRoot(double arr){
		
	}
}
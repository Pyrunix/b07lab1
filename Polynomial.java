public class Polynomial {
	double[] coefficients;
	
	public Polynomial(){
		coefficients = new double[]{0};
	}
	
	public Polynomial(double[] arr){
		coefficients = new double[arr.length];
		for (int i = 0 ; i < arr.length ; i++){
			coefficients[i] = arr[i];
		}
	}
	
	public Polynomial add(Polynomial other){
		int len1 = this.coefficients.length;
		int len2 = other.coefficients.length;
		int maxLen = Math.max(len1, len2);
		double[] arr = new double[maxLen];
		for (int i = 0 ; i < maxLen; i++){
			if (i >= len1){
				arr[i] += other.coefficients[i];
			} else if (i >= len2){
				arr[i] += this.coefficients[i];
			} else {
			arr[i] += other.coefficients[i] + this.coefficients[i];
			}
		}
		return new Polynomial(arr);
	}
	
	public double evaluate(double val){
		int len = coefficients.length;
		double sum = 0;
		for (int i = 0; i < len ; i++){
			sum += Math.pow(val, i) * coefficients[i]; 
		}
		return sum;
	}

	public boolean hasRoot(double val){
		if (evaluate(val) == 0){
			return true;
		}
		return false;
	}
}
import java.io.FileWriter;
import java.io.FileReader;
import java.util.*;
import java.io.*;  

public class Polynomial {
      double[] coefficients;
      int[] exp;
      
      public Polynomial(){
            coefficients = new double[]{0};
            exp = new int[]{0};
      }
      
      public Polynomial(double[] arr, int[] exp){
            coefficients = new double[arr.length];
            this.exp = new int[exp.length];
            for (int i = 0 ; i < arr.length ; i++){
                  coefficients[i] = arr[i];
                  this.exp[i] = exp[i];
            }
      }

      public Polynomial(String s){
	    BufferedReader f = new BufferedReader(new FileReader(s));
            String str = f.readLine();
            str = str.replace("-", "+-");
            String[] terms = str.split("+|\\x");
            coefficients = new double[terms.length / 2];
            exp = new int[terms.length / 2];
            for (int i = 0 ; i < terms.length ; i++){
                if (i % 2 == 0){
                    coefficients[i] = Double.parseDouble(terms[i]);
                } else {
                    exp[i] = Integer.parseInt(terms[i]);
                }
            }
      }

      public void saveToFile(String s){
            FileWriter out = new FileWriter(s);
            String input = new String("");
            for (int i = 0 ; i < coefficients.length ; i++){
                input = input + coefficients[i];
                input = input + "x";
                input = input + exp[i];
		if (i + 1 < coefficients.length){
			if (coefficients[i+1] >= 0){
				input = input + "+";
			}
		}
            }
            out.write(input);
      }

      public boolean exp_contains(int val){
	    for (int i = 0 ; i < exp.length ; i++){
		if (val == exp[i]){
			return true;
		}
	    }

	    return false;
      }
      
      public Polynomial add(Polynomial other){
            int uniqueNum = 0;
            int maxVal = 0;
	    for (int i = 0 ; i < this.exp.length ; i++){
            	maxVal = Math.max(this.exp[i], maxVal);
	    }
	    for (int i = 0 ; i < other.exp.length ; i++){
            	maxVal = Math.max(other.exp[i], maxVal);
	    }

            for (int i = 0 ; i < maxVal ; i++){
                  if (this.exp_contains(i) == true || other.exp_contains(i) == true){
                        uniqueNum++;
                  }
            }
            
            int[] arr2 = new int[uniqueNum];
            int count = 0;
            for (int i = 0 ; i < maxVal ; i++){
                  if (this.exp_contains(i) == true || other.exp_contains(i) == true){
                        arr2[count] = i;
                        count++;
                  }
            }
            
            double[] arr = new double[arr2.length];
            for (int i = 0 ; i < arr2.length ; i++){
                  for (int j = 0 ; j < this.exp.length ; j++){
                        if ( this.exp[j] == arr2[i] ){
                              arr[i] += this.coefficients[j];
                        }
                  }

                  for (int j = 0 ; j < other.exp.length ; j++){
                        if ( other.exp[j] == arr2[i] ){
                              arr[i] += other.coefficients[j];
                        }
                  }
            }
            return new Polynomial(arr, arr2);
            
      }
      
      public double evaluate(double val){
            int len = coefficients.length;
            double sum = 0;
            for (int i = 0; i < len ; i++){
                  sum += Math.pow(val, exp[i]) * coefficients[i];
            }
            return sum;
      }

      public boolean hasRoot(double val){
            if (evaluate(val) == 0){
                return true;
            }
            return false;
      }

      public Polynomial multiply(Polynomial other){
            double[] new_co = new double[this.coefficients.length + other.coefficients.length - 1];
            int[] new_exp = new int[this.exp.length + other.exp.length - 1];

            for (int i = 0 ; i < this.coefficients.length ; i++){
                for (int j = 0 ; j < other.coefficients.length ; j++){
                    new_co[i + j] = new_co[i+j] + this.coefficients[i] * other.coefficients[j];
                }
            }
            for (int i = 0 ; i < this.exp.length ; i++){
                for (int j = 0 ; j < other.exp.length ; j++){
                    new_exp[i + j] = new_exp[i+j] + exp[i] + exp[j];
                }
	    }
            //Find how many redundant exponents there are, and then add them
            int count = 0;
            for (int i = 0 ; i < new_exp.length - 1 ; i++){
                for (int j = i + 1 ; j < new_exp.length ; j++){
                    if (new_exp[i] == new_exp[j]){
                        new_co[i] = new_co[i] + new_co[j];
                        new_co[j] = 0;
                        count++;
                    }
                }
            }
            
            double[] new_co2 = new double[new_co.length - count];
            int[] new_exp2 = new int[new_co.length - count];
            count = 0;
            for (int i = 0 ; i < new_co.length ; i++){
                if (new_co[i] != 0){
                    new_co2[count] = new_co[i];
                    new_exp2[count] = new_exp[i];
                    count++;
                }
            }
	    Polynomial temp = new Polynomial(new_co2, new_exp2);
            return temp;
      }
}
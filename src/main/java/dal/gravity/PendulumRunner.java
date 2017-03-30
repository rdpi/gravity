package dal.gravity;

import java.text.NumberFormat;
import java.util.Scanner;

/** 
 * compares the values of a simple pendulum using the harmonic motion equation
 * versus the Euler algorithm approximation
 */
public class PendulumRunner {

    public static void main (String [] args) {
	NumberFormat nf = NumberFormat.getInstance ();
	nf.setMaximumFractionDigits (3);

	double delta = (args.length == 0) ? .1 : Double.parseDouble (args[0]);
	double sLen = 10, pMass = 10, theta0 = Math.PI/30;
	System.out.print("Enter gravity: ");
	Scanner input = new Scanner(System.in);
	GravityModel GravityConstant = new GravityModel(input.nextDouble());
	RegularPendulum rp = new RegularPendulum (sLen, pMass, theta0, GravityConstant, delta);
	SimplePendulum sp = new SimplePendulum (sLen, pMass, theta0, GravityConstant);
	RegularPendulum rpCoarse = 
	    new RegularPendulum (sLen, pMass, theta0, GravityConstant, .1);

	// print out difference in displacement in 1 second intervals
	// for 20 seconds
	int iterations = (int) (1/delta);
	System.out.println ("analytical vs. numerical displacement (fine, coarse)");
	for (int second = 1; second <= 20; second++) {
	    for (int i = 0; i < iterations; i++) rp.step ();
	    for (int i = 0; i < 10; i++) rpCoarse.step (); 
	    System.out.println ("t=" + second + "s: \t" + 
				nf.format (Math.toDegrees (sp.getTheta (second))) 
				+ "\t" +
				nf.format (Math.toDegrees (rp.getLastTheta ()))
				+ "\t" + 
				nf.format (Math.toDegrees (rpCoarse.getLastTheta ())));
	}
    }
}


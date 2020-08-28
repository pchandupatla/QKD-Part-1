// TODO: Put your name here.
// Name: Pranav Chandupatla and Anshul Kumar

import java.util.Arrays;
import java.util.Random;

public class qkd1{

    public static void main(String[] args) {

        int n = 10; // number of qubits
        Random rand = new Random();

	// Alice --------------------------------------------

        // Alice generates the raw key.
        String keyAlice = new String(); // Declare a String object.
        for (int i=0; i < n; i++) {       // Iterate over the number of qubits.
	    // Append a random character ('0' or '1') to the end.
            if (rand.nextInt(2)==0) {   // Flip a coin (0 or 1).
	        keyAlice += '0';
	    	}
            else {
                keyAlice += '1';
			}
        }
        System.out.println("keyAlice    = " + keyAlice);

        // Alice chooses the encoding basis for each key bit.
	// This should be a string of '+'s and 'x's with '+'=H/V, 'x'=D/A.
	// TODO: Put your code here.
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < n; i++) {
			if(rand.nextInt(2) == 0)
			{
				builder.append('+');//.5 chance of either H/V or D/A
			}
			else
			{
				builder.append('x');
			}
		}
		String basisAlice =  builder.toString();

	System.out.println("basisAlice  = " + basisAlice);

	// Alice selects a qubit state according to the key and basis.
	// This should be a string of the characters 'H', 'V', 'D', 'A'.
	builder.setLength(0);
	// TODO: Put your code here.
		for (int i = 0; i < n; i++)
		{
			char keyBit = keyAlice.charAt(i);
			char basisBit = basisAlice.charAt(i);
			if(keyBit == '0')
			{
				if(basisBit == '+')
				{
					builder.append('H');
				}
				else
				{
					builder.append('D');
				}
			}
			else
			{
				if(basisBit == '+')
				{
					builder.append('V');
				}
				else
				{
					builder.append('A');
				}
			}
		}
		String qubitAlice = builder.toString();
	System.out.println("qubitAlice  = " + qubitAlice);

	// Alice prepares and sends each qubit.
	// Use the methods of the Qubit class to prepare each qubit.
        Qubit[] qubitArray = new Qubit[n]; // Declare an array of Qubits.
        for (int i=0; i<n; i++) {
            qubitArray[i] = new Qubit();   // Initialize qubitArray.
	}
	// TODO: Put your code here.
		for (int i = 0; i < n; i++)
		{
			Qubit q = new Qubit();
			char polarization = qubitAlice.charAt(i);
			switch(polarization)
			{
				case('H'): q.prepareH();break;
				case('V'): q.prepareV();break;
				case('D'): q.prepareD();break;
				case('A'): q.prepareA();break;
				case('R'): q.prepareR();break;
				case('L'): q.prepareL();break;
			}
			qubitArray[i] = q;
		}
	// You may use the toString() method to verify the prepared qubits.
		//System.out.println(Arrays.toString(qubitArray));
	// Eve   --------------------------------------------
	// You should implement this section after completing Alice and Bob.

	// Eve chooses a basis to measure each qubit.
	// TODO: Put your code here.
		builder.setLength(0);
		for (int i = 0; i < n; i++) {
			if(rand.nextInt(2) == 0)
			{
				builder.append('+');//.5 chance of either H/V or D/A
			}
			else
			{
				builder.append('x');
			}
		}
		String basisEve =  builder.toString();

	System.out.println("basisEve    = " + basisEve);

	// Eve performs a measurement on each qubit.
	// (This is similar to what Bob does.)

	// TODO: Put your code here.
		builder.setLength(0);
		for (int i = 0; i < n; i++)
		{
			Qubit q = qubitArray[i];
			char measurementBasis = basisEve.charAt(i);
			if(measurementBasis == '+')
			{
				builder.append(q.measureHV());
			}
			else
			{
				builder.append(q.measureDA());
			}
		}

		String outcomeEve = builder.toString();
        System.out.println("outcomeEve  = " + outcomeEve);

		// TODO: Put your code here.

	// Eve resends qubits to Bob.
	// (This is similar to what Alice does.)
	// TODO: Put your code here.
		for (int i = 0; i < n; i++)
		{
			Qubit q = new Qubit();
			char polarization = outcomeEve.charAt(i);
			switch(polarization)
			{
				case('H'): q.prepareH();break;
				case('V'): q.prepareV();break;
				case('D'): q.prepareD();break;
				case('A'): q.prepareA();break;
				case('R'): q.prepareR();break;
				case('L'): q.prepareL();break;
			}
			qubitArray[i] = q;
		}


	// Bob   --------------------------------------------

        // Bob chooses a basis to measure each qubit.
	// (This is similar to what Alice does.)
	// TODO: Put your code here.
		builder.setLength(0);
		for (int i = 0; i < n; i++) {
			if(rand.nextInt(2) == 0)
			{
				builder.append('+');//.5 chance of either H/V or D/A
			}
			else
			{
				builder.append('x');
			}
		}
		String basisBob =  builder.toString();
	System.out.println("basisBob    = " + basisBob);

	// Bob performs a measurement on each qubit.
	// Use the methods of the Qubit class to measure each qubit.
		builder.setLength(0);
	// TODO: Put your code here.
		for (int i = 0; i < n; i++)
		{
			Qubit q = qubitArray[i];
			char measurementBasis = basisBob.charAt(i);
			if(measurementBasis == '+')
			{
				builder.append(q.measureHV());
			}
			else
			{
				builder.append(q.measureDA());
			}
		}

		String outcomeBob = builder.toString();
        System.out.println("outcomeBob  = " + outcomeBob);

	// This should be a string of the characters 'H', 'V', 'D', 'A'.

	// Bob infers the raw key.
	// This should be a string of '0's and '1's.
		builder.setLength(0);
		for (int i = 0; i < n; i++)
		{
			char bitBob = outcomeBob.charAt(i);
			if(bitBob == 'H' || bitBob == 'D')
			{
				builder.append(0);
			}
			else
			{
				builder.append(1);
			}
		}
	String keyBob = builder.toString();
	// TODO: Put your code here.
	System.out.println("keyBob      = " + keyBob);
        // Only about half of Bob's raw key with match Alice's raw key.


	// -----------------------------------------------------------
	// Alice and Bob now publicly announce which bases they chose.
	// -----------------------------------------------------------


	// Alice and Bob extract their sifted keys.
		builder.setLength(0); // Alice
		StringBuilder builderTwo = new StringBuilder(); // Bob
		for (int i = 0; i < n; i++)
		{
			char basisA = basisAlice.charAt(i);
			char basisB = basisBob.charAt(i);
			if(basisA == basisB) //keep the bit
			{
				char aliceBit = keyAlice.charAt(i);
				char bobBit = keyBob.charAt(i);
				builder.append(aliceBit);
				builderTwo.append(bobBit);
			}
		}
	String siftedAlice = builder.toString();
	String siftedBob   = builderTwo.toString();
	// TODO: Put your code here.
	System.out.println("siftedAlice = " + siftedAlice);
	System.out.println("siftedBob   = " + siftedBob);

	// Compare Alice and Bob's sifted keys.
        int numMatch = 0;
	double matchPercent;
	if ( siftedAlice.length() != siftedBob.length() ) {
	    System.out.println("Sifted keys are different lengths!");
	}
	else {
	    for (int i=0; i<siftedAlice.length(); i++) {
		if ( siftedAlice.charAt(i) == siftedBob.charAt(i) ) {
		    numMatch += 1;
		}
	    }
	    matchPercent = (double) numMatch  /siftedAlice.length() * 100;
	    System.out.println(Double.toString(matchPercent) + "% match");
	}
	// Without Eve, you should expect a 100% match.
	// With Eve present, this will drop to about 75%.
	// (Using a larger value of n will give better statistics.)

	// Sample Output ---------------------------------------------
	// keyAlice    = 0101100000
	// basisAlice  = +x+x++++x+
		// qubitAlice  = HAHAVHHHDH
        // basisEve    = x++xx+x+++
        // outcomeEve  = DVHADHAHHH
        // basisBob    = x+xx+x++xx
        // outcomeBob  = DVDAHDVHDA
	// keyBob      = 0101001001
	// siftedAlice = 11000
	// siftedBob   = 10100
	// 60.0% match
	// -----------------------------------------------------------
    }

}

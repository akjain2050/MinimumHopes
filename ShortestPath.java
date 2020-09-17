import java.util.Arrays;

/**
 *
 * @author akjain2050@gmail.com
 *
 * Write a program which takes number of compartments as inputs, takes JETPACK threshold values for each compartment,
 * and the value of the compartment where the thesis paper is hidden,
 * and return the minimum hops Ramkrishna have to make to find his thesis paper.
 *
 */
public class ShortestPath {

	public static void main(String[] args) {
		testUseCase1();
		testUseCase2();
		testUseCase3();
		testUseCase4();
	}

	/**
	 *
	 * @param maxCompartmentInTrain = number of compartments in train
	 * @param jetpackThresholdValues = JETPACK threshold values for each compartment
	 * @param targetCompartment = compartment where the thesis paper is hidden
	 * @return ShortestPath object which holds path and minimumHopes value.
	 *
	 * eg.
	 * 1) Input :
	 *    maxCompartmentInTrain = 5,
	 *    jetpackThresholdValues = {2,3,1,1,3}
	 *    targetCompartment where the thesis paper is hidden = 5
	 *
	 *    Output :
	 *    Minimum hopes: 2
	 */
	public static int getMinimumHopes(int maxCompartmentInTrain, int [] jetpackThresholdValues , int targetCompartment) {
		if((jetpackThresholdValues.length < maxCompartmentInTrain) || targetCompartment > maxCompartmentInTrain) {
			System.out.println("Invalid Input!!");
			return 0;
		}

		int target = targetCompartment - 1 ;//targetCompartment where the thesis paper is hidden. Reducing 1 because as per array index starting from zero
		int current = 0 ;//Current Compartment number where Person will stand first. First Compartment will be 1 but as per array index it will be 0.

		int countMinimumHope = 0;//To keep minimum hopes
		String path = (current +1)+"" ;//To keep path for minimum hopes

		while(current < target) {
			int jThreshold = jetpackThresholdValues[current];//jetpack threshold value
			int distance = target - current ;

//			System.out.println("current = "+current+" jThreshold = "+jThreshold+" distance = "+distance);

			if(jThreshold >= distance) {
				path = path+"->"+(target+1) ;
				current = target ;
				countMinimumHope++;
				break ;
			} else {
				int maxMove = 0;//max jetpack threshold
				int compartmentHasMaxJetpackThreshold = 0 ;
				int moveTo = 0 ;
				//To find out which compartment has max jetpack Threshold.
				//If 2 compartment has same value, take the last compartment value to reduce the no of hopes.
				for (int compartmentNum = current; compartmentNum <= (current+jThreshold); compartmentNum++) {//if current =1, threshold = 2, it will check 1,2,3 compartments for max move.
					if(maxMove <= jetpackThresholdValues[compartmentNum]) { //With equal to sign,we take care condition, if 2 compartment has same value
						maxMove = jetpackThresholdValues[compartmentNum];
						compartmentHasMaxJetpackThreshold = compartmentNum ;
					}
				}

				if(current == compartmentHasMaxJetpackThreshold) {
					moveTo = current + maxMove ;
				} else {
					moveTo = compartmentHasMaxJetpackThreshold ;
				}

				//jump to compartment which has max move or max jetpackThreshold
				path = path+"->"+(moveTo+1) ;
				countMinimumHope++;
				current = moveTo ;
			}
		}

		System.out.println("Shortest Path : "+path);
		return countMinimumHope ;
	}

	public static void showOutput(int maxCompartmentInTrain, int [] jetpackThresholdValues , int targetCompartment) {
		System.out.println("Input:-");
        System.out.println("maxCompartmentInTrain = "+maxCompartmentInTrain);
        System.out.println("jetpackThresholdValues = "+Arrays.toString(jetpackThresholdValues));
        System.out.println("targetCompartment = "+targetCompartment);

		System.out.println("\nOutput:-");
		System.out.println("Minimum hopes: "+getMinimumHopes(maxCompartmentInTrain, jetpackThresholdValues, targetCompartment));
		System.out.println(".............................................");
	}

	/**Input:
	 * maxCompartmentInTrain = 5, jetpackThresholdValues = {2,3,1,1,3}, targetCompartment = 5
	 *
	 * Output:
	 * Shortest Path : 1->2->5
	 * Minimum hopes: 2
	 */
	public static void testUseCase1() {
		int jetpackThresholdValues[] = {2,3,1,1,3} ;//Jetpack threshold values
		int maxCompartmentInTrain = 5 ;//No of compartment in Train
		int targetCompartment = 5 ;//Compartment where thesis paper has been hidden.

		showOutput(maxCompartmentInTrain, jetpackThresholdValues, targetCompartment);
	}

	/**Input:
	 * maxCompartmentInTrain = 5, jetpackThresholdValues = {2,1,1,1,3}, targetCompartment = 5
	 *
	 * Output:
	 * Shortest Path : 1->3->4->5
	 * Minimum hopes: 3
	 */
	public static void testUseCase2() {
		int jetpackThresholdValues[] = {2,1,1,1,3} ;//Jetpack threshold values
		int maxCompartmentInTrain = 5 ;//No of compartment in Train
		int targetCompartment = 5 ;//Compartment where thesis paper has been hidden.

		showOutput(maxCompartmentInTrain, jetpackThresholdValues, targetCompartment);
	}

	/**Input:
	 * maxCompartmentInTrain = 5, jetpackThresholdValues = {5,1,1,1,3}, targetCompartment = 5
	 *
	 * Output:
	 * Shortest Path : 1->5
	 * Minimum hopes: 1
	 */
	public static void testUseCase3() {
		int jetpackThresholdValues[] = {5,1,1,1,3} ;//Jetpack threshold values
		int maxCompartmentInTrain = 5 ;//No of compartment in Train
		int targetCompartment = 5 ;//Compartment where thesis paper has been hidden.

		showOutput(maxCompartmentInTrain, jetpackThresholdValues, targetCompartment);
	}

	/**Input:
	 * maxCompartmentInTrain = 5, jetpackThresholdValues = {4,1,1,5,5,4,1,1,1,4}, targetCompartment = 10
	 *
	 * Output:
	 * Shortest Path : 1->5->10
	 * Minimum hopes: 2
	 */
	public static void testUseCase4() {
		int jetpackThresholdValues[] = {4,1,1,5,5,4,1,1,1,4} ;//Jetpack threshold values
		int maxCompartmentInTrain = 10 ;//No of compartment in Train
		int targetCompartment = 10 ;//Compartment where thesis paper has been hidden.

		showOutput(maxCompartmentInTrain, jetpackThresholdValues, targetCompartment);
	}
}

import java.io.File;
import java.io.FileReader;

import weka.classifiers.Evaluation;
import weka.core.Instances;
import cs446.weka.classifiers.trees.Id3;

/** CS 446:
  * This is a sample testing script for training and evaluating a Badges game classifier.
  * Review the main method, and look up Weka documentation to understand what each line does.
  */
public class WekaTester {

    public static void main(String[] args) throws Exception {

    // Check for valid argument (the address of a .arff file, perhaps produced by FeatureGenerator.java)
	if (args.length != 5) {
	    System.err.println("Usage: WekaTester arff-file");
	    System.exit(-1);
	}

	double[] accuracies = new double[5];

	for (int i=0; i<5; i++) {

		//load test fold 
		Instances test = new Instances(new FileReader(new File(args[i])));
		int t=0;
		//checking the index of the first training fold
		if (i==0) {
			t=1;
		}
		else {
			t=0;
		}
		Instances train = new Instances(new FileReader(new File(args[t])));

		//collecting the training folds together
		for (int j=1; j<5; j++) {

			if(i!=j) {
				Instances temp = new Instances(new FileReader(new File(args[j])));
				for (int k=0; k<temp.numInstances(); k++) {
					train.add(temp.instance(k));
				}

			}
		}

		// The last attribute (index N-1) is the class label
		train.setClassIndex(train.numAttributes() - 1);
		test.setClassIndex(test.numAttributes() - 1);

		// Create a new ID3 classifier. This uses the modified one where you can
		// set the depth of the tree.
		Id3 classifier = new Id3();

		// An example depth. If this value is -1, then the tree is grown to full
		// depth.
		classifier.setMaxDepth(-1);

		// Train on training data (make sure it doesn't overlap with testing data!)
		classifier.buildClassifier(train);

		// Print the classfier to the console (see the toString() method in Id3.java)
		System.out.println(classifier);
		System.out.println();

		// Evaluate on the test set
		Evaluation evaluation = new Evaluation(test);
		evaluation.evaluateModel(classifier, test);
		System.out.println(evaluation.toSummaryString());

		accuracies[i] = evaluation.pctCorrect();
	}

	double avgAccuracy = (accuracies[0]+accuracies[1]+accuracies[2]+accuracies[3]+accuracies[4])/5;
	System.out.println(avgAccuracy);
    }
}

#!/bin/bash

mkdir bin

make

# Generate the example features (first and last characters of the
# first names) from the entire dataset. This shows an example of how
# the feature files may be built. Note that you don't necessarily have to
# use Java for this step.

java -cp lib/weka.jar:bin FeatureGenerator ../data/badges.modified.data.fold1 ../data/badges.fold1.arff
java -cp lib/weka.jar:bin FeatureGenerator ../data/badges.modified.data.fold2 ../data/badges.fold2.arff
java -cp lib/weka.jar:bin FeatureGenerator ../data/badges.modified.data.fold3 ../data/badges.fold3.arff
java -cp lib/weka.jar:bin FeatureGenerator ../data/badges.modified.data.fold4 ../data/badges.fold4.arff
java -cp lib/weka.jar:bin FeatureGenerator ../data/badges.modified.data.fold5 ../data/badges.fold5.arff
# Using the features generated above, train a decision tree classifier
# to predict the data. This is just example code and in the
# homework, you should perform five fold cross-validation. 
java -cp lib/weka.jar:bin WekaTester ../data/badges.fold1.arff ../data/badges.fold2.arff ../data/badges.fold3.arff ../data/badges.fold4.arff ../data/badges.fold5.arff

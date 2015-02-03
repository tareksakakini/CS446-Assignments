#!/bin/bash

mkdir bin

make

# Generate the example features (first and last characters of the
# first names) from the entire dataset. This shows an example of how
# the feature files may be built. Note that you don't necessarily have to
# use Java for this step.

java -cp lib/weka.jar:bin FeatureGenerator ../data/badges.modified.data.all ../data/badges.example.arff
# Using the features generated above, train a decision tree classifier
# to predict the data. This is just example code and in the
# homework, you should perform five fold cross-validation. 
java -cp lib/weka.jar:bin WekaTester ../data/badges.example.arff

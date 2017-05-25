# 20_newsgroup

This project uses the famous 20_newsgroup data, which was originally collected by Tom Mitchell.
You have to provide it at the time of training.

It uses stopwords list also, which is in other txt file provided here. You have to provide it at the time of training and testing.

Also, you will need weka- 3.8 or above version to run it.

This project reads the text file and trains the classifier.
The trained classifier is used to predict the test input files.

To run:
Go to the directory which has all source code.

To extract the jar file:
> jar xf weka.jar

To generate ARFF files:
>javac generateArffDriver.java
>java generateArffDriver

To train the model:
>javac TrainModelDriver.java
>java TrainModelDriver

To predict the test:
>javac PredictDriver.java
>java PredictDriver

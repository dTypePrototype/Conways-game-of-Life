import java.util.Random;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
public class MachineLearning {
	public void NaiveBayesApplication()throws Exception{
		DataSource source = new DataSource("./Training.csv");
		Instances train = source.getDataSet();
		train.setClassIndex(train.numAttributes() -1);				
		NaiveBayes nB = new NaiveBayes();
		nB.buildClassifier(train);
		Evaluation eval = new Evaluation(train);
		eval.crossValidateModel(nB, train, 10, new Random(1));
		System.out.println(eval.toSummaryString("\nResults\n======\n", true));
		System.out.println(eval.fMeasure(1)+" "+eval.precision(1)+" "+eval.recall(1));
		DataSource s = new DataSource("./TestingFile.csv");
		Instances test = s.getDataSet();
		int[][] predict=new int [20][20];
		int curRow=0;
		int curCol=0;
		System.out.println("Predicted Output:");
		System.out.println("===============================\n");
		for(int i=0;i<test.size();i++){
			double[] mat= nB.distributionForInstance(test.get(i));
			if(mat[0]>mat[1])
				predict[curRow][curCol]=0;
			else
				predict[curRow][curCol]=1;
			curCol++;
			if((i+1)%20 == 0){
				curCol = 0;
				curRow++;
			}
			if(curRow==19){
				curRow=0;
				for(int k=0;k<20;k++){
					for(int l=0;l<20;l++){
						System.out.print(predict[k][l]+" ");
					}
					System.out.println();
				}
				System.out.println();
			}	
		}		
	}
}
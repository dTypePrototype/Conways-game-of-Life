import java.io.*;
import java.util.*;
public class Game {
	public static void main(String args[])throws Exception{
		int n, m, noOfGames,w,h,arr[][];
		int noOfGenerations;
		int key=0;
		List<Integer> keyArrayList= new ArrayList<Integer>();
		FilesUsed file= new FilesUsed();
		if(args[0].equals("-f")){
			FileReader ff= new FileReader(args[1]);
			BufferedReader br= new BufferedReader(ff);
			noOfGames=Integer.parseInt(args[2]);
			String[] s1=br.readLine().split("\t");
			n=Integer.parseInt(s1[0]);
			m=Integer.parseInt(s1[1]);
			System.out.println("n="+n+" m="+m);
			String[] s2=br.readLine().split("\t");
			w=Integer.parseInt(s2[0]);
			h=Integer.parseInt(s2[1]);
			System.out.println("w="+w+" h="+h);
			arr= new int [n][m];
			noOfGenerations=Integer.parseInt(br.readLine());
			keyArrayList.add(Integer.parseInt(br.readLine()));
			String line=br.readLine();
			int count=0;
			while(line!=null){
				count+=(2*m)+1;
				for(int i=1;i<=count;i++){
					line=br.readLine();
					System.out.println(line);
				}
				br.readLine();
				count=0;
				if(line!=null){
					keyArrayList.add(Integer.parseInt(line));
				}
			}
			br.close();
		}
		else{
			noOfGames=Integer.parseInt(args[0]);
			n=Integer.parseInt(args[1]);
			m=Integer.parseInt(args[2]);
			arr= new int [n][m];
			w=Integer.parseInt(args[3]);
			h=Integer.parseInt(args[4]);
			noOfGenerations=Integer.parseInt(args[5]);
			File f= new File("Output.txt");
			if(f.length()==0){
				file.bw.write(n+"\t"+m+"\n");
				file.bw.write(w+"\t"+h+"\n");
				file.bw.write(noOfGenerations+"\n");
			}
		}
		System.out.println("Actual Output:");
		System.out.println("===============================\n");
		for(int ii=1;ii<=noOfGames;ii++){
			Random rand = new Random();
			int p=25+rand.nextInt(80);
			double nol= (h*w)*((double)p/100);
			int noOfLivingCells=(int)(nol);
			Conway AlgoFunction= new Conway();
			int startIndexOfWidth=0, startIndexOfHeight=0;
			if(h<m){
				startIndexOfHeight= rand.nextInt(n-h-1);		
			}
			if(w<n){
				startIndexOfWidth= rand.nextInt(n-w-1);
			}
			if(h==n)
				startIndexOfHeight=0;
			if(w==n){
				startIndexOfWidth=0;
			}
			List<Pair> shuffleIndex= new ArrayList<>();
			String keyString="";
			keyString=Integer.toBinaryString(startIndexOfHeight);
			if(Integer.toBinaryString(startIndexOfHeight).length()<4){
				for(int i=1;i<=(4-Integer.toBinaryString(startIndexOfHeight).length());i++){
					keyString="0"+keyString;
				}
			}
			if(Integer.toBinaryString(startIndexOfWidth).length()<4){
				for(int i=1;i<=(4-Integer.toBinaryString(startIndexOfWidth).length());i++){
					keyString=keyString+"0";
				}
			}
			keyString=keyString+Integer.toBinaryString(startIndexOfWidth);
			for(int i=startIndexOfHeight;i<startIndexOfHeight+h;i++){
				for(int j=startIndexOfWidth;j<startIndexOfWidth+w;j++){
					shuffleIndex.add(new Pair(i,j));
				}	
			}
			Collections.shuffle(shuffleIndex);
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					arr[i][j]=0;
				}
			}
			for(int i=0;i<noOfLivingCells;i++){
				arr[shuffleIndex.get(i).row][shuffleIndex.get(i).column]=1;
			}
			for(int i=startIndexOfHeight;i<startIndexOfHeight+h;i++){
				for(int j=startIndexOfWidth;j<startIndexOfWidth+w;j++){
					keyString=keyString+arr[i][j];
				}
			}
			key= Integer.parseUnsignedInt(keyString,2);
			AlgoFunction.conwaysAlgo(arr, n, m, noOfGenerations, file, keyArrayList,key,keyString);
		}
		file.bw.close();
		CsvFileGeneration csvFileObj= new CsvFileGeneration();
		csvFileObj.GenerateTrainAndTestFile();
		MachineLearning mlObj=new MachineLearning();
		mlObj.NaiveBayesApplication();
	}
}
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
public class CsvFileGeneration{
	
	public void GenerateTrainAndTestFile()throws IOException{
		FileReader fr= new FileReader("Output.txt");
		FileWriter fw= new FileWriter("Output.csv");
		FileWriter testFile=new FileWriter("Testing.csv");
		BufferedReader br= new BufferedReader(fr);
		BufferedWriter bw= new BufferedWriter(fw);
		BufferedWriter testBw= new BufferedWriter(testFile);
		br.readLine();
		String[] st=br.readLine().split("\t");
		int w=Integer.parseInt(st[0]);
		int h=Integer.parseInt(st[1]);
		int noOfGenerations= Integer.parseInt(br.readLine());
		String line;
		while((line=br.readLine())!=null){
			int key= Integer.parseInt(line);
			int inputArr[][]=new int[20][20];
			generateMatrixWithKeys(inputArr,key,w,h);
			for(int i=1;i<=21;i++){
				line=br.readLine();
			}
			line=br.readLine();
			int outputArr[][]=new int [20][20];
			for(int i=0;i<20;i++){
				String[] s=line.split(" ");
				for(int j=0;j<20;j++){
					outputArr[i][j]=Integer.parseInt(s[j]);
				}
				if(i!=19){
					line=br.readLine();
				}
			}
			System.out.println();
			for(int i=0;i<20;i++){
				for(int j=0;j<20;j++){
					System.out.print(outputArr[i][j]+" ");
				}
				System.out.println();
			}
			for(int i=0;i<20;i++){
				for(int j=0;j<20;j++){
					for(int k=1;k<=noOfGenerations;k++){
						
						if(((i-k)>=0)&&((j-k)>=0)){
							bw.write(inputArr[i-k][j-k]+",");
							testBw.write(inputArr[i-k][j-k]+",");
						}
						else{
							bw.write("0,");
							testBw.write("0,");
						}
						if((i-k)>=0){
							bw.write(inputArr[i-k][j]+",");
							testBw.write(inputArr[i-k][j]+",");
						}
						else{
							bw.write("0,");
							testBw.write("0,");
						}
						if(((i-k)>=0)&&((j+k)<20)){
							bw.write(inputArr[i-k][j+k]+",");
							testBw.write(inputArr[i-k][j+k]+",");
						}
						else{
							bw.write("0,");
							testBw.write("0,");
						}
						if(((j+k)<20)){
							bw.write(inputArr[i][j+k]+",");
							testBw.write(inputArr[i][j+k]+",");
						}
						else{
							bw.write("0,");
							testBw.write("0,");
						}
						if(((i+k)<20)&&((j+k)<20)){
							bw.write(inputArr[i+k][j+k]+",");
							testBw.write(inputArr[i+k][j+k]+",");
						}
						else{
							bw.write("0,");
							testBw.write("0,");
						}
						if(((i+k)<20)){
							bw.write(inputArr[i+k][j]+",");
							testBw.write(inputArr[i+k][j]+",");
						}
						else{
							bw.write("0,");
							testBw.write("0,");
						}
						if(((i+k)<20)&&((j-k)>=0)){
							bw.write(inputArr[i+k][j-k]+",");
							testBw.write(inputArr[i+k][j-k]+",");
						}
						else{
							bw.write("0,");
							testBw.write("0,");
						}
						if((j-k)>=0){
							bw.write(inputArr[i][j-k]+",");
							testBw.write(inputArr[i][j-k]+",");
						}
						else{
							bw.write("0,");
							testBw.write("0,");
						}
					}
					testBw.write("\n");
					if(outputArr[i][j]==0){
						bw.write("F\n");
					}
					else{
						bw.write("T\n");
					}										
				}
			}
		}
		testBw.close();
		bw.close();
		br.close();
		FileReader f1=new FileReader("Output.csv");
		FileWriter f2=new FileWriter("Training.csv");
		FileReader testf1=new FileReader("Testing.csv");
		FileWriter testf2=new FileWriter("TestingFile.csv");
		BufferedReader br1= new BufferedReader(f1);
		BufferedWriter bw2= new BufferedWriter(f2);
		BufferedReader testbr1= new BufferedReader(testf1);
		BufferedWriter testbw2= new BufferedWriter(testf2);
		String row,rowTest;
		row=br1.readLine();
		rowTest=testbr1.readLine();
		String[] p=row.split(",");
		String[] testP=rowTest.split(",");
		int length=p.length;
		int testLen=testP.length;
		for(int i=1;i<=length;i++) {
			if(i!=length){
				bw2.write("N"+i+",");	
			}
			else{
				bw2.write("N"+i+"\n");
			}
		}
		for(int i=1;i<=testLen;i++) {
			if(i!=testLen){
				testbw2.write("N"+i+",");	
			}
			else{
				testbw2.write("N"+i+"\n");
			}
		}
		bw2.write(row+"\n");
		testbw2.write(rowTest.substring(0, (rowTest.length()-1))+"\n");
		while((row=br1.readLine())!=null){
			bw2.write(row+"\n");
		}
		br1.close();
		bw2.close();
		while((rowTest=testbr1.readLine())!=null){
			testbw2.write(rowTest.substring(0,(rowTest.length()-1))+"\n");
		}
		testbr1.close();
		testbw2.close();	
	}
	private static void generateMatrixWithKeys(int a[][],int key,int width,int height){
		String s = Integer.toBinaryString(key);
		int num = 28-s.length();
		for(int i = 0;i<num;i++){
			s = "0"+s;
		}
		int xCoord = Integer.parseUnsignedInt(s.substring(0, 4), 2);
		int yCoord = Integer.parseUnsignedInt(s.substring(4,8),2);
		int index = 8;
		for(int i = xCoord;i<xCoord+height;i++){
			for(int j = yCoord;j<yCoord+width;j++){
				a[i][j] = Integer.parseInt(s.substring(index, index+1));
				index++;
			}
		}
	}
}

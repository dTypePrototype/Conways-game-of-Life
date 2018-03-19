import java.io.IOException;
import java.util.List;
public class Conway{
	
	public void conwaysAlgo(int arr[][],int n,int m, int nog, FilesUsed file, List<Integer> keyArrayList,int key, String keyString)throws IOException{
		int totalNeighbours=0;
		int res[][]=new int[n][m];
		for(int k=0;k<nog;k++){
			if(keyArrayList.contains(key)==true){
				continue;
			}
			else{
				keyArrayList.add(key);
			}
			file.bw.write(key+"\n");
			for(int i=0;i<m;i++){
				for(int j=0;j<n;j++){
					file.bw.write(arr[i][j]+" ");
				}
					file.bw.newLine();
			}
			for(int i=0;i<m;i++){
				for(int j=0;j<n;j++){
					totalNeighbours=0;
					if(i-1>=0 && j-1>=0 && arr[i-1][j-1]==1)
							totalNeighbours++;
					if(i-1>=0 && arr[i-1][j]==1)
							totalNeighbours++;
					if(j-1>=0 && arr[i][j-1]==1)
							totalNeighbours++;
					if(i+1<m &&  arr[i+1][j]==1)
							totalNeighbours++;
					if(i+1<m && j-1>=0 && arr[i+1][j-1]==1)
							totalNeighbours++;
					if(i+1<m && j+1<n && arr[i+1][j+1]==1)
							totalNeighbours++;
					if(j+1<n && arr[i][j+1]==1)
							totalNeighbours++;
					if(i-1>=0 && j+1<n && arr[i-1][j+1]==1)
							totalNeighbours++;
					if(arr[i][j]==0){
						if(totalNeighbours==3){
							res[i][j]=1;
						}
					}
					if(arr[i][j]==1){
						if(totalNeighbours<2){
							res[i][j]=0;
						}
						if(totalNeighbours==2||totalNeighbours==3){
							res[i][j]=1;
						}
						if(totalNeighbours>3)
							res[i][j]=0;
					}
				}				
			}
			for(int x=0;x<m;x++){
				for(int y=0;y<n;y++){
					arr[x][y]=res[x][y];
				}
			}
			file.bw.newLine();
			for(int i=0;i<m;i++){
				for(int j=0;j<n;j++){
					file.bw.write(res[i][j]+" ");
				}
				file.bw.newLine();
			}
		}	
	}
}
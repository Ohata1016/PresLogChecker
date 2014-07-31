import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.ZipException;


public class MyUnzipper {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyUnzipper unzipper = new MyUnzipper();
		unzipper.main();
	}
	
	public void main(){

		Scanner scanner = new Scanner(System.in);
		
		//モードの指定
		System.out.print("Whitch Type?（1.PresLogChecker 2. Block log checker):");
		System.out.flush();
		int type = scanner.nextInt();
		scanner.reset();
		
		//ログ・ファイルの有無の確認するか指定
		System.out.print("check fether log files exists?(0.false，1.true):");
		String isCheckLog = scanner.next();
		scanner.reset();
		
		System.out.print("Input root:");
		System.out.flush();
		//ルート・ディレクトリの指定			
		String path = scanner.next();
		unzipFiles(path);
		
		if(type == 1){
			if(Integer.valueOf(isCheckLog) == 1){
				LogChecker.checkLog(path);	
			}			
		}
		if(type == 2){
			LogChecker.checkBlockLog(path);
		}

		scanner.close();
	}
	
	public void unzipFiles(String path){
		 ZipDecompresser za = new ZipDecompresser();
			
		File file = new File(path);

		//全てのzipファイルを解凍する
		for(File zipFile : file.listFiles()){
			//zipファイルなら解凍する
			if(zipFile.getName().endsWith(".zip")){
				try{
					za.unzip(zipFile, zipFile.getName().substring(0, zipFile.getName().indexOf(".zip")));	
				} catch (ZipException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
			}
		}
	}

}

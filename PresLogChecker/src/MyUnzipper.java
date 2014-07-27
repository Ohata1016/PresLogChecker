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
		//���[�g�E�f�B���N�g���̎w��
		Scanner scanner = new Scanner(System.in);
		System.out.print("check fether log files exists?(0.false�C1.true):");
		String isCheckLog = scanner.next();
		scanner.reset();
		
		System.out.print("Input root:");
		System.out.flush();
		
		String path = scanner.next();
		unzipFiles(path);
		if(Integer.valueOf(isCheckLog) == 1){
			LogChecker.checkLog(path);	
		}
		scanner.close();
	}
	
	public void unzipFiles(String path){
		 ZipDecompresser za = new ZipDecompresser();
			
		File file = new File(path);

		//�S�Ă�zip�t�@�C�����𓀂���
		for(File zipFile : file.listFiles()){
			//zip�t�@�C���Ȃ�𓀂���
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

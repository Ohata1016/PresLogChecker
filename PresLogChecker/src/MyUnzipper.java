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
		
		//���[�h�̎w��
		System.out.print("Whitch Type?�i1.PresLogChecker 2. Block log checker):");
		System.out.flush();
		int type = scanner.nextInt();
		scanner.reset();
		
		//���O�E�t�@�C���̗L���̊m�F���邩�w��
		System.out.print("check fether log files exists?(0.false�C1.true):");
		String isCheckLog = scanner.next();
		scanner.reset();
		
		System.out.print("Input root:");
		System.out.flush();
		//���[�g�E�f�B���N�g���̎w��			
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

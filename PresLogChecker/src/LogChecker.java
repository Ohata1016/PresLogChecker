import java.io.File;
import java.util.Scanner;


public class LogChecker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LogChecker checker = new LogChecker();
		checker.main();
	}
	
	public void main(){
		Scanner scanner = new Scanner(System.in);
		System.out.print("Input root:");
		System.out.flush();
		
		String path = scanner.next();
		checkLog(path);
		
		scanner.close();
	}
	
	public static void checkLog(String path){		
		File file = new File(path);
		
		for(File report : file.listFiles()){
			File logFile = new File(report.getAbsolutePath() + "/.pres2/pres2.log");
			if(report.getName().equals(".DS_Store") || report.getName().endsWith(".zip")){
				//なにもしない
			}else if(!logFile.exists()){
				System.out.println("log not found" + report.getName());
			}else if(logFile.length() == 0){
				System.out.println("0kb log" + report.getName());
			}
		}
		System.out.println("チェック終了");
	}

}

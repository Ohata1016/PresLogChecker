import java.io.File;
import java.util.Scanner;

public class LogChecker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LogChecker checker = new LogChecker();
		checker.main();
	}

	public void main() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Whitch Type?（1.PresLogChecker 2. Block log checker:");
		System.out.flush();

		int type = scanner.nextInt();
		scanner.reset();

		System.out.print("Input root:");
		System.out.flush();

		String path = scanner.next();
		if (type == 1) {
			checkLog(path);
		}
		if (type == 2) {
			checkBlockLog(path);
		}
		scanner.close();
	}

	public static void checkLog(String path) {
		File file = new File(path);

		for (File report : file.listFiles()) {
			File logFile = new File(report.getAbsolutePath()
					+ "/.pres2/pres2.log");
			if (report.getName().equals(".DS_Store")
					|| report.getName().endsWith(".zip")) {
				// なにもしない
			} else if (!logFile.exists()) {
				System.out.println("log not found" + report.getName());
			} else if (logFile.length() == 0) {
				System.out.println("0kb log" + report.getName());
			}
		}
		System.out.println("チェック終了");
	}

	public static void checkBlockLog(String path) {
		File file = new File(path);

		for (File report : file.listFiles()) {
			String projectName = calcProjectName(report);
			if (projectName != null) {
				File srcParent = new File(report.getAbsolutePath() + "\\"
						+ projectName + "/src");
				if (report.getName().equals(".DS_Store")
						|| report.getName().endsWith(".zip")) {
					// なにもしない
				} else if (srcParent.exists()) {
					searchLangDefFiles(srcParent);
				}
			}
		}
		System.out.println("チェック終了");
	}

	public static void searchLangDefFiles(File file) {
		if (file.listFiles() == null) {
			if (file.getName().contains("lang_def_project")) {
				System.out.println("file exist" + file.getAbsolutePath());
			}
			return ;
		} else {
			for (File tmpFile : file.listFiles()) {
				searchLangDefFiles(tmpFile);
			}
		}

	}

	public static String calcProjectName(File report) {
		String name = null;
		if (report != null && report.listFiles() != null) {
			File[] files = report.listFiles();
			for (File file : files) {
				if (file.getName().startsWith("lecture")) {
					name = file.getName();
				}
			}
		}
		return name;
	}

}

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

/**
 * Zip ファイルを解凍するサンプルです。 ※日本語ファイル名、ディレクトリ名に未対応
 */
public class ZipDecompresser {


	/**
	 * Zip ファイルの解凍メソッド
	 * 
	 * @param file
	 *            対象のZIPファイル
	 * @throws ZipException
	 * @throws IOException
	 */
	public void unzip(File file, String name) throws ZipException, IOException {
		ZipInputStream in = null; // ZIPファイル読込み用ストリーム
		BufferedOutputStream out = null; // 解凍ファイル出力用ストリーム

		try {
			// コンストラクタの引数には、読み込むZIPファイルのパスを指定する
			in = new ZipInputStream(new BufferedInputStream(
					new FileInputStream(file.getAbsolutePath())));

			ZipEntry zipEntry = null; // ZIPファイルから取り出したエントリを格納する変数
			int data = 0; // 読込んだバイトを格納する変数

			// ZIPファイルの次のエントリの先頭にストリームを移動する
			// ZIPファイルの終端に達したらNULLが返却されるのでループを抜ける
			while ((zipEntry = in.getNextEntry()) != null) {
				// 圧縮されているファイルのパスを標準出力に表示する
				//System.out.println(zipEntry.getName());

				// ZIPファイルにディレクトリ構成で圧縮されている場合を考慮して、
				// 出力先ディレクトリ以下に子ディレクトリを作成しておく
				// これをやらないとエラーになります。
				new File(file.getAbsolutePath().substring(0,file.getAbsolutePath().indexOf(".zip")) + "\\" + zipEntry.getName()).getParentFile()
						.mkdirs();

				// ZIPファイル内のエントリをファイルに出力するストリームを生成する
				out = new BufferedOutputStream(new FileOutputStream(file.getAbsolutePath().substring(0,file.getAbsolutePath().indexOf(".zip"))
						+ "\\" + zipEntry.getName()));

				// 1バイトずつ、エントリから読み込んで、展開先ファイルに出力する
				while ((data = in.read()) != -1) {
					out.write(data);
				}

				// 現在のZIPエントリを閉じる
				in.closeEntry();

				// 出力ストリームを閉じる
				out.close();
				out = null;
			}
			System.out.println("unzip " + name);
		}
		// 読み込み対象ファイルが存在しなかった場合
		catch (FileNotFoundException e) {
			System.out.println(e);
		}
		// 入出力エラー()が発生した場合
		catch (IOException e) {
			System.out.println(e);
		}
		// その他例外が発生した場合
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		// 例外発生時にも確実にリソースが開放されるように
		// close()の呼び出しはfinallyブロックで行う。
		finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			}

			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}

		//System.out.println("--処理終了--");

	}
}
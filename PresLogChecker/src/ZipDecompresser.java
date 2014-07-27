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
 * Zip �t�@�C�����𓀂���T���v���ł��B �����{��t�@�C�����A�f�B���N�g�����ɖ��Ή�
 */
public class ZipDecompresser {


	/**
	 * Zip �t�@�C���̉𓀃��\�b�h
	 * 
	 * @param file
	 *            �Ώۂ�ZIP�t�@�C��
	 * @throws ZipException
	 * @throws IOException
	 */
	public void unzip(File file, String name) throws ZipException, IOException {
		ZipInputStream in = null; // ZIP�t�@�C���Ǎ��ݗp�X�g���[��
		BufferedOutputStream out = null; // �𓀃t�@�C���o�͗p�X�g���[��

		try {
			// �R���X�g���N�^�̈����ɂ́A�ǂݍ���ZIP�t�@�C���̃p�X���w�肷��
			in = new ZipInputStream(new BufferedInputStream(
					new FileInputStream(file.getAbsolutePath())));

			ZipEntry zipEntry = null; // ZIP�t�@�C��������o�����G���g�����i�[����ϐ�
			int data = 0; // �Ǎ��񂾃o�C�g���i�[����ϐ�

			// ZIP�t�@�C���̎��̃G���g���̐擪�ɃX�g���[�����ړ�����
			// ZIP�t�@�C���̏I�[�ɒB������NULL���ԋp�����̂Ń��[�v�𔲂���
			while ((zipEntry = in.getNextEntry()) != null) {
				// ���k����Ă���t�@�C���̃p�X��W���o�͂ɕ\������
				//System.out.println(zipEntry.getName());

				// ZIP�t�@�C���Ƀf�B���N�g���\���ň��k����Ă���ꍇ���l�����āA
				// �o�͐�f�B���N�g���ȉ��Ɏq�f�B���N�g�����쐬���Ă���
				// ��������Ȃ��ƃG���[�ɂȂ�܂��B
				new File(file.getAbsolutePath().substring(0,file.getAbsolutePath().indexOf(".zip")) + "\\" + zipEntry.getName()).getParentFile()
						.mkdirs();

				// ZIP�t�@�C�����̃G���g�����t�@�C���ɏo�͂���X�g���[���𐶐�����
				out = new BufferedOutputStream(new FileOutputStream(file.getAbsolutePath().substring(0,file.getAbsolutePath().indexOf(".zip"))
						+ "\\" + zipEntry.getName()));

				// 1�o�C�g���A�G���g������ǂݍ���ŁA�W�J��t�@�C���ɏo�͂���
				while ((data = in.read()) != -1) {
					out.write(data);
				}

				// ���݂�ZIP�G���g�������
				in.closeEntry();

				// �o�̓X�g���[�������
				out.close();
				out = null;
			}
			System.out.println("unzip " + name);
		}
		// �ǂݍ��ݑΏۃt�@�C�������݂��Ȃ������ꍇ
		catch (FileNotFoundException e) {
			System.out.println(e);
		}
		// ���o�̓G���[()�����������ꍇ
		catch (IOException e) {
			System.out.println(e);
		}
		// ���̑���O�����������ꍇ
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		// ��O�������ɂ��m���Ƀ��\�[�X���J�������悤��
		// close()�̌Ăяo����finally�u���b�N�ōs���B
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

		//System.out.println("--�����I��--");

	}
}
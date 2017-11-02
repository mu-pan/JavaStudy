package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author
 *
 */
public class CsvMargeMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// 対象ファイル
		File file = new File("C:\\1000\\C16_CSV.csv");

		// ファイルが存在する場合
		if (file.exists() && file.length() > 0) {

			ReadFile read = new ReadFile();
			// CSVを格納するリスト
			List<String[]> seisanList;
			try {
				// ファイルの読み込み
				seisanList = read.readFile(file);

				// 明細データを部門単位でソート
				Collections.sort(seisanList, new Comparator<String[]>() {
					public int compare(String[] str1, String[] str2) {
						return str1[1].compareTo((str2[1]));
					}
				});

				// ソート後のCSVを出力
				read.outPutKoutsuhi(seisanList);

			} catch (UnsupportedEncodingException | FileNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}else{
			System.out.println("指定したファイルが存在しません");
		}
	}
}

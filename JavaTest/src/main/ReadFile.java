/**
 *
 */
package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 *
 */
public class ReadFile {

	/**
	 * ファイルの読み込み
	 *
	 * @param file
	 * @return seisanList
	 */
	protected List<String[]> readFile(File file) {

		List<String[]> seisanList = new ArrayList<String[]>();

		try {

			// ①CSVを読み込む(文字コードの指定）
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "SJIS"));
			// CSV読み込み用
			String line;
			// ヘッダー行を読み込み
			String header = line = br.readLine();
			System.out.println(header);
			// 一行ずつ読み込み
			while ((line = br.readLine()) != null) {
				String[] code = line.split(","); // 行をカンマ区切りで配列に変換
				// 特定の値を除く（6項目：0002 タクシーを除く）
				if (!code[5].equals("0006")) {
					seisanList.add(code);
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
		return seisanList;

	}
}

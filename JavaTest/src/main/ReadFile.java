/**
 *
 */
package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author
 *
 */
public class ReadFile {

	// 閾値
	private static final Long KINGAKU = 10000L;

	/**
	 * ファイルの読み込み
	 *
	 * @param file
	 * @return seisanList
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	protected List<String[]> readFile(File file) throws UnsupportedEncodingException, FileNotFoundException {

		List<String[]> seisanList = new ArrayList<String[]>();

		// ①CSVを読み込む(文字コードの指定）
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "SJIS"));
		try {

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

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return seisanList;

	}

	/**
	 *
	 * @param seisanList
	 */
	protected void outPutKoutsuhi(List<String[]> seisanList) {

		String bumon = "";
		long kingaku = 0L;

		// 明細行のリスト
		LinkedList<String[]> outPutList = new LinkedList<String[]>();
		// 部門単位に明細を集計
		for (String[] strings : seisanList) {
			// 部門が一致する場合、金額を集計
			if (bumon.equals(strings[1])) {
				// KOTSUHIKINGAKUを集計
				kingaku += Long.parseLong(strings[10]);
				// 明細行を格納
				outPutList.add(strings);
				//
			} else {
				// 集計した値が閾値以上の場合のみ出力対象
				if (kingaku > KINGAKU) {
					// 集計した明細行と合計行を出力する
					for (String[] string : outPutList) {
						// System.out.println(string);
						System.out.println("部門：" + string[1] + "金額：" + string[10]);
					}
					System.out.println("部門：" + bumon + "合計金額：" + kingaku);
				}
				bumon = strings[1];
				kingaku = Long.parseLong(strings[10]);
				// クリア
				outPutList.clear();
				// 明細行を格納
				outPutList.add(strings);
			}
		}
		// 最後の集計行を出す
		if (kingaku > KINGAKU) {
			for (String[] string : outPutList) {
				System.out.println("部門：" + string[1] + "金額：" + string[10]);
			}
			System.out.println("部門：" + bumon + "合計金額：" + kingaku);
		}
	}
}

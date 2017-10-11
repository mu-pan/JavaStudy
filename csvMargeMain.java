package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author u122092
 *
 */
public class csvMargeMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		File file = new File("C:\\1000\\C16_CSV.csv");

		// CSVを格納するリスト
		List<String[]> seisanList = new ArrayList<String[]>();

		try {
			// ①CSVを読み込む
			// BufferedReader br = new BufferedReader(new FileReader(file));
			//
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "SJIS"));

			// CSV読み込み用
			String line;

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
			System.out.println(e);
		}

		// 明細データを部門単位でソート
		Collections.sort(seisanList, new Comparator<String[]>() {
			public int compare(String[] str1, String[] str2) {
				return str1[1].compareTo((str2[1]));
			}
		});

		String Bumon = null;
		long kingaku = 0L;

		// 部門単位に明細を集計
		for (String[] strings : seisanList) {
			if (Bumon.equals(strings[1])) {
				// KOTSUHIKINGAKUを集計
				kingaku = kingaku + Long.parseLong(strings[10]);
				continue;
			} else {
				// 集計した値が閾値以上の場合のみ出力対象
				if (kingaku > 100000) {

				}
				Bumon = strings[1];
				kingaku = 0L;
			}

		}

		// 出力時は集計行を追加する
	}

}
package main;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author
 *
 */
public class CsvMargeMain {
	private static final Long KINGAKU = 10000L;

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
			List<String[]> seisanList = null;//read.readFile(file);

			// 明細データを部門単位でソート
			Collections.sort(seisanList, new Comparator<String[]>() {
				public int compare(String[] str1, String[] str2) {
					return str1[1].compareTo((str2[1]));
				}
			});

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
	// TODO Junitテスト
}

package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PrintWriter;

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

		try {
			// ①CSVを読み込む
			BufferedReader br = new BufferedReader(new FileReader(file));

			// ②PipedWriterオブジェクトpipeOutを生成
			PipedWriter pipeOut = new PipedWriter();
			// ③PipedReaderオブジェクトpipeInを生成
			PipedReader pipeIn = new PipedReader(pipeOut);
			// ④PrintWriterクラスでPipedWriterクラスの
			// オブジェクトpipeOutをラップ
			PrintWriter out = new PrintWriter(pipeOut);

			String line;

			// 一行ずつ読み込み
			while ((line = br.readLine()) != null) {
				String[] arrayline = line.split(",", 5); // 行をカンマ区切りで配列に変換

				for (String elem : arrayline) {
					System.out.println(elem);
				}
			}
			br.close();

		} catch (IOException e) {
			System.out.println(e);
		}

		// 特定の値を除く（6項目：0002 タクシーを除く）

		// 明細データを部門単位でソート

		// 部門単位に明細を集計

		// 集計した値が閾値以上の場合のみ出力対象

		// 出力時は集計行を追加する
	}

}
package nakamura;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class CsvReader {

	public CsvReader() {
	}
	
	public Map<String, Summary> readAll(File csv) throws IOException {
		
		if (csv == null || !csv.exists() || csv.length() == 0) {
			throw new CsvReadException("指定されたファイルは読み込めません。");
		}
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csv), Charset.forName("SJIS")))) {
			// 先頭行は読み飛ばし
			br.readLine();
			
			Map<String, Summary> map = new HashMap<String, Summary>();
			
			br.lines().forEach(line -> {
				String[] values = line.split(",");
				if (!"\"0002\"".equals(values[6])) {
					String org = values[1];
					Summary summary = map.get(org);
					if (summary == null) {
						summary = new Summary(org);
						map.put(org, summary);
					}
					
					summary.addRecord(values[10]);
				}
			});
			
			return map;
		} catch (IOException e) {
			throw new CsvReadException("CSVの読み込み時にエラーが発生しました。 csvファイル名:" + csv.getName(), e);
		}
		
	}

}

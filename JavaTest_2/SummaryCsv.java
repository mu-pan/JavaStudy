package nakamura;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

public class SummaryCsv {

	// 閾値
	private static final Long KINGAKU = 10000L;

	public static void main(String[] args) {
		
		long start = System.nanoTime();
		
		try {
			printSummaryByOrganization(new CsvReader().readAll(new File("C16_CSV.csv")));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("処理時間:" + ((double)System.nanoTime() - start) / 1000000000 + "sec");
		
	}
	
	public static void printSummaryByOrganization(Map<String, Summary> summaryMap) {
		
		Iterator<String> it = new TreeSet<>(summaryMap.keySet()).iterator();
		while (it.hasNext()) {
			String organization = it.next();
			Summary summary = summaryMap.get(organization);
			if (summary.getSumPrice() > KINGAKU) {
				System.out.println(summary.printSummary());;
			}
		}
		
	}
	
	
}

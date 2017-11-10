package nakamura;

import java.util.ArrayList;
import java.util.List;

public class Summary {
	
	private String organization;
	private List<String> recs;
	private long sumPrice;
	
	public Summary(String organization) {
		this.organization = organization;
		this.recs = new ArrayList<String>();
		this.sumPrice = 0l;
	}

	public void addRecord(String price) {
		recs.add(price);
		sumPrice += Long.parseLong(price);
	}
	
	public String getOrganization() {
		return organization;
	}
	
	public List<String> getRecs() {
		return recs;
	}
	
	public long getSumPrice() {
		return sumPrice;
	}
	
	public String printSummary() {
		StringBuilder sb = new StringBuilder();
		for (String price : recs) {
			sb.append("部門:").append(organization).append(" 金額:").append(price).append("\n");
		}
		sb.append("部門:").append(organization).append(" 合計金額:").append(sumPrice);

		return sb.toString();
	}
	
}

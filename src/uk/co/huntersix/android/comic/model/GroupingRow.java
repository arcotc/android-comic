package uk.co.huntersix.android.comic.model;

public class GroupingRow {
	public Grouping leftGrouping;
	public Grouping rightGrouping;
	
	public GroupingRow(Grouping leftGrouping) {
		this.leftGrouping = leftGrouping;
	}
	
	public GroupingRow(Grouping leftGrouping, Grouping rightGrouping) {
		this(leftGrouping);
		this.rightGrouping = rightGrouping;
	}
	
	public boolean hasRightGrouping() {
		return rightGrouping != null;
	}
}

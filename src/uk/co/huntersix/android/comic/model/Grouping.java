package uk.co.huntersix.android.comic.model;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;

public class Grouping {
	public String name;
	public Bitmap thumbnail;
	public String desc;
	public List<Comic> comics = new ArrayList<Comic>();
	public boolean purchased;
	
	public Grouping(String name, Bitmap thumbnail) {
		this.name = name;
		this.thumbnail = thumbnail;
	}
}

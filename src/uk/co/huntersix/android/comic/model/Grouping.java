package uk.co.huntersix.android.comic.model;

import android.graphics.Bitmap;

public class Grouping {
	public String comicResourcesPath;
	public String name;
	public Bitmap thumbnail;
	public int[] pageBitMapIds;
	
	public Grouping(String comicResourcesPath, String name, Bitmap thumbnail, int[] pageBitMapIds) {
		this.comicResourcesPath = comicResourcesPath;
		this.name = name;
		this.thumbnail = thumbnail;
		this.pageBitMapIds = pageBitMapIds;
	}
}

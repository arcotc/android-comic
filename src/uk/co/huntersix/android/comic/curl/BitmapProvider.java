package uk.co.huntersix.android.comic.curl;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class BitmapProvider implements CurlView.BitmapProvider {
	private int[] bitmapIds;
	private Resources resources;

	public BitmapProvider(Resources resources, int[] bitmapIds) {
		this.resources = resources;
		this.bitmapIds = bitmapIds;
	}
	
	public Resources getResources() {
		return resources;
	}
	
	@Override
	public Bitmap getBitmap(int width, int height, int index) {
		Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		b.eraseColor(0xFFFFFFFF);
		Canvas c = new Canvas(b);
		Drawable d = getResources().getDrawable(bitmapIds[index]);

		int margin = 7;
		int border = 3;
		Rect r = new Rect(margin, margin, width - margin, height - margin);

		int imageWidth = r.width() - (border * 2);
		int imageHeight = imageWidth * d.getIntrinsicHeight() / d.getIntrinsicWidth();
		if (imageHeight > r.height() - (border * 2)) {
			imageHeight = r.height() - (border * 2);
			imageWidth = imageHeight * d.getIntrinsicWidth() / d.getIntrinsicHeight();
		}

		r.left += ((r.width() - imageWidth) / 2) - border;
		r.right = r.left + imageWidth + border + border;
		r.top += ((r.height() - imageHeight) / 2) - border;
		r.bottom = r.top + imageHeight + border + border;

		Paint p = new Paint();
		p.setColor(0xFFC0C0C0);
		c.drawRect(r, p);
		r.left += border;
		r.right -= border;
		r.top += border;
		r.bottom -= border;

		d.setBounds(r);
		d.draw(c);
		return b;
	}

	@Override
	public int getBitmapCount() {
		return bitmapIds.length;
	}
}

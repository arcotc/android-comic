package uk.co.huntersix.android.comic;

import uk.co.huntersix.android.comic.curl.CurlView;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SummaryDetailsFragment extends Fragment {
	private CurlView view;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	view = (CurlView)inflater.inflate(R.layout.curl, container, false);
    	
		int index = 0;
    	view.setBitmapProvider(new BitmapProvider());
    	view.setSizeChangedObserver(new SizeChangedObserver());
    	view.setCurrentIndex(index);
    	view.setBackgroundColor(0xFF202830);
    	
        return view;
    }

	private class BitmapProvider implements CurlView.BitmapProvider {
		private int[] mBitmapIds = { R.drawable.john_1_1125x1500, R.drawable.john_2_1125x1500 };

		@Override
		public Bitmap getBitmap(int width, int height, int index) {
			Bitmap b = Bitmap.createBitmap(width, height,
					Bitmap.Config.ARGB_8888);
			b.eraseColor(0xFFFFFFFF);
			Canvas c = new Canvas(b);
			Drawable d = getResources().getDrawable(mBitmapIds[index]);

			int margin = 7;
			int border = 3;
			Rect r = new Rect(margin, margin, width - margin, height - margin);

			int imageWidth = r.width() - (border * 2);
			int imageHeight = imageWidth * d.getIntrinsicHeight()
					/ d.getIntrinsicWidth();
			if (imageHeight > r.height() - (border * 2)) {
				imageHeight = r.height() - (border * 2);
				imageWidth = imageHeight * d.getIntrinsicWidth()
						/ d.getIntrinsicHeight();
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
			return mBitmapIds.length;
		}
	}

	/**
	 * CurlView size changed observer.
	 */
	private class SizeChangedObserver implements CurlView.SizeChangedObserver {
		@Override
		public void onSizeChanged(int w, int h) {
			if (w > h) {
				view.setViewMode(CurlView.SHOW_TWO_PAGES);
				view.setMargins(.1f, .05f, .1f, .05f);
			} else {
				view.setViewMode(CurlView.SHOW_ONE_PAGE);
				view.setMargins(.1f, .1f, .1f, .1f);
			}
		}
	}
}

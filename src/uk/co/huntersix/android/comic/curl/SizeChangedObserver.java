package uk.co.huntersix.android.comic.curl;

public class SizeChangedObserver implements CurlView.SizeChangedObserver {
	private CurlView view;
	
	public SizeChangedObserver(CurlView view) {
		this.view = view;
	}
	
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

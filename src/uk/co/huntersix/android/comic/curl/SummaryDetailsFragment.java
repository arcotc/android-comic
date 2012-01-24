package uk.co.huntersix.android.comic.curl;

import uk.co.huntersix.android.comic.R;
import uk.co.huntersix.android.comic.model.Grouping;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SummaryDetailsFragment extends Fragment {
	private Grouping grouping;
	private CurlView view;
	
	public SummaryDetailsFragment(Grouping grouping) {
		this.grouping = grouping;
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	view = (CurlView)inflater.inflate(R.layout.curl, container, false);

		try {
			Context context = this.getActivity().getApplicationContext();
			Resources comicR = context.getPackageManager().getResourcesForApplication(grouping.comicResourcesPath);

			int index = 0;
	    	view.setBitmapProvider(new BitmapProvider(comicR, grouping.pageBitMapIds));
	    	view.setSizeChangedObserver(new SizeChangedObserver(view));
	    	view.setCurrentIndex(index);
	    	view.setBackgroundColor(0xFF202830);    	
		} 
		catch (NameNotFoundException nameNotFoundException) {
			// TODO Auto-generated catch block
			nameNotFoundException.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

        return view;
    }
}

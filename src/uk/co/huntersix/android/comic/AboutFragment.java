package uk.co.huntersix.android.comic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AboutFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.about, container, false);

//	    final Button button = (Button) view.findViewById(R.id.closeButton);
	    
	    return view;
    }
}

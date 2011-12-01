package uk.co.huntersix.android.comic;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends FragmentActivity {
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	    
    	FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    	
    	SummaryListFragment summaryListFragment = new SummaryListFragment();
    	fragmentTransaction.replace(R.id.summarylist, summaryListFragment);
    	fragmentTransaction.commit();
	}
}

package uk.co.huntersix.android.comic;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	    
    	FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    	
    	SummaryListFragment summaryListFragment = new SummaryListFragment();
    	fragmentTransaction.replace(R.id.summarylist, summaryListFragment);
    	fragmentTransaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_menu, menu);
	    
	    return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
		    case R.id.about:
		        about();
		        return true;
		    default:
		        return super.onOptionsItemSelected(item);
	    }
	}
	
	private void about() {
		AboutFragment aboutFragment = new AboutFragment();
		
    	FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    	fragmentTransaction.replace(R.id.summarylist, aboutFragment);
    	fragmentTransaction.addToBackStack(null);
    	fragmentTransaction.commit();
	}
}

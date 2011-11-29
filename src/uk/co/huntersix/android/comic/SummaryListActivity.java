package uk.co.huntersix.android.comic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class SummaryListActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.summary_list);

	    GridView gridview = (GridView) findViewById(R.id.gridview);
	    gridview.setAdapter(new SummaryListAdapter(this));

	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	            Toast.makeText(SummaryListActivity.this, "" + position, Toast.LENGTH_SHORT).show();
	            Intent summaryListActivityIntent = new Intent(SummaryListActivity.this, SummaryDetailsActivity.class);
	            SummaryListActivity.this.startActivity(summaryListActivityIntent);
	        }
	    });
	}
}

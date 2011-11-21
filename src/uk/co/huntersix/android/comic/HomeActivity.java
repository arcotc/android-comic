package uk.co.huntersix.android.comic;

import java.util.ArrayList;
import java.util.List;

import uk.co.huntersix.android.comic.model.Grouping;
import uk.co.huntersix.android.comic.model.GroupingRow;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends ListActivity {
	private static final List<GroupingRow> GROUPINGS = new ArrayList<GroupingRow>();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ListView listView = getListView();
		
//		listView.addFooterView(findViewById(R.layout.menu));
		
		setListAdapter(new EfficientAdapter(this));
//		listView.setTextFilterEnabled(true);

		listView.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		      // When clicked, show a toast with the TextView text
		      Toast.makeText(getApplicationContext(), "You click me " + GROUPINGS.get(position).leftGrouping.name, Toast.LENGTH_SHORT).show();
		    }
		});
	}
	
	static final class EfficientAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		
        public EfficientAdapter(Context context) {
    		init(context);
    		
            // Cache the LayoutInflate to avoid asking for a new one each time.
            inflater = LayoutInflater.from(context);
        }

    	void init(Context context) {
//    		GROUPINGS.add(new Grouping("ABC", BitmapFactory.decodeResource(context.getResources(), R.drawable.icb_nt_cdrom_page_282)));
//    		GROUPINGS.add(new Grouping("DEF", BitmapFactory.decodeResource(context.getResources(), R.drawable.icb_nt_cdrom_page_283)));
//    		GROUPINGS.add(new Grouping("GHI", BitmapFactory.decodeResource(context.getResources(), R.drawable.icb_nt_cdrom_page_282)));
//    		GROUPINGS.add(new Grouping("JKL", BitmapFactory.decodeResource(context.getResources(), R.drawable.icb_nt_cdrom_page_283)));
//    		GROUPINGS.add(new Grouping("MNO", BitmapFactory.decodeResource(context.getResources(), R.drawable.icb_nt_cdrom_page_282)));
    		GROUPINGS.add(new GroupingRow(new Grouping("ABC"), new Grouping("DEF")));
    		GROUPINGS.add(new GroupingRow(new Grouping("GHI"), new Grouping("JKL")));
    		GROUPINGS.add(new GroupingRow(new Grouping("MNO")));
    	}

        @Override
		public int getCount() {
			return GROUPINGS.size();
		}

		@Override
		public Object getItem(int position) {
			return GROUPINGS.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View summaryView, ViewGroup parent) {
			ViewHolder viewHolder;
			
			if (summaryView == null) {
				summaryView = inflater.inflate(R.layout.summary, null);
				
				viewHolder = new ViewHolder();
				viewHolder.leftSummaryTitle = (TextView) summaryView.findViewById(R.id.cell1).findViewById(R.id.summaryTitle);
				viewHolder.leftThumbnail = (ImageView) summaryView.findViewById(R.id.cell1).findViewById(R.id.thumbnail);
				viewHolder.rightSummaryTitle = (TextView) summaryView.findViewById(R.id.cell2).findViewById(R.id.summaryTitle);
				viewHolder.rightThumbnail = (ImageView) summaryView.findViewById(R.id.cell2).findViewById(R.id.thumbnail);
				
                summaryView.setTag(viewHolder);
            } else {
                // Get the ViewHolder back to get fast access to the TextView
                // and the ImageView.
            	viewHolder = (ViewHolder) summaryView.getTag();
            }

            // Bind the data efficiently with the holder.
			viewHolder.leftSummaryTitle.setText(GROUPINGS.get(position).leftGrouping.name);
			viewHolder.leftThumbnail.setImageBitmap(GROUPINGS.get(position).leftGrouping.thumbnail);
			if (GROUPINGS.get(position).hasRightGrouping()) {
				viewHolder.rightSummaryTitle.setText(GROUPINGS.get(position).rightGrouping.name);
				viewHolder.rightThumbnail.setImageBitmap(GROUPINGS.get(position).rightGrouping.thumbnail);
			}
			
			return summaryView;
		}

		static class ViewHolder {
            TextView leftSummaryTitle;
            ImageView leftThumbnail;
            TextView rightSummaryTitle;
            ImageView rightThumbnail;
        }
	}
}
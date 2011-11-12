package uk.co.huntersix.android.comic;

import java.util.ArrayList;
import java.util.List;

import uk.co.huntersix.android.comic.model.Grouping;
import android.app.ListActivity;
import android.content.Context;
import android.graphics.BitmapFactory;
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
	private static final List<Grouping> GROUPINGS = new ArrayList<Grouping>();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setListAdapter(new EfficientAdapter(this));
		
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		      // When clicked, show a toast with the TextView text
		      Toast.makeText(getApplicationContext(), "You click me " + GROUPINGS.get(position).name, Toast.LENGTH_SHORT).show();
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
    		GROUPINGS.add(new Grouping("Genesis", BitmapFactory.decodeResource(context.getResources(), R.drawable.icb_nt_cdrom_page_282)));
    		GROUPINGS.add(new Grouping("John", BitmapFactory.decodeResource(context.getResources(), R.drawable.icb_nt_cdrom_page_283)));
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
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.summary, null);
				
				viewHolder = new ViewHolder();
				viewHolder.text = (TextView) convertView.findViewById(R.id.summaryTitle);
				viewHolder.icon = (ImageView) convertView.findViewById(R.id.thumbnail);

                convertView.setTag(viewHolder);
            } else {
                // Get the ViewHolder back to get fast access to the TextView
                // and the ImageView.
            	viewHolder = (ViewHolder) convertView.getTag();
            }

            // Bind the data efficiently with the holder.
			viewHolder.text.setText(GROUPINGS.get(position).name);
			viewHolder.icon.setImageBitmap(GROUPINGS.get(position).thumbnail);
			
			return convertView;
		}

		static class ViewHolder {
            TextView text;
            ImageView icon;
        }
	}
}
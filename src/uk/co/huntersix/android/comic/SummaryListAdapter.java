package uk.co.huntersix.android.comic;

import java.util.ArrayList;
import java.util.List;

import uk.co.huntersix.android.comic.model.Grouping;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SummaryListAdapter extends BaseAdapter {
	private static final List<Grouping> GROUPINGS = new ArrayList<Grouping>();
    private Context context;
    private LayoutInflater inflater;
    
    public SummaryListAdapter(Context context) {
        this.context = context;
        
        init();
        
        // Cache the LayoutInflate to avoid asking for a new one each time.
        inflater = LayoutInflater.from(context);    
    }
    
    private void init() {
		GROUPINGS.add(new Grouping("ABC", BitmapFactory.decodeResource(context.getResources(), R.drawable.book1_125x167)));
		GROUPINGS.add(new Grouping("DEF", BitmapFactory.decodeResource(context.getResources(), R.drawable.book2_125x167)));
		GROUPINGS.add(new Grouping("GHI", BitmapFactory.decodeResource(context.getResources(), R.drawable.book3_125x167)));
		GROUPINGS.add(new Grouping("JKL", BitmapFactory.decodeResource(context.getResources(), R.drawable.book4_125x167)));
		GROUPINGS.add(new Grouping("MNO", BitmapFactory.decodeResource(context.getResources(), R.drawable.book5_125x167)));
		GROUPINGS.add(new Grouping("PQR", BitmapFactory.decodeResource(context.getResources(), R.drawable.book6_125x167)));
		GROUPINGS.add(new Grouping("STU", BitmapFactory.decodeResource(context.getResources(), R.drawable.book7_125x167)));
		GROUPINGS.add(new Grouping("VWX", BitmapFactory.decodeResource(context.getResources(), R.drawable.book8_125x167)));
		GROUPINGS.add(new Grouping("YZ", BitmapFactory.decodeResource(context.getResources(), R.drawable.book1_125x167)));
    }

    public int getCount() {
        return GROUPINGS.size();
    }

    public Object getItem(int position) {
        return GROUPINGS.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.summary_list_item, null);
			
			viewHolder = new ViewHolder();
			viewHolder.summaryTitle = (TextView) convertView.findViewById(R.id.summaryTitle);
			viewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.thumbnail);
			
            convertView.setTag(viewHolder);
        } else {
            // Get the ViewHolder back to get fast access to the TextView
            // and the ImageView.
        	viewHolder = (ViewHolder) convertView.getTag();
        }

        // Bind the data efficiently with the holder.
		viewHolder.summaryTitle.setText(GROUPINGS.get(position).name);
		viewHolder.thumbnail.setImageBitmap(GROUPINGS.get(position).thumbnail);
    	
        return convertView;
    }
    
	static class ViewHolder {
        TextView summaryTitle;
        ImageView thumbnail;
    }
}
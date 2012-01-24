package uk.co.huntersix.android.comic;

import java.util.ArrayList;
import java.util.List;

import uk.co.huntersix.android.comic.model.Grouping;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

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
    
    public Grouping getGrouping(int i) {
    	return GROUPINGS.get(i);
    }
    
    private void init() {
		for (String comicResourcesPath : context.getResources().getString(R.string.available_comics).split(",")) {
			try {
				Resources comicR = context.getPackageManager().getResourcesForApplication(comicResourcesPath);
				int titlePageId = comicR.getIdentifier("title_page", "drawable", comicResourcesPath);
				int titleId = comicR.getIdentifier("title", "string", comicResourcesPath);
				GROUPINGS.add(new Grouping(comicResourcesPath,
										   comicR.getString(titleId), 
										   BitmapFactory.decodeResource(comicR, titlePageId), 
										   loadPagesFromResource(comicR, comicResourcesPath)));
			} 
			catch (NameNotFoundException nameNotFoundException) {
				// TODO Auto-generated catch block
				nameNotFoundException.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
    }
    
    private int[] loadPagesFromResource(Resources comicR, String comicResourcesPath) {
		Integer numberOfPages = new Integer(comicR.getString(comicR.getIdentifier("number_of_pages", "string", comicResourcesPath)));
    	int[] pageBitMapIds = new int[numberOfPages];
		String pagePrefix = comicR.getString(comicR.getIdentifier("page_prefix", "string", comicResourcesPath));
		for (int i=1; i<=numberOfPages; i++) {
			Integer bitMapId = comicR.getIdentifier(pagePrefix + i, "drawable", comicResourcesPath);
			pageBitMapIds[i - 1] = bitMapId;
		}
		
		return pageBitMapIds;
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
			convertView = inflater.inflate(R.layout.summary_list_item2, null);
			
			viewHolder = new ViewHolder();
//			viewHolder.summaryTitle = (TextView) convertView.findViewById(R.id.summaryTitle);
			viewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.thumbnail);
			
            convertView.setTag(viewHolder);
        } else {
            // Get the ViewHolder back to get fast access to the TextView
            // and the ImageView.
        	viewHolder = (ViewHolder) convertView.getTag();
        }

        // Bind the data efficiently with the holder.
//		viewHolder.summaryTitle.setText(GROUPINGS.get(position).name);
		viewHolder.thumbnail.setImageBitmap(GROUPINGS.get(position).thumbnail);
    	
        return convertView;
    }
    
	static class ViewHolder {
//        TextView summaryTitle;
        ImageView thumbnail;
    }
}
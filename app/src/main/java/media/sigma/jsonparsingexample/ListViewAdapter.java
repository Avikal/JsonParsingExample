package media.sigma.jsonparsingexample;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;
	ImageLoader imageLoader;
	HashMap<String, String> resultp = new HashMap<String, String>();
	public static String image_url;
	public ListViewAdapter(Context context,
			ArrayList<HashMap<String, String>> arraylist) {
		this.context = context;
		data = arraylist;
		imageLoader = new ImageLoader(context);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		// Declare Variables
		TextView rank;
		TextView country;
//		TextView population;
		ImageView flag;
		String complain_id;
//		String image_url;
//		Animation animation = null;
//		animation = AnimationUtils.loadAnimation(context, R.anim.shake);
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView = inflater.inflate(R.layout.listview_item, parent, false);
		// Get the position
		resultp = data.get(position);

		// Locate the TextViews in listview_item.xml
		rank = (TextView) itemView.findViewById(R.id.rank);
		country = (TextView) itemView.findViewById(R.id.country);
//		population = (TextView) itemView.findViewById(R.id.population);

		// Locate the ImageView in listview_item.xml
		flag = (ImageView) itemView.findViewById(R.id.flag);

		// Capture position and set results to the TextViews
		rank.setText(resultp.get(JSONListData.COLONY_NAME));
		country.setText(resultp.get(JSONListData.ADDRESS1));
		 
//		population.setText(resultp.get(ComplainList.POPULATION));
		complain_id = resultp.get(JSONListData.ID);
		
		image_url = JSONListData.img_url+complain_id+".png";
		// Capture position and set results to the ImageView
//		 Passes flag images URL into ImageLoader.class
		System.out.println("Country List List adapter k andar =  " + image_url);
		imageLoader.DisplayImage(image_url, flag);
		// Capture ListView item click
		itemView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Get the position
				resultp = data.get(position);
				Intent intent = new Intent(context, SingleItemView.class);
				// Pass the Complain Id
				intent.putExtra("complain_id", resultp.get(JSONListData.ID));
				// Pass the Description
				intent.putExtra("description", resultp.get(JSONListData.DESCRIPTION));
				// Pass the Address1
				intent.putExtra("address1", resultp.get(JSONListData.ADDRESS1));
				// Pass the Address2
				intent.putExtra("address2", resultp.get(JSONListData.ADDRESS2));
				// Pass the Ward Number
				intent.putExtra("ward_no", resultp.get(JSONListData.WARD_NO));
				// Pass the Colony Name
				intent.putExtra("colony_name", resultp.get(JSONListData.COLONY_NAME));
				// Pass the City
				intent.putExtra("city", resultp.get(JSONListData.CITY));
				// Pass the State
				intent.putExtra("state", resultp.get(JSONListData.STATE));
				// Pass all data img
				intent.putExtra("flag", JSONListData.img_url);
				// Start SingleItemView Class
				context.startActivity(intent);

			}
		});

		return itemView;
	}
}

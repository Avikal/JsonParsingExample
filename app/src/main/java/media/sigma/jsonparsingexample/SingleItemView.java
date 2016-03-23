package media.sigma.jsonparsingexample;

import java.util.HashMap;

 

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleItemView extends Activity {
	// Declare Variables
	String description;
	String address1;
	String address2;
	String ward_no;
	String colony_name;
	String city;
	String state;
	
	String flag;
	String position;
	String complain_id;
	String image_s;
	ImageView icon_back;
	ImageLoader imageLoader = new ImageLoader(this);
	HashMap<String, String> resultp = new HashMap<String, String>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from singleitemview.xml
		setContentView(R.layout.singleitemview);
		/*icon_back = (ImageView)findViewById(R.id.imageView1);
		icon_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent back = new Intent(v.getContext(),MainActivity.class);
				startActivity(back);
			}
		});*/
//		complain_id = resultp.get(MainActivity.ID);
		
		Intent i = getIntent();
		// Get the result of descrption
		description = i.getStringExtra("description");
		// Get the result of add1
		address1 = i.getStringExtra("address1");
		// Get the result of add2
		address2 = i.getStringExtra("address2");
		// Get the result of ward
		ward_no = i.getStringExtra("ward_no");
		// Get the result of colony
		colony_name  = i.getStringExtra("colony_name");
		// Get the result of city
		city = i.getStringExtra("city");
		// Get the result of state
		state = i.getStringExtra("state");
		// Get the result of flag
		flag = i.getStringExtra("flag");
		// Get the result of complain id
		complain_id = i.getStringExtra("complain_id");
		// Image set by the image_s;
		image_s = flag+complain_id+".png";
		System.out.println("Single class k andar = "+image_s);

		// Locate the TextViews in singleitemview.xml
		TextView txtdescrption = (TextView) findViewById(R.id.description);
		TextView txtadd1 = (TextView) findViewById(R.id.add1);
//		TextView txtadd2 = (TextView) findViewById(R.id.add_2);
//		TextView txtward = (TextView) findViewById(R.id.ward);
		TextView txtcolony = (TextView) findViewById(R.id.colony);
		TextView txtcity = (TextView) findViewById(R.id.city);
		TextView txtstate = (TextView) findViewById(R.id.state);

		// Locate the ImageView in singleitemview.xml
		ImageView imgflag = (ImageView) findViewById(R.id.flag);

		// Set results to the TextViews
		txtdescrption.setText(description);
		txtadd1.setText(address1);
//		txtadd2.setText(address2);
//		txtward.setText(ward_no);
		txtcolony.setText(colony_name);
		txtcity.setText(city);
		txtstate.setText(state);
		 

		// Capture position and set results to the ImageView
		// Passes flag images URL into ImageLoader.class
		imageLoader.DisplayImage(image_s, imgflag);
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
		super.onBackPressed();
		Intent intent = new Intent(SingleItemView.this,JSONListData.class);
		startActivity(intent);
		finish();
	}
}
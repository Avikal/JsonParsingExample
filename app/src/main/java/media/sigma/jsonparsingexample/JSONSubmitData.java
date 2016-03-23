package media.sigma.jsonparsingexample;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * Created by avikal on 3/22/2016.
 */
public class JSONSubmitData extends Activity {

    private static final String TAG = "MainActivity.java";
    byte imageInByte1[];
    ImageView camera;
    private EditText inputEmail;
    private EditText inputImi;
    String IMEI ;
    static String[] WardNumberchange;
    private static ArrayList<HashMap<String, String>> mylistArea = new ArrayList();
    static Spinner spinnercolony,spinnerCity,spinnerState;
    //	static Spinner spinnerward;
    static String wardnum;
    String title;
    static String image_str;
    String postalcode = "";
    TextView categery;
    EditText description, adress1, city, state;
    String descrption,address1,address2,_city,_state;
    TextView submit,show;
    private static final int CAMERA_REQUEST = 1;
    private static final int PICK_FROM_GALLERY = 2;

    Bitmap bitmap;
    /*private String[] WardNumber = { "Ward #1", "Ward #2", "Ward #3", "Ward #4",
            "Ward #5", "Ward #6", "Ward #7", "Ward #8", "Ward #9", "Ward #10",
            "Ward #11", "Ward #12" };
    */
    String email;
    String mobile_no;
    String setNum;
    //	private SQLiteHandler db;
    String regexStr = "[0-9]{10}";
    private String[] State= { "Andhra Pradesh","Andaman and Nicobar Islands",	"Arunachal Pradesh","Assam",
            "Bihar","Chandigarh","Chhattisgarh","Dadra and Nagar Haveli","Daman and Diu","Delhi ","Goa","Gujarat",
            "Haryana","Himachal Pradesh","Jammu & Kashmir","Jharkhand","Karnataka","Kerala","Lakshadweep",
            "Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha (Orissa)",
            "Puducherry","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana","Tripura","Uttar Pradesh",
            "Uttarakhand","West Bengal"
    };

    private String[] City = {"Indore","Jabalpur","Sagar","Bhopal","Rewa", "Satna", "Dhar", "Chhindwara", "Gwalior", "Ujjain",
            "Morena","West Nimar","Chhattarpur","Shivpuri","Bhind",	"Balaghat","Dewas","Vidisha","Ratlam","Mandsaur","Neemuch"};



    private String[] Calony = { "Anjani Nagar","Anurag Nagar","Abhinandan Nagar","Anudeshak Nagar",
            "Agreshen Nagar",
            "Ashish Nagar",
            "shish Vihar",
            "Anup Nagar",
            "Ashoka Nagar",
            "Ahilya-Mata Colony",
            "Adrash Nagar",
            "Anand Nagar",
            "Aditya Nagar",
            "Anand Colony",
            "Ashish Redisency",
            "Ahilya Nagar",
            "Annpurna nagar",
            "Aalok nagar",
            "Aalok Nagar NX",
            "Anurag nagar extension",
            "Ansar Colony",
            "Ashish Rejency",
            "Babaji  Nagar",
            "Benkhund Dham",
            "Bijali Nagar",
            "Brejeshwari NX",
            "Basant Vihar",
            "Bank Colony",
            "Book Brand Colony",
            "Bargal Colony",
            "Berathi Colony",
            "Bhagwandin Nagar",
            "Basant Shah Nagar",
            "B.K. Sindhi Colony",
            "Brajeswari Extension",
            "Bhawani puri Colony",
            "Bhoj Nagar",
            "Bakashi Bag Colony",
            "BrajVihar Colony",
            "Bajrang Nagar",
            "Bhakt Prahlaad Colony",
            "Berathi Colony No. 1",
            "Bhaktwaar Ram Nagar",

            "Chandan Nagar",
            "Clerk Colony Extension",
            "Classic Poornima",
            "Clerak Colony",
            "Chandra Lok Colony",
            "Chandra Nagar",
            "Choth Mal Colony",
            "Chadakya Puri",
            "Chameli Baag",

            "DilipSingh Colony",
            "Doctors Qwater",
            "Devi Indria Nagar",
            "DR. Ambethgar Nagar",
            "Dravid Nagar",
            "Dubay Colony",
            "Davrika Dish Nagar",
            "Dur Sanchar Colony",
            "Diamond Colony",
            "Dhar Kothi",
            "Dhanvantri Nagar",
            "Devi DinDayal Mishr Nagar",
            "Film Colony",
            "Gori Nagar",
            "Goyal Vihar",
            "Ganesh Puri",
            "Guru Kripa",
            "Girdh nagar",
            "Ganesh Colony",
            "GulMohar Colony",
            "GulMohan Colony",
            "Greater Brajeshwari",
            "GulMohr X",
            "Ganesh Dham",
            "Group Ha",
            "Goyal Nagar X",
            "Green Park Colony",
            "Gumasta Nagar",
            "Gopal Bag",
            "Godha Colony",
            "Green Land Colony",
            "Girdhan Nagar",
            "Goyal Nagar ",
            "Geeta Nagar",
            "Greater brajeswari Nagar",
            "Gandi Park",
            "Golden Place",
            "Godpade Colony",
            "Gopur Nagar",
            "Guru Nanak Colony",
            "Hemu Colony",
            "Harsh Colony",
            "MahaDev Totala Nagar",
            "Harijan Colony",
            "H.I. Duplex",
            "Indraloak Colony",
            "Indrepuri Colony",
            "Indrjeet Nagar",
            "Juna Risala",
            "Jati Colny",
            "Jahad Colony",
            "Joy-Bilder Colony",
            "Jawara Compound",
            "Johari Place",
            "Jagartri Nagar",
            "Javari Colony",
            "Jai Shree SindCat",
            "JaiRampur Colony",
            "Joshi Colony",
            "Janaki Nagar Main",
            "Janaki Nagar NX",
            "Janaki  Nagar Extension",
            "Jivan Deep Colony",
            "Jai Nagar",
            "Jawahar Nagar",

            "Kamla Nehru Nagar",
            "Kalapna Lok Colony",
            "Kaalnidhi Park",
            "Kealash Park",
            "Kanchan Bag",
            "Kaatoj Colony",
            "Kunjvan Colony",
            "Karshi Vihar Colony",
            "Kahti Vala Tank",
            "Krhanti Kripa Colony",
            "Kalind Kunj Samuh Aawas",
            "Kalind Kunj Samuh Aawas",
            "Kndhari Nagar",

            "Laokmanya Nagar",
            "Laadkana Nagar",
            "Lala Ram Nagar",
            "Lai Park Colony",
            "Lakshya Vihar A",
            "Lakshya Vihar B",
            "Lakshya Vihar C",


            "Menan Nagar",
            "Mangal Nagar",
            "Mechenic Nagar",
            "MeghDut Nagar",
            "MishrBandhu Colony",
            "Mahavari Bag",
            "Mahesh Nagar",
            "Manish Puri",
            "ManBhawan Nagar",
            "Mahaveer Dham",
            "M.I.G Sector A",
            "MeeraPath Colony",
            "Mahhavar Nagar",
            "Maduban Colony",
            "Moolplace Colony",
            "Modal Town",
            "Mangal Murti Colony",
            "Mohan Nagar",
            "Manish Baag",
            "MahaVeer Nagar",
            "Manni Dham Group",
            "Maa Vihar",
            "Mishra Nagar",
            "Mahila Polotechnique Colony",

            "Nilkhand Colony",
            "Narayan Bag",
            "Navratan Bag",
            "Nai Duniya Ke Paas",
            "Nami Nagar Jain Colony",
            "Nidhi Vihar",
            "NanadVan Colony",
            "New Safi Nagar",
            "New Agrwaal Nagar",
            "Nanak Nagar",
            "Nema Nagar",
            "Nalanda Parisar",
            "Narmada Nagar",
            "New Siyaganj",

            "Old Agrawaal Nagar",
            "Om Vihar",

            "Pallar Nagar",
            "Pushp Nagar",
            "Pandit Din Dayal Nagar",
            "Patel Nagar",
            "PantVedh Colony",
            "Patrakar Colony",
            "Paliwal Nagar",
            "Pallash Place",
            "Pallasiya HariJan Colony",
            "Park Road",
            "Parshav Nath Nagar",
            "Palace Colony",
            "Pratap Nagar",
            "Pram Nagar",
            "Palsikar Colony",
            "PanchSheel Nagar",
            "Prakash Nagar",
            "Pancvati Janaki Nagar",
            "Paricharika Nagar",
            "Pushp Vatika",
            "Padmavati Colony",
            "Profeesr Colony",
            "Paraspar Nagar",
            "Praphu Nagar",
            "Prinkako Colony",
            "Parash Nagar",
            "Pragati Nagar",
            "Parag Nagar",
            "Pradhikari Yojana 38",

            "RatanBag",
            "Raghuvanshi Colony",
            "Redimade Complex",
            "Radha Nagar",
            "Ram Chandra nagar",
            "Ram Chandra nagar NX",
            "Ravi Nagar",
            "Ravindra Nagar",
            "Revenu Nagar",
            "Rande Compound",
            "R.K. Puram",
            "Rajgadh Kothi Colony",
            "Rani Sati Colony",
            "Rigal Colony",
            "Ratlam Kothi",
            "Rajesh Nagar",
            "Rajsav Nagar",
            "Revenu Chaturth Varg Colony",
            "Raaj Mahal ",
            "Roop Ram Nagar",
            "Radhi Vatika",
            "Rajgrah Colony",
            "Royal Residancy Group",
            "Radhe Krishn Vihar",
            "RavRaja Park A",
            "Rahi Nagar Extension",
            "Radhika Places",

            "Scheme No. 51",
            "Scheme no. 54",
            "Scheme No. 74",
            "Scheme No. 78",
            "Scheme No. 94",
            "Scheme No. 114",
            "Shyam Nagar NX",
            "Sundar Nagar",
            "Sundar Nagar Extension",
            "Shyam Nagar NX",
            "Sikshak Nagar",
            "Sawasth Nagar",
            "Sai Kripa Colony",
            "Sheshradi Colony",
            "Shukdev Nagar",
            "Sagam Nagar,Sector A.B.C.",
            "Shanti Niketan",
            "Shalimar Township",
            "Scheme No 54 P.U. 3",
            "Shubash Nagar",
            "Shankar bag Colony",
            "Shastri Colony",
            "SitaRam Park",
            "Shulkla nagar",
            "Shakhala Colony",
            "Shadhana Nagar",
            "Samajwad Indira Nagar",
            "Shanti Nagar jain Colony",
            "Scheme No . 91",
            "Sanjay Nagar",
            "Shree Nagar X",
            "Shaket Colony",
            "Shankar Nagar",
            "Sawami Viveknand Nagar",
            "Scheme No. 94 piplyana",
            "Sanchar Nagar Main & NX",
            "Shittal Nagar",
            "Sahandhi Colony",
            "Soniya Gandhi Nagar",
            "Shree nagar Main",
            "State Bank Office Colony",
            "Sita Colony",
            "Shree Bilders Colony",
            "Swastik Nagar",
            "Scheme No 71 A.B.C.D",
            "Sudama Nagar",
            "Safi Nagar",
            "Schame No. 101",
            "Scheme No. 102",
            "Sindhu Nagar",
            "Sadhu Nagar",
            "Sneh Nagar",
            "Sarvdoy Nagar",
            "SaaJan Nagar",
            "Savaad nagar",
            "Shiv Moti Nagar",
            "Scheme No. 47",
            "Sai Nath Colony",
            "Shakti Nagar",
            "Sharma Groop Hoiusing",
            "Saward Vihar ",
            "Saward vatika",
            "Sai Vihar",
            "Seva Sardar Nagar",
            "Shiv Managl Nagar",
            "Shree G Vatika",
            "Sadhu Vaasvaami Nagar",
            "Shivam Puri",
            "Sheetal Nagar ",
            "Sawdesh Nagar",
            "Sawami Dayanand Nagar",
            "Sarnath Colony",
            "Scheme No. 59",
            "Silver Place",
            "Silver Ox",
            "Saraswatoi Nagar",
            "SidhiPuram Nagar",
            "Sandipani Parisar",
            "Sidharth Nagar",
            "Shivmurti Samhu Aawas",
            "Shree kant Place",
            "Samhu Aawas Yojana",
            "SukhDev Vihar",
            "Surya Shukham Block A",
            "Surya Shukham Block B",
            "Surya Shukham Block C",
            "Surya Shukham Block D",
            "Surya Shukham Block E",
            "Surya Shukham Block F",
            "Suyash Ancleav",
            "Shubh Labh Nagar A",
            "Shubh Labh Nagar B",
            "Shiv Shakti Extension",
            "Shree Sai Nath Colony Sector",
            "Sukh sampada Samhu aawas",
            "Sarv Suvidha Nagar",
            "Sukh Shanti Nagar",

            "Teliphone Nagar",
            "Teliphone Nagar X",
            "TreeVeni Nagar& X",
            "Tilak Nagar Main & Extension",
            "Tirupati Colony",
            "TakshShila aawasi Parisar",
            "Tirupati Nagar",
            "Tirupati NX",

            "Udai Nagar",
            "Utkrash Vihar Group",
            "Usha Nagar Main & Extnsion",
            "Udhyogah Nagar",
            "Umesh Nagar",

            "Vanktyesh Nagar",
            "Valamiki Nagar",
            "Vrandavan Nagar",
            "Veena Nagar",
            "Veena Nagar X",
            "Vijay Nagar Main",
            "Vijay Nagar X",
            "Vindhya Chal Colony",
            "VardhMan Nagar",
            "Vikash Nagar",
            "Vishram Colony",
            "Vrandavan Dham",
            "Vallabh Nagar",
            "Vijay  Nagar(SindyCat)",
            "Vinay Nagar",
            "Vijay Nagar",
            "Veersarvakar Nagar",
            "Vashudev Nagar",
            "Vanktyesh Market",
            "Vandana Nagar",
            "Vidhya Nagar",
            "Vishnupuri Colony & X",
            "Veshali Nagar",
            "V.I.P Paraspar Nagar",
            "Virat Nagar",
            "Vishal Nagar",
            "Visavkarma Nagar",
            "Vaibhav Nagar Extension",

            "Yasvant Colony",
            "Yojana Kramank 91 ",
            "Yojana Kramank 103",
            "Yojana Kramank 53",
            "Yojana Kramank 78 Part 1",
            "Yojana Kramank 78 Part 2",
            "Yojana Kramank 114 First",
            "Yojana Kramank 114 Second",
            "Yojana Kramank bhaag 4",
            "Yojana Kramank 59 Second",
            "Yojana Kramank 94 Sector h",
            "Yojana Kramank 94 Sector F" };

    static {
        WardNumberchange = new String[13];
        wardnum = "0";
    }
    // DatabaseHandler dbHandler = new DatabaseHandler(this);
    String valid_descrption, valid_adress1, valid_adress2,
            valid_ward1 = null, vaild_ward2 = null, valid_city = null,
            valid_state = null, Toast_msg = null, valid_user_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page2);
        set_Add();
        inputEmail = (EditText) findViewById(R.id.email);
        inputImi = (EditText) findViewById(R.id.imei);
        camera = (ImageView) findViewById(R.id.btn_camera);
        final String[] option = new String[] { "Take from Camera",
                "Select from Gallery" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, option);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Select Option");
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Log.e("Selected Item", String.valueOf(which));
                if (which == 0) {
                    callCamera();
                }
                if (which == 1) {
                    callGallery();
                }

            }
        });
        TelephonyManager tMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        String mPhoneNumber = tMgr.getLine1Number();

        IMEI = tMgr.getDeviceId();
        if (IMEI != null)   {
            inputImi.setText(IMEI);
            inputImi.setEnabled(false);
        }
        AccountManager am = AccountManager.get(this);
        Account[] accounts = am.getAccounts();
        for (Account ac : accounts) {
            String acname = ac.name;
            String actype = ac.type;
            if (acname.startsWith("91")) {
                mobile_no = acname;
            } else if (acname.endsWith("@gmail.com")
                    || acname.endsWith("@yahoo.com")
                    || acname.endsWith("@hotmail.com")) {
                email = acname;
            }
            if(actype.equals("com.whatsapp")){
                mobile_no = ac.name;
            }
        }

        inputEmail.setText(email);
        inputEmail.setEnabled(false);
        final AlertDialog dialog = builder.create();

        camera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.show();
            }
        });

        submit = (TextView) findViewById(R.id.btn_next);
        show = (TextView) findViewById(R.id.btn_show);
        categery = (TextView) findViewById(R.id.categorytitle);

        Bundle bundle = getIntent().getExtras();
        // title = bundle.getString("title");
        categery.setText("Complain Here!!!");
//		spinnerward = (Spinner) findViewById(R.id.spinnerward);
        spinnercolony = (Spinner) findViewById(R.id.spinnercolony);
        spinnerCity = (Spinner) findViewById(R.id.spinnercity);
        spinnerState = (Spinner) findViewById(R.id.spinnerstate);
		/*ArrayAdapter localArrayAdapter = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, this.WardNumber);
		localArrayAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/
        ArrayAdapter localArrayAdapter1 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, this.State);
        localArrayAdapter1
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerState.setAdapter(localArrayAdapter1);
        spinnerState
                .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> paramAdapterView,
                                               View paramView, int paramInt, long paramLong) {
                        try {
                            String str = (String) JSONSubmitData.spinnerState
                                    .getSelectedItem();
                            valid_state = str;
                            JSONSubmitData.this.postalcode = str;
                            System.out.println("else spinnerState" + str);
                            return;
                        } catch (Exception localException) {
                        }
                    }

                    public void onNothingSelected(
                            AdapterView<?> paramAdapterView) {
                    }
                });

        ArrayAdapter localArrayAdapter2 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, this.City);
        localArrayAdapter2
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(localArrayAdapter2);
        spinnerCity
                .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> paramAdapterView,
                                               View paramView, int paramInt, long paramLong) {
                        try {
                            String str = (String) JSONSubmitData.spinnerCity
                                    .getSelectedItem();
//							 = str;
                            JSONSubmitData.this.postalcode = str;
                            System.out.println("else spinnerState" + str);
                            return;
                        } catch (Exception localException) {
                        }
                    }

                    public void onNothingSelected(
                            AdapterView<?> paramAdapterView) {
                    }
                });
        ArrayAdapter localArrayAdapter3 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, this.Calony);
        localArrayAdapter3
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		/*spinnerward.setAdapter(localArrayAdapter);
		spinnerward
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> paramAdapterView,
							View paramView, int paramInt, long paramLong) {
						try {
							MainActivity.spinnerward.setSelection(paramInt);
							MainActivity.wardnum = (String) MainActivity.spinnerward
									.getSelectedItem();
							String str1 = (String) MainActivity.spinnerward
									.getSelectedItem();
							valid_ward1 = str1;
							System.out.println("else wardnum"
									+ MainActivity.wardnum);
							// ActivityMain2.access$0(ActivityMain2.this,
							// "vmc.xls");
							return;
						} catch (Exception localException) {
						}
					}

					public void onNothingSelected(
							AdapterView<?> paramAdapterView) {
					}
				});*/
        spinnercolony.setAdapter(localArrayAdapter3);
        spinnercolony
                .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> paramAdapterView,
                                               View paramView, int paramInt, long paramLong) {
                        try {
                            String str = (String) JSONSubmitData.spinnercolony
                                    .getSelectedItem();
                            vaild_ward2 = str;
                            JSONSubmitData.this.postalcode = str;
                            System.out.println("else spinnerState" + str);
                            return;
                        } catch (Exception localException) {
                        }
                    }

                    public void onNothingSelected(
                            AdapterView<?> paramAdapterView) {
                    }
                });
        valid_descrption = description.getText().toString();
        valid_adress1 = adress1.getText().toString();
//		valid_adress2 = adress2.getText().toString();
//		valid_city = city.getText().toString();
//		valid_state = state.getText().toString();

        // we are going to use asynctask to prevent network on main thread
        // exception
        submit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                valid_descrption = description.getText().toString();
                valid_adress1 = adress1.getText().toString();
                email = inputEmail.getText().toString();
                IMEI = inputImi.getText().toString();
//				valid_adress2 = adress2.getText().toString();
                new PostDataAsyncTask().execute();
                Toast.makeText(getApplicationContext(), "Data Submiteed", Toast.LENGTH_SHORT).show();
                Reset_Text();

            }
        });
        show.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),JSONListData.class);
                startActivity(intent);

            }
        });

    }
    public void Reset_Text() {

        description.getText().clear();
        adress1.getText().clear();
//		adress2.getText().clear();

        // city.getText().clear();
        // state.getText().clear();

    }
    public void set_Add() {
        description = (EditText) findViewById(R.id.editText_comment);
        adress1 = (EditText) findViewById(R.id.edt_address1);
//		adress2 = (EditText) findViewById(R.id.edt_address2);
//		city = (EditText) findViewById(R.id.edt_city);
//		state = (EditText) findViewById(R.id.edt_state);




    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return;

        switch (requestCode) {
            case CAMERA_REQUEST:

                Bundle extras = data.getExtras();

                if (extras != null) {
                    Bitmap yourImage = extras.getParcelable("data");
                    // convert bitmap to byte
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    yourImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte imageInByte[] = stream.toByteArray();

                    image_str = Base64.encodeBytes(imageInByte);

                    imageInByte1 = imageInByte;
                    Log.e("output before conversion", imageInByte.toString());
                    // Inserting Contacts
                    Log.d("Insert: ", "Inserting ..");
                    camera.setImageBitmap(yourImage);
                    // db.addContact(new Contact(resultCode, "Android",
                    // imageInByte));
                    // Intent i = new Intent(CmplainActivity.this,
                    // CmplainActivity.class);
                    // startActivity(i);
                    // finish();

                }
                break;
            case PICK_FROM_GALLERY:
                Bundle extras2 = data.getExtras();

                if (extras2 != null) {
                    Bitmap yourImage = extras2.getParcelable("data");
                    // convert bitmap to byte
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    yourImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte imageInByte[] = stream.toByteArray();
                    image_str = Base64.encodeBytes(imageInByte);

                    Log.e("output before conversion", imageInByte.toString());
                    // Inserting Contacts
                    Log.d("Insert: ", "Inserting ..");

                    camera.setImageBitmap(yourImage);
                    imageInByte1 = imageInByte;
                    // db.addContact(new Contact(resultCode, "Android",
                    // imageInByte));
                    // Intent i = new Intent(CmplainActivity.this,
                    // CmplainActivity.class);
                    // startActivity(i);
                    // finish();
                }

                break;
        }
    }

    /**
     * open camera method
     */
    public void callCamera() {
        Intent cameraIntent = new Intent(
                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra("crop", "true");
        cameraIntent.putExtra("aspectX", 0);
        cameraIntent.putExtra("aspectY", 0);
        cameraIntent.putExtra("outputX", 200);
        cameraIntent.putExtra("outputY", 150);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);

    }

    /**
     * open gallery method
     */

    public void callGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 0);
        intent.putExtra("aspectY", 0);
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(
                Intent.createChooser(intent, "Complete action using"),
                PICK_FROM_GALLERY);

    }

    public class PostDataAsyncTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            // do stuff before posting data
        }

        @Override
        protected String doInBackground(String... strings) {
            try {

                // 1 = post text data, 2 = post file
                int actionChoice = 1;

                // post a text data
                if (actionChoice == 1) {
                    postText();
                }

                // post a file
                else {
//					postFile();
                }

            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String lenghtOfFile) {
            // do stuff after posting data
        }
    }

    // this will post our text data
    private void postText() {
        try {
            // url where the data will be posted
            String postReceiverUrl = "http://sigmamtech.com/indoreCity/index.php/smartcity/data_submit";
            Log.v(TAG, "postURL: " + postReceiverUrl);

            // HttpClient
            HttpClient httpClient = new DefaultHttpClient();

            // post header
            HttpPost httpPost = new HttpPost(postReceiverUrl);

            // add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("image", image_str));
            nameValuePairs.add(new BasicNameValuePair("email", email));
            nameValuePairs.add(new BasicNameValuePair("imie", IMEI));
            nameValuePairs.add(new BasicNameValuePair("description", valid_descrption));
            nameValuePairs.add(new BasicNameValuePair("address1", valid_adress1));
            nameValuePairs.add(new BasicNameValuePair("address2",valid_adress2));

            nameValuePairs.add(new BasicNameValuePair("wardNo", valid_ward1));
            nameValuePairs.add(new BasicNameValuePair("colonyName",
                    vaild_ward2));
            nameValuePairs.add(new BasicNameValuePair("city",
                    valid_city));
            nameValuePairs.add(new BasicNameValuePair("state",
                    valid_state));

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // execute HTTP post request
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity resEntity = response.getEntity();

            if (resEntity != null) {

                String responseStr = EntityUtils.toString(resEntity).trim();
                Log.v(TAG, "Response: " + responseStr);

                // you can add an if statement here and do other actions based
                // on the response
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub

        super.onBackPressed();
        Intent intent = new Intent(JSONSubmitData.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    // will post our text file
	/*private void postFile() {
		try {

			// the file to be posted
			String textFile = Environment.getExternalStorageDirectory()
					+ "/sample.txt";
			Log.v(TAG, "textFile: " + textFile);

			// the URL where the file will be posted
			String postReceiverUrl = "http://sigmamtech.com/indoreCity/index.php/smartcity/data_submit";
			Log.v(TAG, "postURL: " + postReceiverUrl);

			// new HttpClient
			HttpClient httpClient = new DefaultHttpClient();

			// post header
			HttpPost httpPost = new HttpPost(postReceiverUrl);

			File file = new File(textFile);
			FileBody fileBody = new FileBody(file);

			MultipartEntity reqEntity = new MultipartEntity(
					HttpMultipartMode.BROWSER_COMPATIBLE);
			reqEntity.addPart("file", fileBody);
			httpPost.setEntity(reqEntity);

			// execute HTTP post request
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity resEntity = response.getEntity();

			if (resEntity != null) {

				String responseStr = EntityUtils.toString(resEntity).trim();
				Log.v(TAG, "Response: " + responseStr);

				// you can add an if statement here and do other actions based
				// on the response
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}

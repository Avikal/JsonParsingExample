package media.sigma.jsonparsingexample;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by avikal on 3/22/2016.
 */
public class JSONListData extends AppCompatActivity
{
    ProgressDialog mProgressDialog;
    JSONObject jsonobject;
    JSONArray jsonarray;
    ListView listview;
    ListViewAdapter adapter;

    static String DESCRIPTION = "description";
    static String ADDRESS1 = "address1";
    static String ADDRESS2 = "address2";
    static String WARD_NO = "ward_number";
    static String COLONY_NAME = "colony_name";
    static String CITY = "city";
    static String STATE = "state";
    static String ID = "complain_id";
    public static String img_url = "http://sigmamtech.com/indoreCity/uploads/";
    ArrayList<HashMap<String,String>> arrayList;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.json_show);

        new DownloadJSON().execute();
    }

    private class DownloadJSON extends AsyncTask<Void, Void, Void>
    {
       @Override
       protected void onPreExecute()
       {
           super.onPreExecute();
           mProgressDialog = new ProgressDialog(JSONListData.this);
           mProgressDialog.setMessage("Loading...");
           mProgressDialog.setIndeterminate(false);
           mProgressDialog.show();

       }
        @Override
        protected Void doInBackground(Void... params)
        {

            arrayList = new ArrayList<HashMap<String, String>>();

            jsonobject = JSONfunctions.getJSONfromURL("http://sigmamtech.com/indoreCity/index.php/smartcity/getComplainList");

            try
         {
            jsonarray = jsonobject.getJSONArray("result");

            for(int i=0; i<jsonarray.length();i++)
            {
                HashMap<String, String> map = new HashMap<String,String>();
                jsonobject = jsonarray.getJSONObject(i);

                map.put("complain_id", jsonobject.getString("complain_id"));
                map.put("descripation" , jsonobject.getString("description"));
                map.put("address1", jsonobject.getString("address1"));
                map.put("address2", jsonobject.getString("address2"));
                map.put("ward_number", jsonobject.getString("ward_number"));
                map.put("colony_name", jsonobject.getString("colony_name"));
                map.put("city", jsonobject.getString("city"));
                map.put("state", jsonobject.getString("state"));
                map.put("flag", img_url);

                arrayList.add(map);
            }
         }catch (JSONException e)
         {
             e.printStackTrace();
         }
         return null;
        }

        @Override
        public void onPostExecute(Void args)
        {
            listview = (ListView) findViewById(R.id.listview);
            adapter = new ListViewAdapter(JSONListData.this,arrayList);
            listview.setAdapter(adapter);
            mProgressDialog.dismiss();
        }


    }
}

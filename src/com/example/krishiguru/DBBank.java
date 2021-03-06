package com.example.krishiguru;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.example.krishiguru.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class DBBank extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		ArrayList<HashMap<String, String>> BankList = new ArrayList<HashMap<String, String>>();
		String response = null;
		try {
    		response= CustomHttpClient.executeHttpGet("http://203.199.146.117/getbankname.php");	
            response=response.toString();
            response = response.trim();
            	    JSONArray obj=(JSONArray) new JSONTokener(response).nextValue();
            	    for(int i = 0; i < obj.length(); i++){
        				JSONObject c = obj.getJSONObject(i);
        				String name = c.getString("name") ;
        				
        				HashMap<String, String> map = new HashMap<String, String>();
        				map.put("name", name);
        				BankList.add(map);
            	    }
			}
    		catch (Exception e) {
    		Log.e("Exception Occured","An Error has occured fetching data");
    	} 	
    	final ListAdapter adapter = new SimpleAdapter(this, BankList,
				R.layout.list_item,
				new String[] { "name" }, new int[] {
						R.id.name });

		setListAdapter(adapter);
		ListView lv = getListView();

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
					String vari= adapter.getItem(position).toString();
				vari=vari.substring(6, vari.length()-1);
				Intent in = new Intent(getApplicationContext(), Bankdetail.class);
				in.putExtra("TAG_VARI",vari);
				startActivity(in);
			}
		});
	}
	
	
	

}

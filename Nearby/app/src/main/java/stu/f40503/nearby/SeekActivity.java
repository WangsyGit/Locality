package stu.f40503.nearby;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SearchView;

public class SeekActivity extends Activity {
	private SearchView srv;
	private Button record1;
	private Button record2;
	private Button record3;
	private Button record4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seek);
		
		File file2 = new File("searchRecord.txt");
		InputStreamReader inputFile2 = null;
		try {
			inputFile2 = new InputStreamReader(new FileInputStream(file2), "GB2312");
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader reader2 = new BufferedReader(inputFile2);
		String line2;
		ArrayList newRecord = new ArrayList();
		try {
			while((line2 = reader2.readLine()) != null){
				newRecord.add(line2);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(newRecord.size() == 0){
			record1.setText("");
			record2.setText("");
			record3.setText("");
			record4.setText("");
		}
		else if(newRecord.size() == 1){
			record1.setText("");
			record2.setText("");
			record3.setText("");
			record4.setText(newRecord.get(0)+"");
		}
		else if(newRecord.size() == 2){
			record1.setText("");
			record2.setText("");
			record3.setText(newRecord.get(1)+"");
			record4.setText(newRecord.get(0)+"");
		}
		else if(newRecord.size() == 3){
			record1.setText("");
			record2.setText(newRecord.get(2)+"");
			record3.setText(newRecord.get(1)+"");
			record4.setText(newRecord.get(0)+"");
		}
		else if(newRecord.size() == 4){
			record1.setText(newRecord.get(3)+"");
			record2.setText(newRecord.get(2)+"");
			record3.setText(newRecord.get(1)+"");
			record4.setText(newRecord.get(0)+"");
		}
		
		srv = (SearchView)findViewById(R.id.searchView1);
		srv.setSubmitButtonEnabled(true);
		srv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			
			@Override
			public boolean onQueryTextSubmit(String arg0) {
				// TODO Auto-generated method stub
				String searchContent = srv.getContext().getResources().getString(R.id.searchView1);//not sure
				File file = new File("searchRecord.txt");
				int i = 0;
				ArrayList record = new ArrayList();
				if (file.exists()) {
					InputStreamReader inputFile = null;
					try {
						inputFile = new InputStreamReader(new FileInputStream(file), "GB2312");
					} catch (UnsupportedEncodingException
							| FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					BufferedReader reader = new BufferedReader(inputFile);
					String line;
					try {
						while((line=reader.readLine()) != null){
							i++;
							record.add(line);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					file.delete();
				}
				try {
					file.createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				BufferedWriter output = null;
				try {
					output = new BufferedWriter(new FileWriter(file));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(i == 4){
					try {
						output.write(record.get(1)+"");
						output.newLine();
						output.write(record.get(2)+"");
						output.newLine();
						output.write(record.get(3)+"");
						output.newLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(i < 4){
					for(int j = 0; j < i; j++){
						try {
							output.write(record.get(j)+"");
							output.newLine();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				try {
					output.write(searchContent);
					output.newLine();
					output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				 /*
		        *
		        * search for info
		        *
		        * &
		        *
		        * direct to the info xml
		        *
		        * */
				Intent intent1 = new Intent(SeekActivity.this, ResultActivity.class);
                startActivity(intent1);
				return true;
			}
			
			@Override
			public boolean onQueryTextChange(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}
		});		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.seek, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

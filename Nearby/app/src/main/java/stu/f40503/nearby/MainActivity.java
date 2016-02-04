package stu.f40503.nearby;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.bluetooth.BluetoothAdapter;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.bluetooth);

        BluetoothAdapter bta;
        bta = BluetoothAdapter.getDefaultAdapter();
        TextView status = (TextView)findViewById(R.id.bluetooth);
        if (!bta.isEnabled()) {
            bta.enable();
        }
        status.setText("蓝牙已开启");
        /*
        *
        * connect the bluetooth
        *
        * &
        *
        * determine whether the bluetooth has correctly connected
        *
        * */
        Button enter = (Button)findViewById(R.id.enter);
        enter.setEnabled(true);
        Button.OnClickListener listener = new Button.OnClickListener() {
            public void onClick (View v) {
                Intent intent=new Intent(MainActivity.this,CameraActivity.class);
                startActivity(intent);
                /*
                *
                * move to the
                *
                * main page
                *
                *
                *
                *
                *
                *
                * */
            }
        };
        enter.setOnClickListener(listener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.kappspot.karthikmam.textinverter;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.*;


public class MainActivity extends Activity implements DialogInterface.OnClickListener{


    char invertedCharactersLower[] = { 'z', 'ʎ', 'x', 'ʍ', 'ʌ', 'n', 'ʇ', 's', 'ɹ', 'b', 'd', 'o', 'u', 'ɯ', 'l', 'ʞ', 'ɾ', 'ᴉ', 'ɥ', 'ƃ', 'ɟ', 'ǝ', 'p', 'ɔ', 'q', 'ɐ' };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button okButton = (Button) findViewById(R.id.btOk);
        final EditText inputText = (EditText) findViewById(R.id.etInput);
        final TextView outputText = (TextView) findViewById(R.id.tvResult);
        final Button copyButton = (Button) findViewById(R.id.btCopy);
        final Button shareButton = (Button) findViewById(R.id.btShare);

        okButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = inputText.getText().toString();
                String output = "";
                for(int i=0; i<input.length(); i++)
                {
                    if(input.charAt(i) >= 'a' && input.charAt(i) <= 'z')
                    {
                        output = invertedCharactersLower[25 - (input.charAt(i) - 'a')] + output;
                    }
                    else if(input.charAt(i) >= 'A' && input.charAt(i) <= 'Z')
                    {
                        output = invertedCharactersLower[25 - (input.charAt(i) - 'A')] + output;
                    }
                    else
                    {
                        output = input.charAt(i) + output;
                    }
                }
                outputText.setText(Html.fromHtml(output));
            }
        });

        copyButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager ClipMan = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("output", outputText.getText());
                ClipMan.setPrimaryClip(clip);

                Toast copySuccess = Toast.makeText(getApplicationContext(), "Text copied to clipboard", Toast.LENGTH_SHORT);
                copySuccess.show();
            }
        });


        shareButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, outputText.getText().toString());
                startActivity(shareIntent);
            }
        });
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

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }
}

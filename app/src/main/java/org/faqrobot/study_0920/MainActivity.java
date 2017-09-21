package org.faqrobot.study_0920;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "______________";

    EditText editText;
    TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.text);
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i==TextToSpeech.SUCCESS){
                    int result = textToSpeech.setLanguage(Locale.US);
                    if(result!=TextToSpeech.LANG_AVAILABLE&&result!=TextToSpeech.LANG_COUNTRY_AVAILABLE){
                        Log.e(TAG, "不支持US");
                    }
                }
            }
        });
        findViewById(R.id.button_speck).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.speak(editText.getText().toString().trim(), TextToSpeech.QUEUE_FLUSH, null, "speech");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        textToSpeech.shutdown();
    }
}

package com.example.user.weathertestapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.weathertestapp.util.ServerUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class MainActivity extends BaseActivity {

    private android.widget.TextView locationTxt;
    private android.widget.TextView weatherTxt;
    private android.widget.TextView temperatureTxt;
    private android.widget.TextView maxTemperatureTxt;
    private android.widget.TextView minTemperatureTxt;
    private android.widget.TextView windTxt;
    private android.widget.TextView windSpeedTxt;
    private android.widget.EditText latitudeEdt;
    private android.widget.EditText longitudeEdt;
    private android.widget.Button okBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        setUpEvents();
        setValues();
    }

    @Override
    public void setUpEvents() {
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServerUtil.getCurrentWeatherFromServer(mContext, new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
//                서버에서 응답이 오면 자동으로 실행되는 부분
//                json이 재료로 날아오는걸 분석해서 화면에 뿌려주기
                        try {
                            JSONObject firstObj = json.getJSONObject("weather").getJSONArray("minutely").getJSONObject(0);

                            String location = firstObj.getJSONObject("station").getString("name");
                            locationTxt.setText(location);

                            String sky = firstObj.getJSONObject("sky").getString("name");
                            weatherTxt.setText(sky);

                            String temperature = firstObj.getJSONObject("temperature").getString("tc");
                            float tc = Float.parseFloat(temperature);
                            String tcStr = String.format(Locale.KOREA, "%.1f ℃", tc);
                            temperatureTxt.setText(tcStr);
                            String temMax = firstObj.getJSONObject("temperature").getString("tmax");
                            float tmax = Float.parseFloat(temMax);
                            String tmaxStr = String.format(Locale.KOREA, "%.1f ℃", tmax);
                            maxTemperatureTxt.setText(tmaxStr);
                            String temMin = firstObj.getJSONObject("temperature").getString("tmin");
                            float tmin = Float.parseFloat(temMin);
                            String tminStr = String.format(Locale.KOREA, "%.1f ℃", tmin);
                            minTemperatureTxt.setText(tminStr);

                            String wdir = firstObj.getJSONObject("wind").getString("wdir");
                            windTxt.setText(wdir);
                            String wspd = firstObj.getJSONObject("wind").getString("wspd");
                            windSpeedTxt.setText(wspd);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, latitudeEdt.getText().toString(), longitudeEdt.getText().toString());
            }
        });
    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.windSpeedTxt = (TextView) findViewById(R.id.windSpeedTxt);
        this.windTxt = (TextView) findViewById(R.id.windTxt);
        this.minTemperatureTxt = (TextView) findViewById(R.id.minTemperatureTxt);
        this.maxTemperatureTxt = (TextView) findViewById(R.id.maxTemperatureTxt);
        this.temperatureTxt = (TextView) findViewById(R.id.temperatureTxt);
        this.weatherTxt = (TextView) findViewById(R.id.weatherTxt);
        this.locationTxt = (TextView) findViewById(R.id.locationTxt);
        this.okBtn = (Button) findViewById(R.id.okBtn);
        this.longitudeEdt = (EditText) findViewById(R.id.longitudeEdt);
        this.latitudeEdt = (EditText) findViewById(R.id.latitudeEdt);
    }
}

package com.example.user.weathertestapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
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

    }

    @Override
    public void setValues() {
        String jsonStr = "{\n" +
                "    \"weather\":{\n" +
                "        \"minutely\":[\n" +
                "            {\n" +
                "                \"station\":{\n" +
                "                    \"longitude\":\"127.06036\",\n" +
                "                    \"latitude\":\"37.58463\",\n" +
                "                    \"name\":\"동대문\",\n" +
                "                    \"id\":\"408\",\n" +
                "                    \"type\":\"KMA\"\n" +
                "                },\n" +
                "                \"wind\":{\n" +
                "                    \"wdir\":\"179.50\",\n" +
                "                    \"wspd\":\"1.60\"\n" +
                "                },\n" +
                "                \"precipitation\":{\n" +
                "                    \"sinceOntime\":\"0.00\",\n" +
                "                    \"type\":\"0\"\n" +
                "                },\n" +
                "                \"sky\":{\n" +
                "                    \"code\":\"SKY_A01\",\n" +
                "                    \"name\":\"맑음\"\n" +
                "                },\n" +
                "                \"rain\":{\n" +
                "                    \"sinceOntime\":\"0.00\",\n" +
                "                    \"sinceMidnight\":\"0.00\",\n" +
                "                    \"last10min\":\"0.00\",\n" +
                "                    \"last15min\":\"0.00\",\n" +
                "                    \"last30min\":\"0.00\",\n" +
                "                    \"last1hour\":\"0.00\",\n" +
                "                    \"last6hour\":\"0.00\",\n" +
                "                    \"last12hour\":\"0.00\",\n" +
                "                    \"last24hour\":\"0.00\"\n" +
                "                },\n" +
                "                \"temperature\":{\n" +
                "                    \"tc\":\"25.50\",\n" +
                "                    \"tmax\":\"28.00\",\n" +
                "                    \"tmin\":\"17.00\"\n" +
                "                },\n" +
                "                \"humidity\":\"40.60\",\n" +
                "                \"pressure\":{\n" +
                "                    \"surface\":\"\",\n" +
                "                    \"seaLevel\":\"\"\n" +
                "                },\n" +
                "                \"lightning\":\"0\",\n" +
                "                \"timeObservation\":\"2017-09-01 12:08:00\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"common\":{\n" +
                "        \"alertYn\":\"Y\",\n" +
                "        \"stormYn\":\"N\"\n" +
                "    },\n" +
                "    \"result\":{\n" +
                "        \"code\":9200,\n" +
                "        \"requestUrl\":\"/weather/current/minutely?lon=127.047553&village=&county=&stnid=&lat=37.613033&version=1&city=\",\n" +
                "        \"message\":\"성공\"\n" +
                "    }\n" +
                "}";

        Log.d("JSON", jsonStr);

        try {
            JSONObject json = new JSONObject(jsonStr);
//            1. 날씨 보여주는 동네 이름 추출 : String 변수 저장
            JSONObject weather = json.getJSONObject("weather");
            JSONArray minutely = weather.getJSONArray("minutely");
            JSONObject firstObj = minutely.getJSONObject(0);
            JSONObject station = firstObj.getJSONObject("station");
            String name = String.format(Locale.KOREA, "%s구", station.getString("name"));
//            Toast.makeText(mContext, name + "", Toast.LENGTH_SHORT).show();
            locationTxt.setText(name);

            JSONObject sky = firstObj.getJSONObject("sky");
            weatherTxt.setText(sky.getString("name"));

            JSONObject temperature = firstObj.getJSONObject("temperature");
            String tc = String.format(Locale.KOREA, "%s 도", temperature.getString("tc"));
            temperatureTxt.setText(tc);
            String tmax = String.format(Locale.KOREA, "%s 도", temperature.getString("tmax"));
            maxTemperatureTxt.setText(tmax);
            String tmin = String.format(Locale.KOREA, "%s 도", temperature.getString("tmin"));
            minTemperatureTxt.setText(tmin);

            JSONObject wind = firstObj.getJSONObject("wind");
            windTxt.setText(wind.getString("wdir"));
            windSpeedTxt.setText(wind.getString("wspd"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
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
    }
}

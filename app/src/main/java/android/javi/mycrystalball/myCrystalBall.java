package android.javi.mycrystalball;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.FloatMath;
import android.widget.TextView;
import android.widget.Toast;


public class myCrystalBall extends Activity {

    private TextView answerText;

    private SensorManager sensorManager;
    private Sensor accelerometer;

   private final SensorEventListener sensorListener = new MySensorListener(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_crystal_ball);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);



        answerText = (TextView) findViewById(R.id.answerText);
        answerText.setText(Predictions.get().getPrediction());
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorListener, accelerometer,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorListener);
    }
}

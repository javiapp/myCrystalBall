package android.javi.mycrystalball;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.FloatMath;
import android.widget.Toast;

public class MySensorListener implements SensorEventListener {
    private float acceleration;
    private float currentAcceleration;
    private float previousAcceleration;

    private Activity activity;

    public MySensorListener(Activity activity) {
        acceleration = 0.0f;
        currentAcceleration = SensorManager.GRAVITY_EARTH;
        previousAcceleration = SensorManager.GRAVITY_EARTH;
        this.activity = activity;
    }

    @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            previousAcceleration = currentAcceleration;
            currentAcceleration = FloatMath.sqrt(x * x + y * y + z * z);
            float delta = currentAcceleration - previousAcceleration;
            acceleration = acceleration * 0.9f + delta;

            if(acceleration > 15){
                Toast toast = Toast.makeText(activity.getApplicationContext(), "Device has Shaken", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
}

package cl.ist.proyectoapp3.;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Mqtt extends AppCompatActivity {

    private MqttAndroidClient client;
    private final MemoryPersistence persistence = new MemoryPersistence();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //++++++++++++++++++++++++++++++++++++++++++++
        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), "tcp://192.168.100.133:1883", clientId);

        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Toast.makeText(Mqtt.this, "Conectado", Toast.LENGTH_SHORT).show();
                    sub();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Toast.makeText(Mqtt.this, "Desconectado", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                txtsub.setText("Mensaje recivido: " + new String(message.getPayload()));
            }


            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }

    public void pub(View view){
        String topic = "ejemplo";
        //char* msg = txtpub.getText();
        try {
            client.publish(topic, txtpub.getText().toString().getBytes(), 0, false);
            txtpub.setText("");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void sub(){
        try {
            client.subscribe("ejemplo1", 0);
        }catch (MqttException e){
            e.printStackTrace();
        }
    }
}

package ar.com.sistema.httpconexion2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Handler.Callback {
    TextView textView;
    ImageView img;
    public static  final  int MENSAJE_STRING =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         textView = findViewById(R.id.tv);
         img = findViewById(R.id.img);

        Handler handler = new Handler(this);

        HiloConexion hilo = new HiloConexion(handler,true);
        hilo.start();

        HiloConexion hiloImg = new HiloConexion(handler,false);
        hiloImg.start();
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {
        if(message.arg1==MENSAJE_STRING){
            textView.setText(message.obj.toString());
        }else if(message.arg1==3){
            byte[] img = (byte[]) message.obj;
            Log.d("img tamño",""+img.length);
            this.img.setImageBitmap(BitmapFactory.decodeByteArray(img,0,img.length));

        }
        return false;
    }
}
package ar.com.sistema.httpconexion2022;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import java.nio.charset.StandardCharsets;

class HiloConexion extends Thread {
   Handler colaMensajes;
   boolean texto;
   public HiloConexion(Handler colaMensajes,boolean texto){
      this.colaMensajes = colaMensajes;
      this.texto=texto;
   }

   public void run(){
      ConexionHttp con = new ConexionHttp();
      if(this.texto) {
         byte[] respueta = con.obtenerInformacion("https://www.clarin.com/rss/lo-ultimo/");
         String respuetaS = new String(respueta);
         Log.d("respueta",respuetaS);

         Message message = new Message();
         message.arg1 = MainActivity.MENSAJE_STRING;
         message.obj = respuetaS;
         this.colaMensajes.sendMessage(message);
      }else{

         byte[] respueta = con.obtenerInformacion("https://depor.com/resizer/PkYoB5NMAbTrT-mzXk8iyfwZ5fs=/580x330/smart/filters:format(jpeg):quality(75)/cloudfront-us-east-1.images.arcpublishing.com/elcomercio/GBQSRILFOBCX7KERNRKUTP55WM.jpg");

         //https://www.infobae.com/pf/resources/images/logo_infobae_naranja.svg?d=955"


         Message message = new Message();
         message.arg1 = 3;
         message.obj = respueta;
         this.colaMensajes.sendMessage(message);

      }

   }
}

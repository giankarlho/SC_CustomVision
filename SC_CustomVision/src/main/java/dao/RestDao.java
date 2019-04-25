package dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class RestDao {

    public String consultar(String url) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        JsonParser convert = new JsonParser();
        String fruta;
        try {
            StringEntity body = new StringEntity("{\"Url\": \"" + url + "\"}");

            HttpPost request = new HttpPost("https://australiaeast.api.cognitive.microsoft.com/customvision/v3.0/Prediction/934ce50c-0b84-4f0d-bd96-19cd0b7134ec/classify/iterations/Prueba1_iteration/url");
            request.addHeader("Prediction-Key", "015e97d30890492482ce889479736023");
            request.addHeader("Content-Type", "application/json");
            request.setEntity(body);
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            JsonObject json = convert.parse(EntityUtils.toString(entity)).getAsJsonObject();

            JsonArray predicciones = json.getAsJsonArray("predictions");
            if (predicciones == null) {
                fruta = "Error";
            } else {
                fruta = convert.parse(predicciones.get(0).toString()).getAsJsonObject().get("tagName").toString().substring(1);
                fruta = fruta.substring(0, fruta.length() - 1);
            }
            return fruta;
        } catch (JsonSyntaxException e) {
            throw e;
        }
    }

}

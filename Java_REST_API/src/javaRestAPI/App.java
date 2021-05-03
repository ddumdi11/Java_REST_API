package javaRestAPI;

import java.net.URLEncoder;

import javax.swing.JOptionPane;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;


public class App {

	public static void main(String[] args) throws Exception {
		// Host url
		String host = "https://imdb8.p.rapidapi.com/title/find";
		String charset = "UTF-8";
		// Headers for a request
		String x_rapidapi_host = "imdb8.p.rapidapi.com";
		String x_rapidapi_key = "8995eeaffemshf418b001fb71822p1a5542jsn6785e213a9bc"; // Type here your key
		// Params		
		String q = JOptionPane.showInputDialog("Nach welchem Stichwort soll ich in der Filmdatenbank suchen?");
		// Format query for preventing encoding problems
		String query = String.format("q=%s", URLEncoder.encode(q, charset));

		HttpResponse<JsonNode> response = Unirest.get(host + "?" + query).header("x-rapidapi-host", x_rapidapi_host)
				.header("x-rapidapi-key", x_rapidapi_key).asJson();
		System.out.println(response.getStatus());
		System.out.println(response.getHeaders().get("Content-Type"));
	}
}

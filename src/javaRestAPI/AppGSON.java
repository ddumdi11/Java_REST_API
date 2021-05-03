package javaRestAPI;

import java.awt.Frame;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class AppGSON {
	
	private static void showLongTextMessageInDialog(String longMessage, Frame frame) {
	    SwingUtilities.invokeLater(() -> {
	        JTextArea textArea = new JTextArea(22, 111);
	        textArea.setText(longMessage);
	        textArea.setEditable(false);
	        JScrollPane scrollPane = new JScrollPane(textArea);
	        JOptionPane.showMessageDialog(frame, scrollPane);
	    });
	}

	public static void main(String[] args) throws UnsupportedEncodingException, UnirestException {
		// Host url
		String host = "https://imdb8.p.rapidapi.com/title/find";
		String charset = "UTF-8";
		// Headers for a request
		String x_rapidapi_host = "imdb8.p.rapidapi.com";
		String x_rapidapi_key = "8995eeaffemshf418b001fb71822p1a5542jsn6785e213a9bc"; // Type here your key
		// Params
		String q = JOptionPane.showInputDialog("Nach welchem Stichwort soll ich in der Filmdatenbank suchen?");
		// Host, charset and headers vars should be the same
		// Format query for preventing encoding problems
		String query = String.format("q=%s", URLEncoder.encode(q, charset));		
		// Json response
		HttpResponse<JsonNode> response = Unirest.get(host + "?" + query).header("x-rapidapi-host", x_rapidapi_host)
				.header("x-rapidapi-key", x_rapidapi_key).asJson();
		// Prettifying
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(response.getBody().toString());
		String prettyJsonString = gson.toJson(je);
		//JOptionPane.showMessageDialog(null, prettyJsonString);
		showLongTextMessageInDialog(prettyJsonString,null);
		//System.out.println(prettyJsonString);

	}

}

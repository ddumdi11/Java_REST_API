package javaRestAPI;

import java.io.InputStream;
import java.net.URLEncoder;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class AppMemeGenerator {

	public static void main(String[] args) throws UnirestException, IOException {
		// Host url
	      String host = "https://ronreiter-meme-generator.p.rapidapi.com/meme";
	      String charset = "UTF-8";
	      // Headers for a request
	      String x_rapidapi_host = "ronreiter-meme-generator.p.rapidapi.com";
	      String x_rapidapi_key = "YOUR_RAPIDAPI_KEY";
	      // Params
	      String meme = "Chuck-Norris-Approves";
	      String top = "We made this meme with Chuck";
	      String bottom = "Although we weren't even connected to the internet";

	      String query = String.format("meme=%s&top=%s&bottom=%s",
	       URLEncoder.encode(meme, charset),
	       URLEncoder.encode(top, charset),
	       URLEncoder.encode(bottom, charset));

	      // Meme generator
	      HttpResponse httpResponse = Unirest.get(host + "?" + query)
	      .header("x-rapidapi-host", x_rapidapi_host)
	      .header("x-rapidapi-key", x_rapidapi_key)
	      .asBinary();
	  //Image saving
	      InputStream is = httpResponse.getRawBody();
	      BufferedImage inputStreamImage = ImageIO.read(is);
	      File image = new File("image.jpg");
	      ImageIO.write(inputStreamImage, "jpg", image);
	      System.out.println( httpResponse.getHeaders()
	.get("Content-Type"));

	}

}

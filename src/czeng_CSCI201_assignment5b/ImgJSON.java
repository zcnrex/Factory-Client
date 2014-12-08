package czeng_CSCI201_assignment5b;

//import der;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ImgJSON {
	private String urlString = "";
	
	public ImgJSON(String name){
		try {
			String ss = getText("https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=" + name);

			JSONObject json = new JSONObject(ss);
			JSONObject responseData = (JSONObject)json.get("responseData");

			JSONArray result  = responseData.getJSONArray("results");
				JSONObject url = (JSONObject) result.get(0);
				urlString = (String) url.get("url");
//				System.out.println(url.get("url"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static String getText(String url) throws Exception {
      URL website = new URL(url);
      URLConnection connection = website.openConnection();
      BufferedReader in = new BufferedReader(
                              new InputStreamReader(
                                  connection.getInputStream()));

      StringBuilder response = new StringBuilder();
      String inputLine;

      while ((inputLine = in.readLine()) != null) 
          response.append(inputLine);

      in.close();

      return response.toString();
  }
	
	public String getURL(){
		return urlString;
	}
	
}

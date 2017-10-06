package com.jbd.authorization;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

@Stateless
public class FBGraph {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(FBGraph.class);
    private static final Marker MARKER = MarkerFactory.getMarker("SearchEmailsServlet");
    @Inject
    LoginFBServlet loginFBServlet;

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    private String accessToken;


    public FBGraph(String accessToken) {
        this.accessToken = accessToken;
    }

    public FBGraph() {
    }

    ;

    public String getFBGraph() {
        String graph = null;
        try {

            String g = "https://graph.facebook.com/v2.8/me?fields=id,first_name,last_name,email&" + accessToken;
            URL u = new URL(g);
            URLConnection c = u.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    c.getInputStream()));
            String inputLine;
            StringBuffer b = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                b.append(inputLine + "\n");
            }
            in.close();
            graph = b.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("ERROR in getting FB graph data. " + e);

        }
        return graph;
    }

    public Map<String, String> getGraphData(String fbGraph) {
        Map<String, String> fbProfile = new HashMap<String, String>();
        try {
            JSONObject json = new JSONObject(fbGraph);
            LOGGER.debug(MARKER, "JSON created " + json.toString());
            fbProfile.put("id", json.getString("id"));
            fbProfile.put("first_name", json.getString("first_name"));
            fbProfile.put("last_name", json.getString("last_name"));
            if (json.has("email"))
                fbProfile.put("email", json.getString("email"));
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException("ERROR in parsing FB graph data. " + e);
        }
        return fbProfile;
    }
}

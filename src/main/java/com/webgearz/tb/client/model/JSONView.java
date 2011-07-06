package com.webgearz.tb.client.model;

import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.View;
/**
 * Courtesy James Kingsbery - one of the best coders Ive seen
 * @author admin
 *
 */
public class JSONView implements View{

	private static final String DEFAULT_JSON_CONTENT_TYPE = "application/json";
	private static final Log LOG = LogFactory.getLog(JSONView.class);
    private JSONObject jsonObject;

    private int statusCode = HttpURLConnection.HTTP_OK;

	@Override
	public String getContentType() {
		
		return DEFAULT_JSON_CONTENT_TYPE;
	}

	
	
	
	public JSONView(JSONObject jsonObject) {
        super();
        this.jsonObject = jsonObject;
    }

    public JSONView() {
        super();
        this.jsonObject = null;
    }

    public JSONView(int statusCode) {
        this.statusCode = statusCode;
    }
    
  
    @Override
    public void render(Map model, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (this.jsonObject == null) {
            this.jsonObject = fromMap(model);
        }

        response.setStatus(statusCode);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        assert this.jsonObject !=null;
        out.write(this.jsonObject.toString());

    }

    public static JSONArray fromList(List list) {
        JSONArray result = new JSONArray();
        for (Object x : list) {
            result.put(getJsonValue(x));
        }
        return result;
    }

    public static JSONObject fromMap(Map<String, ?> model) {
        JSONObject jsonObject = new JSONObject();

        try {
            Iterator<String> ite = model.keySet().iterator();
            while (ite.hasNext()) {
                String key = ite.next();
                Object value = model.get(key);
                jsonObject.put(key, getJsonValue(value));
            }
        } catch (Exception e) {
            LOG.error("call fromMap failed ");
        }

        return jsonObject;
    }

    private static Object getJsonValue(Object value) {
        Object jsonVal;
        if (value instanceof Map) {
            jsonVal = fromMap((Map<String, ?>) value);
        } else if (value instanceof List) {
            jsonVal = fromList((List) value);
        } else {
            jsonVal = value;
        }
        return jsonVal;
    }

}

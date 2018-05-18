package com.icehousecorp.testing.testrail;

/*
  TestRail API binding for Java (API v2, available since TestRail 3.0)
  <p>
  Learn more:
  <p>
  http://docs.gurock.com/testrail-api2/start
  http://docs.gurock.com/testrail-api2/accessing
  <p>
  Copyright Gurock Software GmbH. See license.md for details.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;

@SuppressWarnings("unused")
public class HTTPClient {
    private String m_user;
    private String m_password;
    private String m_url;

    public class APIException extends Exception {
        APIException(String message) {
            super(message);
        }
    }

    HTTPClient(String base_url) {
        if (!base_url.endsWith("/")) {
            base_url += "/";
        }

        this.m_url = base_url + "index.php?/api/v2/";
    }

    void setUser(String user) {
        this.m_user = user;
    }

    void setPassword(String password) {
        this.m_password = password;
    }

    /**
     * Send Get
     * <p>
     * Issues a GET request (read) against the API and returns the result
     * (as Object, see below).
     * <p>
     * Arguments:
     * <p>
     * uri                  The API method to call including parameters
     * (e.g. get_case/1)
     * <p>
     * Returns the parsed JSON response as standard object which can
     * either be an instance of JSONObject or JSONArray (depending on the
     * API method). In most cases, this returns a JSONObject instance which
     * is basically the same as java.util.Map.
     */
    public String sendGet(String uri)
            throws IOException, APIException {
        return this.sendRequest("GET", uri, null);
    }

    /**
     * Send POST
     * <p>
     * Issues a POST request (write) against the API and returns the result
     * (as Object, see below).
     * <p>
     * Arguments:
     * <p>
     * uri                  The API method to call including parameters
     * (e.g. add_case/1)
     * data                 The data to submit as part of the request (e.g.,
     * a map)
     * <p>
     * Returns the parsed JSON response as standard object which can
     * either be an instance of JSONObject or JSONArray (depending on the
     * API method). In most cases, this returns a JSONObject instance which
     * is basically the same as java.util.Map.
     */
    String sendPost(String uri, Object data)
            throws IOException, APIException {
        return this.sendRequest("POST", uri, data);
    }

    private String sendRequest(String method, String uri, Object data)
            throws IOException, APIException {
        URL url = new URL(this.m_url + uri);

        // Create the connection object and set the required HTTP method
        // (GET/POST) and headers (content type and basic auth).
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.addRequestProperty("Content-Type", "application/json");

        String auth = getAuthorization(this.m_user, this.m_password);
        conn.addRequestProperty("Authorization", "Basic " + auth);

        if (method.equals("POST")) {
            // Add the POST arguments, if any. We just serialize the passed
            // data object (i.e. a dictionary) and then add it to the
            // request body.
            if (data != null) {
                byte[] block = JSON.toJSONString(data).
                        getBytes("UTF-8");

                conn.setDoOutput(true);
                OutputStream ostream = conn.getOutputStream();
                ostream.write(block);
                ostream.flush();
            }
        }

        // Execute the actual web request (if it wasn't already initiated
        // by getOutputStream above) and record any occurred errors (we use
        // the error stream in this case).
        int status = conn.getResponseCode();

        InputStream istream;
        if (status != 200) {
            istream = conn.getErrorStream();
            if (istream == null) {
                throw new APIException(
                        "TestRail API return HTTP " + status +
                                " (No additional error message received)"
                );
            }
        } else {
            istream = conn.getInputStream();
        }

        // Read the response body, if any, and deserialize it from JSON.
        StringBuilder text = new StringBuilder();
        if (istream != null) {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            istream,
                            "UTF-8"
                    )
            );

            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line);
                text.append(System.getProperty("line.separator"));
            }

            reader.close();
        }

        JSONObject result;
        if (!text.toString().equals("")) {
            result = JSON.parseObject(text.toString());
        } else {
            result = new JSONObject();
        }

        // Check for any occurred errors and add additional details to
        // the exception message, if any (e.g. the error message returned
        // by TestRail).
        if (status != 200) {
            String error = "No additional error message received";
            if (result.containsKey("error")) {
                error = '"' + (String) result.get("error") + '"';
            }

            throw new APIException("TestRail API returned HTTP " + status + "(" + error + ")");
        }

        return text.toString();
    }

    private static String getAuthorization(String user, String password) {
        try {
            return getBase64((user + ":" + password).getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // Not thrown
        }

        return "";
    }

    private static String getBase64(byte[] buffer) {
        final char[] map = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
                'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', '+', '/'
        };

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buffer.length; i++) {
            byte b0 = buffer[i++], b1 = 0, b2 = 0;

            int bytes = 3;
            if (i < buffer.length) {
                b1 = buffer[i++];
                if (i < buffer.length) {
                    b2 = buffer[i];
                } else {
                    bytes = 2;
                }
            } else {
                bytes = 1;
            }

            int total = (b0 << 16) | (b1 << 8) | b2;

            switch (bytes) {
                case 3:
                    sb.append(map[(total >> 18) & 0x3f]);
                    sb.append(map[(total >> 12) & 0x3f]);
                    sb.append(map[(total >> 6) & 0x3f]);
                    sb.append(map[total & 0x3f]);
                    break;

                case 2:
                    sb.append(map[(total >> 18) & 0x3f]);
                    sb.append(map[(total >> 12) & 0x3f]);
                    sb.append(map[(total >> 6) & 0x3f]);
                    sb.append('=');
                    break;

                case 1:
                    sb.append(map[(total >> 18) & 0x3f]);
                    sb.append(map[(total >> 12) & 0x3f]);
                    sb.append('=');
                    sb.append('=');
                    break;
            }
        }

        return sb.toString();
    }
}


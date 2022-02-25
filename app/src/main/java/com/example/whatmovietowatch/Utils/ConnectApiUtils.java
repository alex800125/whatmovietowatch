package com.example.whatmovietowatch.Utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectApiUtils {

    private final static String TAG = "ConnectUtils";

    public static String getMovieFromApi(final String uri) {
        StringBuilder string = new StringBuilder();
        BufferedReader buffer = null;

        Log.i(TAG, "Uri: " + uri);

        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String linha;
            while ((linha = buffer.readLine()) != null) {
                string.append(linha + "\n");
            }

        } catch (Exception e) {
            Log.e(TAG, "Exception e = " + e);
        } finally {
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (Exception e) {
                    Log.e(TAG, "Exception e = " + e);
                }
            }
        }

        return string.toString();
    }
}

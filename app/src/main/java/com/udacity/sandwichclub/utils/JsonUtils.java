/*
 * PROJECT LICENSE
 *
 * This project was submitted by Brandon Ingram as part of the Android Developer
 * Nanodegree Program at Udacity.
 *
 * As part of Udacity Honor code, your submissions must be your own work, hence
 * submitting this project as yours will cause you to break the Udacity Honor Code
 * and the suspension of your account.
 *
 * Me, the author of the project, allow you to check the code as a reference, but if
 * you submit it, it's your own responsibility if you get expelled.
 *
 * Copyright (c) 2018 Brandon Ingram
 *
 * Besides the above notice, the following license applies and this license notice
 * must be included in all works derived from this project.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String NAME_KEY = "name";
    private static final String MAIN_NAME_KEY = "mainName";
    private static final String ALSO_KNOWN_AS_KEY = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN_KEY = "placeOfOrigin";
    private static final String INGREDIENTS_KEY = "ingredients";
    private static final String DESCRIPTION_KEY = "description";
    private static final String IMAGE_URL_KEY = "image";

    public static Sandwich parseSandwichJson(String json) {
        try {
            // Grab references to all JSON object we will need
            JSONObject sandwichJsonObject = new JSONObject(json);
            JSONObject nameJsonObject = sandwichJsonObject.getJSONObject(NAME_KEY);
            JSONArray alsoKnownAsJsonArray = nameJsonObject.getJSONArray(ALSO_KNOWN_AS_KEY);
            JSONArray ingredientsJsonArray = sandwichJsonObject.getJSONArray(INGREDIENTS_KEY);

            // Retrieve the fields from the JSON object so we can construct the Sandwich object
            String mainName = nameJsonObject.getString(MAIN_NAME_KEY);
            List<String> alsoKnownAs = convertJsonArrayToStringList(alsoKnownAsJsonArray);
            String placeOfOrigin = sandwichJsonObject.getString(PLACE_OF_ORIGIN_KEY);
            String description = sandwichJsonObject.getString(DESCRIPTION_KEY);
            String image = sandwichJsonObject.getString(IMAGE_URL_KEY);
            List<String> ingredients = convertJsonArrayToStringList(ingredientsJsonArray);

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        } catch (JSONException e) {
            Log.w(JsonUtils.class.getName(), "Encountered exception parsing JSON: " + e.getMessage(), e);
        }

        return null;
    }

    private static List<String> convertJsonArrayToStringList(JSONArray jsonArray)
            throws JSONException {
        if (jsonArray != null) {
            ArrayList<String> stringList = new ArrayList<>();
            int arrayLength = jsonArray.length();
            for (int i = 0; i < arrayLength; i++) {
                stringList.add(jsonArray.getString(i));
            }

            return stringList;
        }

        return null;
    }
}

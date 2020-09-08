package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static final String NAME = "name";
    public static final String MAIN_NAME = "mainName";
    public static final String ALSO_KNOWN_AS = "alsoKnownAs";
    public static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE = "image";
    public static final String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = new Sandwich();

        ArrayList<String> mAlsoKnownAs = new ArrayList<>();
        ArrayList<String> mIngredients = new ArrayList<>();

        if (json != null) {
            try{
                JSONObject rootJsonObject = new JSONObject(json);
                JSONObject nameJSONObject = rootJsonObject.getJSONObject(NAME);

                String mainName = nameJSONObject.optString(MAIN_NAME);

                JSONArray otherName = nameJSONObject.getJSONArray(ALSO_KNOWN_AS);
                for (int i = 0; i < otherName.length(); i++){
                    mAlsoKnownAs.add(otherName.optString(i));
                }

                String placeOfOrigin = rootJsonObject.optString(PLACE_OF_ORIGIN);
                String description = rootJsonObject.optString(DESCRIPTION);
                String imageUrl = rootJsonObject.optString(IMAGE);

                JSONArray ingredientsJSONArray = rootJsonObject.getJSONArray(INGREDIENTS);
                for (int i = 0; i < ingredientsJSONArray.length(); i++){
                    mIngredients.add(ingredientsJSONArray.optString(i));
                }

                sandwich.setMainName(mainName);
                sandwich.setAlsoKnownAs(mAlsoKnownAs);
                sandwich.setPlaceOfOrigin(placeOfOrigin);
                sandwich.setDescription(description);
                sandwich.setImage(imageUrl);
                sandwich.setIngredients(mIngredients);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return sandwich;
    }
}

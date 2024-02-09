package com.loujin.croakcode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.text.Html;

import android.text.Spanned;
import androidx.core.text.HtmlCompat;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    ImageView imageView;
    TextView resultTextView, confidenceTextView, descriptionTextView;

    // Descriptions
    private static final String bandedBullfrogDescription = "<strong>The banded bullfrog</strong> (Kaloula pulchra) is a species of frog in the narrow-mouthed frog family Microhylidae. Native to Southeast Asia, it is also known as the Asian painted frog, digging frog, Malaysian bullfrog, common Asian frog, and painted balloon frog. Adults measure 5.4 to 7.5 cm (2.1 to 3.0 in) and have a dark brown back with stripes that vary from copper-brown to salmon pink. \n\n    The banded bullfrog lives at low altitudes and is found in both urban and rural settings, as well as in forest habitats. They bury themselves underground during dry periods and emerge after heavy rainfall to emit calls and breed. They feed primarily on ants and termites; predators of adults and tadpoles include snakes, dragonfly larvae, and snails. When threatened, they inflate their lungs and secrete a noxious white substance.\n\n    The species is prevalent in the pet trade and is a potential invasive species being introduced in Taiwan, the Philippines, Guam, Singapore, Borneo, and Sulawesi.";

    private static final String philippineToadDescription = "<strong>The Philippine toad</strong> (Ingerophrynus philippinicus) is a species of toad in the family Bufonidae. It is endemic to the Philippines. Its natural habitats are subtropical or tropical dry forest, subtropical or tropical moist lowland forest, subtropical or tropical swamps, subtropical or tropical moist montane forest, subtropical or tropical moist shrubland, intermittent rivers, swamps, freshwater lakes, intermittent freshwater lakes, freshwater marshes, intermittent freshwater marshes, intertidal marches, arable land, plantations, rural gardens, urban areas, water storage areas, ponds, aquaculture ponds, and seasonally flooded agricultural land.";

    private static final String eastAsianBullfrogDescription = "<strong>The Chinese edible frog</strong>, East Asian bullfrog, or Taiwanese frog (Hoplobatrachus rugulosus) is a species of frog in the family Dicroglossidae. It is found in Cambodia, China, Hong Kong, Laos, Macau, Malaysia, Myanmar, the Philippines, Taiwan, Thailand, and Vietnam. Its natural habitats are freshwater marshes, intermittent freshwater marshes, arable land, pasture land, rural gardens, urban areas, ponds, aquaculture ponds, open excavations, irrigated land, seasonally flooded agricultural land, and canals and ditches. \n\n    They breed in spring to early summer. The domesticated Thai variety and wild Chinese populations of H. rugulosus belong to two separate genetic lineages respectively. Yu et al. (2015) suggest that H. rugulosus may in fact be a cryptic species complex.";

    private static final String genericFrogDescription = "<strong>Description for generic frog goes here.</strong>";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        imageView = findViewById(R.id.imageView);
        resultTextView = findViewById(R.id.result);
        descriptionTextView = findViewById(R.id.descriptionText);

        // Get data from intent
        Bitmap image = getIntent().getParcelableExtra("image");
        String result = getIntent().getStringExtra("result");

        // Set image to imageView
        if (image != null) {
            imageView.setImageBitmap(image);
        }

        // Set result and confidences to text views
        if (result != null) {
            resultTextView.setText("Result: " + result);
        }

        // Set description based on result
        String description = String.valueOf(getDescription(result));
        descriptionTextView.setText(description);

        // Set click listener for the back button
        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Go back when the back button is clicked
            }
        });
    }

    // Method to get the description based on the classified result
    private Spanned getDescription(String result) {
        switch (result) {
            case "Banded Bull Frog":
                return fromHtmlCompat(bandedBullfrogDescription);
            case "Philippine Toad":
                return fromHtmlCompat(philippineToadDescription);
            case "East Asian Bull Frog":
                return fromHtmlCompat(eastAsianBullfrogDescription);
            default:
                return fromHtmlCompat(genericFrogDescription);
        }
    }

    // Helper method to create Spanned object using HtmlCompat
    private Spanned fromHtmlCompat(String htmlString) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return HtmlCompat.fromHtml(htmlString, HtmlCompat.FROM_HTML_MODE_LEGACY);
        } else {
            //noinspection deprecation
            return Html.fromHtml(htmlString);
        }
    }

}

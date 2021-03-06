package pl.fream.evryplace.androidmodule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by lynx on 03/03/18.
 */

public class JokeActivity extends AppCompatActivity {
    public static final String EXTRA_JOKE = "EXTRA_JOKE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        String extraJoke = getIntent().getStringExtra(EXTRA_JOKE);
        TextView jokeTextView = findViewById(R.id.activity_joke_text);
        jokeTextView.setText(extraJoke);
    }

}

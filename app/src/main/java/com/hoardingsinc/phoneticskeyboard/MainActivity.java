package com.hoardingsinc.phoneticskeyboard;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView presentation = (TextView) findViewById(R.id.presentation);
        presentation.setText(Html.fromHtml(getString(R.string.presentation)));
        presentation.setMovementMethod(LinkMovementMethod.getInstance());
    }

}

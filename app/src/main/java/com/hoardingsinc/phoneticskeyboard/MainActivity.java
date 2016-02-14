package com.hoardingsinc.phoneticskeyboard;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView presentation = (TextView) findViewById(R.id.presentation);
        presentation.setText(Html.fromHtml(getString(R.string.presentation)));
        presentation.setMovementMethod(LinkMovementMethod.getInstance());

        TextView pronunroidAdd = (TextView) findViewById(R.id.pronunroid_ad);
        pronunroidAdd.setText(Html.fromHtml(getString(R.string.pronunroid_ad)));
        pronunroidAdd.setMovementMethod(LinkMovementMethod.getInstance());

        KeyboardPreferences keyboardPreferences = new KeyboardPreferences(this);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group_layout);
        switch (keyboardPreferences.getLayout()) {
            case KeyboardPreferences.LAYOUT_NORMAL:
                radioGroup.check(R.id.radio_normal_layout);
                break;
            case KeyboardPreferences.LAYOUT_EXTENDED:
                radioGroup.check(R.id.radio_extended_layout);
                break;
        }

        Button b = (Button) findViewById(R.id.button_pronunroid_ad);
        b.setOnClickListener(this);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        if (!checked) {
            return;
        }

        KeyboardPreferences keyboardPreferences = new KeyboardPreferences(this);

        switch (view.getId()) {
            case R.id.radio_normal_layout:
                keyboardPreferences.saveLayout(KeyboardPreferences.LAYOUT_NORMAL);
                break;
            case R.id.radio_extended_layout:
                keyboardPreferences.saveLayout(KeyboardPreferences.LAYOUT_EXTENDED);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        // There's only one button: Pronunroid ad
        startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(
                "https://play.google.com/store/apps/details?id=com.hoardingsinc.pronunroid")));
    }
}

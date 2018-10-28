package com.hoardingsinc.phoneticskeyboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

public class PhoneticsKeyboard extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener {

    private KeyboardView kv;
    private Keyboard keyboard;

    private boolean caps = false;

    @Override
    public View onCreateInputView() {
        kv = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, keyboardLayoutVersion());
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);

        return kv;
    }

    private int keyboardLayoutVersion() {
        KeyboardPreferences keyboardPreferences = new KeyboardPreferences(this);
        switch (keyboardPreferences.getLayout()) {
            case KeyboardPreferences.LAYOUT_NORMAL:
                return R.xml.phonetics_normal;
            case KeyboardPreferences.LAYOUT_EXTENDED:
                return R.xml.phonetics_extended;
            case KeyboardPreferences.LAYOUT_EXTENDED_2:
                return R.xml.phonetics_extended_2;
            case KeyboardPreferences.LAYOUT_COMPACT:
                return R.xml.phonetics_compact;
            default:
                return R.xml.phonetics_normal;
        }
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();
        switch(primaryCode){
            case Keyboard.KEYCODE_MODE_CHANGE:
                InputMethodManager inputManager = (InputMethodManager) getSystemService(
                        INPUT_METHOD_SERVICE);
                inputManager.showInputMethodPicker();
                break;
            case Keyboard.KEYCODE_DELETE :
                ic.deleteSurroundingText(1, 0);
                break;
            case Keyboard.KEYCODE_SHIFT:
                caps = !caps;
                keyboard.setShifted(caps);
                kv.invalidateAllKeys();
                break;
            case Keyboard.KEYCODE_DONE:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            default:
                char code = (char)primaryCode;
                if(Character.isLetter(code) && caps){
                    code = Character.toUpperCase(code);
                }
                ic.commitText(String.valueOf(code),1);
        }
    }

    @Override
    public void onPress(int primaryCode) {
    }

    @Override
    public void onRelease(int primaryCode) {
    }

    @Override
    public void onText(CharSequence text) {
        getCurrentInputConnection().commitText(text, 1);
    }

    @Override
    public void swipeDown() {
    }

    @Override
    public void swipeLeft() {
    }

    @Override
    public void swipeRight() {
    }

    @Override
    public void swipeUp() {
    }

    @Override
    public void onStartInputView(EditorInfo info, boolean restarting) {
        super.onStartInputView(info, restarting);

        setInputView(onCreateInputView());
    }

}

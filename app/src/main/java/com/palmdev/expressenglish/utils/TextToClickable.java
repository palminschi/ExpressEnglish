package com.palmdev.expressenglish.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.palmdev.expressenglish.R;

import java.text.BreakIterator;
import java.util.Locale;

public class TextToClickable {

    public static void initSelectableWord(CharSequence content, TextView textView) {
        //First - trim the text and remove the spaces at start and end.
        String textContent = content.toString().trim();

        //Set the text as SPANNABLE text.
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(textContent, TextView.BufferType.SPANNABLE);

        //After we get the spans of the text with iterator and we initialized the iterator
        Spannable spans = (Spannable) textView.getText();
        BreakIterator iterator = BreakIterator.getWordInstance(Locale.US); // <- Input text language
        iterator.setText(textContent);
        int start = iterator.first();

        //Here we get all possible words by iterators
        for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
            String possibleWord = textContent.substring(start, end);
            if (Character.isLetterOrDigit(possibleWord.charAt(0))) {
                ClickableSpan clickSpan = getClickableSpan(possibleWord);
                spans.setSpan(clickSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }

    public static void setCoordinate(int x, int y){
        xCoordinate = x;
        yCoordinate = y;
    }
    public static int xCoordinate = 0;
    public static int yCoordinate = 0;

    private static ClickableSpan getClickableSpan(final String word) {
        return new ClickableSpan() {
            final String mWord;
            {
                mWord = word;
            }

            @Override // Create Popup Window
            public void onClick(View widget) {
                // inflate the layout of the popup window
                LayoutInflater inflater = (LayoutInflater)widget
                        .getContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                @SuppressLint("InflateParams")
                View popupView = inflater.inflate(R.layout.popup_translate_word,null);

                // create the popup window
                PopupWindow popup = new PopupWindow(
                        popupView,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        true
                        );
                TextView word = popup.getContentView().findViewById(R.id.popupText);
                TextView translatedWord = popup.getContentView().findViewById(R.id.popupTranslatedText);
                TextView btnClose = popup.getContentView().findViewById(R.id.btnClose);
                TextView btnSave = popup.getContentView().findViewById(R.id.btnSave);
                ImageView btnSound = popup.getContentView().findViewById(R.id.btnSound);
                word.setText(mWord);
                btnClose.setOnClickListener(v -> {
                    popup.dismiss();
                });
                btnSound.setOnClickListener(v -> {
                    TextToSpeech.Companion.play( mWord, widget.getContext());
                });
                popup.showAtLocation(widget, Gravity.NO_GRAVITY, xCoordinate, yCoordinate - 160);
            }

        };
    }
}


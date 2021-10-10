package com.palmdev.expressenglish.utils;

import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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


    private static ClickableSpan getClickableSpan(final String word) {
        return new ClickableSpan() {
            final String mWord;
            {
                mWord = word;
            }

            @Override
            public void onClick(View widget) {
                Log.d("Clicked word is : ", mWord);
                Toast.makeText(widget.getContext(), mWord, Toast.LENGTH_SHORT).show();
            }

        };
    }
}


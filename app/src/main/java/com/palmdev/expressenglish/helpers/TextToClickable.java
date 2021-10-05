package com.palmdev.expressenglish.helpers;

import android.text.Layout;
import android.text.Spannable;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TextToClickable {

    public static void initSelectableWord(CharSequence entireContent, TextView textView) {
        //First we trim the text and remove the spaces at start and end.
        String definition = entireContent.toString().trim();
        //And then  set the textview movement method to prevent frezing
        //And we set the text as SPANNABLE text.
        TextView definitionView = textView;
        definitionView.setMovementMethod(LinkMovementMethod.getInstance());
        definitionView.setText(definition, TextView.BufferType.SPANNABLE);
        //After we get the spans of the text with iterator and we initialized the iterator
        Spannable spans = (Spannable) definitionView.getText();
        BreakIterator iterator = BreakIterator.getWordInstance(Locale.US);
        iterator.setText(definition);
        int start = iterator.first();

        //Here we get all possible words by iterators
        for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
            String possibleWord = definition.substring(start, end);
            if (Character.isLetterOrDigit(possibleWord.charAt(0))) {
                ClickableSpan clickSpan = getClickableSpan(possibleWord);
                spans.setSpan(clickSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }

    public static class LinkMovementMethodOverride implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            //First we get the initizlized text view and its content
            TextView widget = (TextView) v;
            Object text = widget.getText();
            //And the control it if a Spanned.
            if (text instanceof Spanned) {
                //If it is a spanned text we get the text and motion event.
                //This will decide the movement direction: UP or DOWN.
                Spanned buffer = (Spanned) text;
                int action = event.getAction();
                if (action == MotionEvent.ACTION_UP
                        || action == MotionEvent.ACTION_DOWN) {
                    //We set the new position to show. We moved the lines.
                    int x = (int) event.getX();
                    int y = (int) event.getY();

                    x -= widget.getTotalPaddingLeft();
                    y -= widget.getTotalPaddingTop();

                    x += widget.getScrollX();
                    y += widget.getScrollY();

                    Layout layout = widget.getLayout();
                    int line = layout.getLineForVertical(y);
                    int off = layout.getOffsetForHorizontal(line, x);
                    //And we initiazlized the clickable spans again for all spanned words
                    ClickableSpan[] link = buffer.getSpans(off, off,
                            ClickableSpan.class);
                    // and assign onclick method of the ClickableSpan class for each word.
                    if (link.length != 0) {
                        if (action == MotionEvent.ACTION_UP) {
                            link[0].onClick(widget);
                        } else if (action == MotionEvent.ACTION_DOWN) {

                        }
                        return true;
                    }
                }
            }
            return false;
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
                //Here you can do whatever you want with this word.
                //I have made a translation with this and show my users.
            }

        };
    }
}


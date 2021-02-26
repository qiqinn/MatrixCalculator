/*
 * Copyright (C) 2017 Ashar Khan <ashar786khan@gmail.com>
 *
 * This file is part of Matrix Calculator.
 *
 * Matrix Calculator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Matrix Calculator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Matrix Calculator.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.softminds.matrixcalculator.base_activities;

import android.animation.ObjectAnimator;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.softminds.matrixcalculator.AdLoadListener;
import com.softminds.matrixcalculator.GlobalValues;
import com.softminds.matrixcalculator.R;

public class faqs extends AppCompatActivity implements View.OnTouchListener {

    boolean isDark;
    CardView adCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        isDark = preferences.getBoolean("DARK_THEME_KEY", false);
        if (isDark)
            setTheme(R.style.AppThemeDark);
        else
            setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_layout);

        adCard = findViewById(R.id.AddCardFAQ);
        if (!((GlobalValues) getApplication()).DonationKeyFound()) {
            AdView faqAd = findViewById(R.id.adViewFaq);
            AdRequest adRequest = new AdRequest.Builder().build();
            faqAd.setAdListener(new AdLoadListener(adCard));
            faqAd.loadAd(adRequest);

        } else {
            ((ViewGroup) adCard.getParent()).removeView(adCard);
            //remove cardView from main layout
        }

        if (isDark) {
            SetThisColorToCard(ContextCompat.getColor(this, R.color.DarkcolorPrimary));
            SetThisColorToAllQuestions(ContextCompat.getColor(this, R.color.DarkcolorAccent));
        } else {
            SetThisColorToAllQuestions(ContextCompat.getColor(this, R.color.colorAccent));
        }

    }

    private void SetThisColorToCard(int id) {
        //grab all 7 cards
        CardView cardView1 = findViewById(R.id.QA1);
        CardView cardView2 = findViewById(R.id.QA2);
        CardView cardView3 = findViewById(R.id.QA3);
        CardView cardView5 = findViewById(R.id.QA5);
        CardView cardView8 = findViewById(R.id.QA8);
        CardView cardView9 = findViewById(R.id.QA9);
        CardView cardView10 = findViewById(R.id.QA10);
        //set the background color
        cardView1.setCardBackgroundColor(id);
        cardView2.setCardBackgroundColor(id);
        cardView3.setCardBackgroundColor(id);
        cardView5.setCardBackgroundColor(id);
        cardView8.setCardBackgroundColor(id);
        cardView9.setCardBackgroundColor(id);
        cardView10.setCardBackgroundColor(id);
    }

    private void SetThisColorToAllQuestions(int id) {
        //grab all 10 TextViews
        TextView textView1 = findViewById(R.id.Q1);
        TextView textView2 = findViewById(R.id.Q2);
        TextView textView3 = findViewById(R.id.Q3);
        TextView textView5 = findViewById(R.id.Q5);
        TextView textView8 = findViewById(R.id.Q8);
        TextView textView9 = findViewById(R.id.Q9);
        TextView textView10 = findViewById(R.id.Q10);
        //set the background color
        textView1.setTextColor(id);
        textView2.setTextColor(id);
        textView3.setTextColor(id);
        textView5.setTextColor(id);
        textView8.setTextColor(id);
        textView9.setTextColor(id);
        textView10.setTextColor(id);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationZ", 20);
                animator.setDuration(200);
                animator.setInterpolator(new DecelerateInterpolator());
                animator.start();
                view.performClick();
                return true;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "translationZ", 0);
                animator2.setDuration(200);
                animator2.setInterpolator(new AccelerateInterpolator());
                animator2.start();
                return true;
            default:
                return false;
        }
    }
}

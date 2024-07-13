package com.example.openinghoursview;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import java.util.Calendar;

public class OpeningHoursView extends LinearLayout {

    private AppCompatTextView dayTV, openTV, closeTV, openTitle, closeTitle;
    private String dayString, openString, closeString;
    private int dayTextColor, openHoursTextColor, closeHoursTextColor;
    private float dayTextSize, openTextSize, closeTextSize, openTitleTextSize, closeTitleTextSize;


    public OpeningHoursView(Context context) {
        super(context);
        init(null);
    }

    public OpeningHoursView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public OpeningHoursView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.day_openning_hours_view, this);

        dayTV = findViewById(R.id.day);
        openTV = findViewById(R.id.open);
        closeTV = findViewById(R.id.close);
        openTitle = findViewById(R.id.openTitle);
        closeTitle = findViewById(R.id.closeTitle);

        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.OpeningHoursView,
                0, 0);

        try {
            dayString= typedArray.getString(R.styleable.OpeningHoursView_day);
            openString= typedArray.getString(R.styleable.OpeningHoursView_openHours);
            closeString= typedArray.getString(R.styleable.OpeningHoursView_closeHours);

            dayTextColor = typedArray.getColor(R.styleable.OpeningHoursView_dayTextColor, ContextCompat.getColor(getContext(), android.R.color.black));
            openHoursTextColor = typedArray.getColor(R.styleable.OpeningHoursView_openHoursTextColor, ContextCompat.getColor(getContext(), android.R.color.black));
            closeHoursTextColor = typedArray.getColor(R.styleable.OpeningHoursView_closeHoursTextColor, ContextCompat.getColor(getContext(), android.R.color.black));

            dayTextSize = typedArray.getDimension(R.styleable.OpeningHoursView_dayTextSize, dayTV.getTextSize());
            openTextSize = typedArray.getDimension(R.styleable.OpeningHoursView_openHoursTextSize, openTV.getTextSize());
            closeTextSize = typedArray.getDimension(R.styleable.OpeningHoursView_closeHoursTextSize, closeTV.getTextSize());
            openTitleTextSize = typedArray.getDimension(R.styleable.OpeningHoursView_openTitleTextSize, openTitle.getTextSize());
            closeTitleTextSize = typedArray.getDimension(R.styleable.OpeningHoursView_closeTitleTextSize, closeTitle.getTextSize());

            updateView();

        } finally {
            typedArray.recycle();
        }


        openTV.setOnClickListener(v-> openTimePicker((AppCompatTextView)v));
        closeTV.setOnClickListener(v-> openTimePicker((AppCompatTextView)v));
    }

    private void updateView() {
        dayTV.setText(dayString);
        setOpenHours(openString);
        setCloseHours(closeString);

        dayTV.setTextColor(dayTextColor);
        openTV.setTextColor(openHoursTextColor);
        closeTV.setTextColor(closeHoursTextColor);

        dayTV.setTextSize(TypedValue.COMPLEX_UNIT_PX, dayTextSize);
        openTV.setTextSize(TypedValue.COMPLEX_UNIT_PX, openTextSize);
        closeTV.setTextSize(TypedValue.COMPLEX_UNIT_PX, closeTextSize);
        openTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, openTitleTextSize);
        closeTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, closeTitleTextSize);

    }

    private void openTimePicker(AppCompatTextView v) {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                if (min < 10) {
                    v.setText(hour + ":0" + min);
                } else {
                    v.setText(hour + ":" + min);
                }
            }
        }, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }

    public void setDayTV(String dayTV) {
        this.dayTV.setText(dayTV + ":");
    }

    public void setOpenHours(String text) {
        if (text == null || text.isEmpty()) {
            this.openTV.setText(R.string.click_to_add);
        } else {
            this.openTV.setText(text);
        }
    }

    public void setCloseHours(String text) {
        if (text == null || text.isEmpty()) {
            this.closeTV.setText(R.string.click_to_add);
        } else {
            this.closeTV.setText(text);
        }
    }

    public void setDayTextColor(int color) {
        this.dayTextColor = color;
        dayTV.setTextColor(color);
    }

    public void setOpenHoursTextColor(int color) {
        this.openHoursTextColor = color;
        openTV.setTextColor(color);
    }

    public void setCloseHoursTextColor(int color) {
        this.closeHoursTextColor = color;
        closeTV.setTextColor(color);
    }

    public void setDayTextSize(float size) {
        this.dayTextSize = size;
        dayTV.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }

    public void setOpenHoursTextSize(float size) {
        this.openTextSize = size;
        openTV.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }

    public void setCloseHoursTextSize(float size) {
        this.closeTextSize = size;
        closeTV.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }

    public void setOpenTitleTextSize(float size) {
        this.openTitleTextSize = size;
        openTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }

    public void setCloseTitleTextSize(float size) {
        this.closeTitleTextSize = size;
        closeTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }


}

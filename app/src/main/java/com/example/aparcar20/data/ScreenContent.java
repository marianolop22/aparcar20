package com.example.aparcar20.data;

public class ScreenContent {

    private int image;
    private String text;
    private boolean showButton;

    public ScreenContent(int image, String text, boolean showButton) {
        this.image = image;
        this.text = text;
        this.showButton = showButton;
    }

    public ScreenContent () {
        this.image = 0;
        this.text = "";
        this.showButton = false;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isShowButton() {
        return showButton;
    }

    public void setShowButton(boolean showButton) {
        this.showButton = showButton;
    }
}

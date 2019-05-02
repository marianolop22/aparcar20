package com.example.aparcar20;

public class ScreenContent {

    private String image;
    private String text;
    private boolean showButton;

    public ScreenContent(String image, String text, boolean showButton) {
        this.image = image;
        this.text = text;
        this.showButton = showButton;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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

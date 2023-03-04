package com.example.chatgpt.models;

public class GenresModel
{
    boolean isItemSelected = false;
    String labelText;

    public GenresModel(boolean isItemSelected, String labelText) {
        this.isItemSelected = isItemSelected;
        this.labelText = labelText;
    }

    public boolean isItemSelected() {
        return isItemSelected;
    }

    public void setItemSelected(boolean itemSelected) {
        isItemSelected = itemSelected;
    }

    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }
}

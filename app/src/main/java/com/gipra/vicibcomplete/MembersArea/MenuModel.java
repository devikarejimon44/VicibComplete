package com.gipra.vicibcomplete.MembersArea;

public class MenuModel {


    public String menuName;
    public boolean hasChildren, isGroup;
    int image;

    public MenuModel(String menuName, boolean isGroup, boolean hasChildren, int image) {

        this.menuName = menuName;
        this.isGroup = isGroup;
        this.hasChildren = hasChildren;
        this.setImage(image);
    }
    public int getImage() {
        return image;
    }

    private void setImage(int image) {
        this.image = image;
    }
}

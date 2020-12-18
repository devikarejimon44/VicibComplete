package com.gipra.vicibcomplete.MembersArea.ui.Genealogy;

public class TreeClass {


    public String menuName;
    public boolean hasChildren, isGroup;
    int image;

    public TreeClass(String menuName, boolean isGroup, boolean hasChildren) {

        this.menuName = menuName;
        this.isGroup = isGroup;
        this.hasChildren = hasChildren;

    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
//
//    public int getImage() {
//        return image;
//    }
//
//    private void setImage(int image) {
//        this.image = image;
//    }
}

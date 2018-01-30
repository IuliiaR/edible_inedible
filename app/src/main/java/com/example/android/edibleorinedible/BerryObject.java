package com.example.android.edibleorinedible;

class BerryObject {
    private int nameId;
    private int imageId;
    private boolean isEdible;

    BerryObject(int nameId, int imageId, boolean isEdible) {
        this.nameId = nameId;
        this.imageId = imageId;
        this.isEdible = isEdible;
    }

    boolean isEdible() {
        return isEdible;
    }

    int getImageId() {
        return imageId;
    }

    int getNameId() {
        return nameId;
    }
}

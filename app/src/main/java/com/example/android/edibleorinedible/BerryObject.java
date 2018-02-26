package com.example.android.edibleorinedible;

class BerryObject {
    private int mNameId;
    private int mImageId;
    private boolean mIsEdible;

    BerryObject(int nameId, int imageId, boolean isEdible) {
        this.mNameId = nameId;
        this.mImageId = imageId;
        this.mIsEdible = isEdible;
    }

    boolean isEdible() {
        return mIsEdible;
    }

    int getImageId() {
        return mImageId;
    }

    int getNameId() {
        return mNameId;
    }
}

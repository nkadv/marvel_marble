package com.mm.storage.local.db.materials;

import android.content.Context;
import android.database.Cursor;

import com.mm.labours.Labour;
import com.mm.materials.Material;

/**
 * Created by nayerram on 05-10-2016.
 */
public interface IMaterialStorage {
    public boolean persistMaterial(Context context, Material material);
    public Cursor getAllMaterials(Context context);
    public Labour getMaterial(Context context,Integer materialID);
    public Labour getMaterial(Context context,String materialName);
    public Cursor getMaterials(Context context, String searchString);
}

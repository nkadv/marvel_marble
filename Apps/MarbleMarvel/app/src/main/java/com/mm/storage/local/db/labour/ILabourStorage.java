package com.mm.storage.local.db.labour;

import android.content.Context;
import android.database.Cursor;

import com.mm.labours.Labour;

/**
 * Created by nayerram on 04-10-2016.
 */
public interface ILabourStorage {
    public boolean persistLabour(Context context, Labour labour);
    public Cursor getAllLabours(Context context);
    public Labour getLabour(Context context,Integer labourId);
    public Labour getLabour(Context context,String labourName);
    public Cursor getLabours(Context context, String searchString);
}

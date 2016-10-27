package com.mm.storage.local.db.sites.allocations.material;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by nayerram on 07-10-2016.
 */
public interface ISiteMaterialStorage {

    public boolean AddMaterialExpense(Context context, Integer m_id,  Double m_quanity,Integer siteId, String date);

    public boolean AddMaterialExpenses(Context context, ArrayList<Integer> mId_list,  ArrayList<Double> mQuanity_list,Integer siteId, String date);
    public boolean RemoveMaterialExpenses(Context context, ArrayList<Integer> m_list, Integer siteId, Date date);
    public Cursor getMaterialExpenses(Context context, Integer siteId);
}

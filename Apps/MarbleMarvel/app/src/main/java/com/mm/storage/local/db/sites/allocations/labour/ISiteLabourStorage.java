package com.mm.storage.local.db.sites.allocations.labour;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by nayerram on 07-10-2016.
 */
public interface ISiteLabourStorage {

    public boolean AddLabours(Context context, ArrayList<Integer> l_list, Integer siteId, Date date);
    public boolean transferLabours(Context context, ArrayList<Integer> l_list,Integer fromSiteId,Integer toSiteId,Date date);
    public boolean removeLabours(Context context, ArrayList<Integer> l_list,Integer siteId,Date date);
    public Cursor getLabours(Context context, Integer siteId);
}

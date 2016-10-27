package com.mm.storage.local.db.sites;

import android.content.Context;
import android.database.Cursor;

import com.mm.sites.Site;

/**
 * Created by nayerram on 05-10-2016.
 */
public interface ISitesStorage {
    public boolean persistSite(Context context, Site site);
    public Cursor getAllSites(Context context);
    public Cursor getActiveSites(Context context);
    public Cursor getClosedSites(Context context);
    public Cursor getPendingSites(Context context);
    public Site getSite(Context context, Integer siteId);
    public Site getSite(Context context,String siteName);
    public Cursor getSites(Context context, String searchString);
}

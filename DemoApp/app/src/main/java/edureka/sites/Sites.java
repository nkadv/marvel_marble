package edureka.sites;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by nayerram on 13-09-2016.
 */
public class Sites implements Serializable {

    static ArrayList<String> activeSites = new ArrayList<String>();
    static ArrayList<String> closedSites = new ArrayList<String>();

    private Sites(){

        closedSites.add("HSR,Sector 2");
        closedSites.add("Electronic City Phase-1");
        closedSites.add("Banshankari 6th Stage");
        closedSites.add("Palace Road, Mysore");
        closedSites.add("Koramangala, Bangalore");

    }
    private static Sites sites = null;

    static public Sites getInstance() {

        if (sites==null)
        {
            Log.i("getInstance","Creating Sites Obejct:");
            sites =  new Sites();
        }else{
            Log.i("getInstance","Returning existing Sites Obejct:");
        }

        return sites;
    }

    public String[] getActiveSites() {

        String []as = new String[activeSites.size()];
        Log.i("getActiveSites","sizeofActive:"+ activeSites.size());
        Log.i("addActiveSite","Object:"+ activeSites.toString());
        activeSites.toArray(as);
        return as;
    }

    public String[] getClosedSites() {
        String []cs = new String[closedSites.size()];
        closedSites.toArray(cs);
        return cs;
    }

    public void addActiveSite(String asite){
        activeSites.add(asite);
        Log.i("addActiveSite","sizeofActive:"+ activeSites.size());
        Log.i("addActiveSite","Object:"+ activeSites.toString());

    }
}

package edureka.sites;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by nayerram on 13-09-2016.
 */
//
//class Site{
//    String Name;
//    String Address;
//}
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

    public void saveSites(Context ctx){

        try{
            FileOutputStream fos = ctx.openFileOutput("activeSites", ctx.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject("asdfadsfasdf");
            oos.close();
            fos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void getSites(Context ctx){
            try {

                FileInputStream fis = new FileInputStream("activeSites");
                ObjectInputStream ois = new ObjectInputStream(fis);
                String str =(String)ois.readObject();
                Toast.makeText(ctx,str,Toast.LENGTH_SHORT).show();
                ois.close();
                fis.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


}

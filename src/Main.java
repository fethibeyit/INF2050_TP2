import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int nbrAlbum = 0;
        int nbrSingle = 0;
        String jsonTxt = null;
        try {
            jsonTxt = DiskFile.loadFileIntoString("collection01.json");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        JSONObject object = (JSONObject) JSONSerializer.toJSON(jsonTxt);
        JSONArray root = object.getJSONArray("collection");
        for (int i = 0; i < root.size(); i++) {
            if (root.getJSONObject(i).getString("type").equals("album")) nbrAlbum++;
        }
        System.out.println("Le nombre d'Album est : " + nbrAlbum);
        for (int i = 0; i < root.size(); i++) {
            if (root.getJSONObject(i).getString("type").equals("single")) nbrSingle++;
        }
        System.out.println("Le nombre de Single est : " + nbrSingle);

        System.out.println("La liste des albums depuis 2003 :");
        for (int i = 0; i < root.size(); i++) {
            JSONObject album = root.getJSONObject(i);
            if (album.getString("type").equals("album") && album.getInt("publication") >= 2003)
                System.out.println(album.getString("title"));
        }
        System.out.println("La liste des albums qui ont la note 5 :");
        for (int i = 0; i < root.size(); i++) {
            JSONObject album = root.getJSONObject(i);
            if (album.getString("type").equals("album") && album.getInt("rating") == 5)
                System.out.println(album.getString("title"));
        }

        ArrayList<Album> collection = new ArrayList<>();
        collection.add(new Album("album", "hybride theory", "linkin park", 2001, 4));
        collection.add(new Album("album", "simulation", "muse", 2018, 3));
        collection.add(new Album("single", "blinded by the light", "the weekend", 2019, 5));

        JSONArray jsonCollection = new JSONArray();
        for (Album alb : collection) {
            JSONObject jsonAlbum = new JSONObject();
            jsonAlbum.put("type", alb.getType());
            jsonAlbum.put("title", alb.getTitle());
            jsonAlbum.put("artist", alb.getArtist());
            jsonAlbum.put("publication", alb.getPublication());
            jsonAlbum.put("rating", alb.getRating());
            jsonCollection.add(jsonAlbum);
        }

        JSONObject jsonfinal = new JSONObject();
        jsonfinal.put("collection", jsonCollection);

        try {
            DiskFile.saveStringIntoFile("favoriteAlbum.json", jsonfinal.toString(2));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

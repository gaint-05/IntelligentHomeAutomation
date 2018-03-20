package com.rajhack4.homeautomation;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbot_5 on 19-03-2018.
 */
public class Utils {
    private static final int TATASKY_FREQUENCY = 36000;
    public static final String LOG_CAT = Utils.class.getName();

    public static List<Tvshow> createTopList() {
        List<Tvshow> list = new ArrayList<Tvshow>();
        list.add(new Tvshow(R.drawable.kapil_sharma_show_comedy,130));
        list.add(new Tvshow(R.drawable.bhabi_comedy,139));
        list.add(new Tvshow(R.drawable.taarak_mehta_ka_ooltah_chashmah_comedy_drama,134));
        list.add(new Tvshow(R.drawable.ask_music,809));
        list.add(new Tvshow(R.drawable.jodha_drama,143));
        return list;
    }

    public static List<Tvshow> createKidsList() {
        List<Tvshow> list = new ArrayList<Tvshow>();
        list.add(new Tvshow(R.drawable.roll_no_kids,666));
        list.add(new Tvshow(R.drawable.chota_bheem_kids,670));
        list.add(new Tvshow(R.drawable.doraemon_kids,655));
        list.add(new Tvshow(R.drawable.ninja_kids,663));
        list.add(new Tvshow(R.drawable.shinchan_kids,655));
        return list;
    }

    public static List<Tvshow> createComedyList() {
        List<Tvshow> list = new ArrayList<Tvshow>();
        list.add(new Tvshow(R.drawable.akbar_birbal_comedy,166));
        list.add(new Tvshow(R.drawable.chidiyaghar_comedy,134));
        list.add(new Tvshow(R.drawable.kapil_sharma_show_comedy,130));
        list.add(new Tvshow(R.drawable.bhabi_comedy,139));
        list.add(new Tvshow(R.drawable.taarak_mehta_ka_ooltah_chashmah_comedy_drama,134));
        return list;
    }

    public static List<Tvshow> createDramaList() {
        List<Tvshow> list = new ArrayList<Tvshow>();
        list.add(new Tvshow(R.drawable.balika_drama,149));
        list.add(new Tvshow(R.drawable.jodha_drama,143));
        list.add(new Tvshow(R.drawable.tenali_rama_drama,134));
        list.add(new Tvshow(R.drawable.taarak_mehta_ka_ooltah_chashmah_comedy_drama,134));
        list.add(new Tvshow(R.drawable.yeh_un_dinon_ki_baat_hai_drama, 134));
        return list;
    }
    public static List<Tvshow> createKnowledgeList() {
        List<Tvshow> list = new ArrayList<Tvshow>();
        list.add(new Tvshow(R.drawable.cosmos_knowledge,709));
        list.add(new Tvshow(R.drawable.locked_knowledge,709));
        list.add(new Tvshow(R.drawable.storage_wars_knowledge,721));
        list.add(new Tvshow(R.drawable.omg_knowledge,721));
        list.add(new Tvshow(R.drawable.too_cute_knowledge, 717));
        return list;
    }
    public static List<Tvshow> createMusicList() {
        List<Tvshow> list = new ArrayList<Tvshow>();
        list.add(new Tvshow(R.drawable.ask_music,809));
        list.add(new Tvshow(R.drawable.bakwaas_music,809));
        list.add(new Tvshow(R.drawable.highway_music,809));
        return list;
    }



}

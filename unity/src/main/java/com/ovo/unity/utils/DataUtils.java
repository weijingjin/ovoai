package com.ovo.unity.utils;

import java.util.HashMap;

public class DataUtils {
    private static DataUtils utils = null;

    private HashMap<String, String> dataMap;

    public static DataUtils getInstance() {
        if (utils == null) {
            synchronized (DataUtils.class) {
                if (utils == null) {
                    utils = new DataUtils();
                }
            }
        }
        return utils;
    }

    /***初始化数据**/
    public void initData() {
        dataMap = new HashMap<>();
        String text1 = "白";
        String json1 = "{\"faceData\" :" +
                " {\"jawOpen\" : 0," +
                "\"mouthFunnel\" : 0," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 0," +
                "\"mouthSmile_R\" : 0}}" +
                "{\"faceData\" :" +
                " {\"jawOpen\" : 50," +
                "\"mouthFunnel\" : 0," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 50," +
                "\"mouthSmile_R\" : 50}}";
        dataMap.put(text1, json1);

        String text2 = "日";
        String json2 = "{\"faceData\" :" +
                " {\"jawOpen\" : 0," +
                "\"mouthFunnel\" : 70," +
                "\"mouthPucker\" : 40," +
                "\"mouthSmile_L\" : 50," +
                "\"mouthSmile_R\" : 50}}" +
                "{\"faceData\" :" +
                " {\"jawOpen\" : 0," +
                "\"mouthFunnel\" : 70," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 50," +
                "\"mouthSmile_R\" : 50}}";
        dataMap.put(text2, json2);

        String text3 = "依";
        String json3 = "{\"faceData\" :" +
                " {\"jawOpen\" : 0," +
                "\"mouthFunnel\" : 40," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 50," +
                "\"mouthSmile_R\" : 50}}" +
                "{\"faceData\" :" +
                " {\"jawOpen\" : 0," +
                "\"mouthFunnel\" : 40," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 50," +
                "\"mouthSmile_R\" : 50}}";
        dataMap.put(text3, json3);

        String text4 = "山";
        String json4 = "{\"faceData\" :" +
                " {\"jawOpen\" : 0," +
                "\"mouthFunnel\" : 60," +
                "\"mouthPucker\" : 32," +
                "\"mouthSmile_L\" : 20," +
                "\"mouthSmile_R\" : 20}}" +
                "{\"faceData\" :" +
                " {\"jawOpen\" : 20," +
                "\"mouthFunnel\" : 40," +
                "\"mouthPucker\" : 7," +
                "\"mouthSmile_L\" : 60," +
                "\"mouthSmile_R\" : 60}}";
        dataMap.put(text4, json4);

        String text5 = "尽";
        String json5 = "{\"faceData\" :" +
                " {\"jawOpen\" : 0," +
                "\"mouthFunnel\" : 50," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 60," +
                "\"mouthSmile_R\" : 60}}" +
                "{\"faceData\" :" +
                " {\"jawOpen\" : 0," +
                "\"mouthFunnel\" : 36," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 80," +
                "\"mouthSmile_R\" : 80}}";
        dataMap.put(text5, json5);

        String text6 = "，";
        String json6 = "{\"faceData\" :" +
                " {\"jawOpen\" : 0," +
                "\"mouthFunnel\" : 0," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 0," +
                "\"mouthSmile_R\" : 0}}" +
                "{\"faceData\" :" +
                " {\"jawOpen\" : 0," +
                "\"mouthFunnel\" : 0," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 0," +
                "\"mouthSmile_R\" : 0}}";
        dataMap.put(text6, json6);

        String text7 = "黄";
        String json7 = "{\"faceData\" :" +
                " {\"jawOpen\" : 0," +
                "\"mouthFunnel\" : 60," +
                "\"mouthPucker\" : 70," +
                "\"mouthSmile_L\" : 0," +
                "\"mouthSmile_R\" : 0}}" +
                "{\"faceData\" :" +
                " {\"jawOpen\" : 36," +
                "\"mouthFunnel\" : 58," +
                "\"mouthPucker\" : 20," +
                "\"mouthSmile_L\" : 0," +
                "\"mouthSmile_R\" : 0}}";
        dataMap.put(text7, json7);

        String text8 = "河";
        String json8 = "{\"faceData\" :" +
                " {\"jawOpen\" : 11," +
                "\"mouthFunnel\" : 37," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 10," +
                "\"mouthSmile_R\" : 10}}" +
                "{\"faceData\" :" +
                " {\"jawOpen\" : 11," +
                "\"mouthFunnel\" : 37," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 10," +
                "\"mouthSmile_R\" : 10}}";
        dataMap.put(text8, json8);

        String text9 = "入";
        String json9 = "{\"faceData\" :" +
                " {\"jawOpen\" : 0," +
                "\"mouthFunnel\" : 72," +
                "\"mouthPucker\" : 47," +
                "\"mouthSmile_L\" : 0," +
                "\"mouthSmile_R\" : 0}}" +
                "{\"faceData\" :" +
                " {\"jawOpen\" : 72," +
                "\"mouthFunnel\" : 47," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 0," +
                "\"mouthSmile_R\" : 0}}";
        dataMap.put(text9, json9);

        String text10 = "海";
        String json10 = "{\"faceData\" :" +
                " {\"jawOpen\" : 40," +
                "\"mouthFunnel\" : 0," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 0," +
                "\"mouthSmile_R\" : 0}}" +
                "{\"faceData\" :" +
                " {\"jawOpen\" : 56," +
                "\"mouthFunnel\" : 0," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 30," +
                "\"mouthSmile_R\" : 30}}";
        dataMap.put(text10, json10);

        String text11 = "流";
        String json11 = "{\"faceData\" :" +
                " {\"jawOpen\" : 20," +
                "\"mouthFunnel\" : 0," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 20," +
                "\"mouthSmile_R\" : 20}}" +
                "{\"faceData\" :" +
                " {\"jawOpen\" : 0," +
                "\"mouthFunnel\" : 75," +
                "\"mouthPucker\" : 28," +
                "\"mouthSmile_L\" : 0," +
                "\"mouthSmile_R\" : 0}}";
        dataMap.put(text11, json11);

        String text12 = "。";
        String json12 = "{\"faceData\" :" +
                " {\"jawOpen\" : 0," +
                "\"mouthFunnel\" : 0," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 0," +
                "\"mouthSmile_R\" : 0}}" +
                "{\"faceData\" :" +
                " {\"jawOpen\" : 0," +
                "\"mouthFunnel\" : 0," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 0," +
                "\"mouthSmile_R\" : 0}}";
        dataMap.put(text12, json12);

        String text13 = "欲";
        String json13 = "{\"faceData\" :" +
                " {\"jawOpen\" : 0," +
                "\"mouthFunnel\" : 76," +
                "\"mouthPucker\" : 58," +
                "\"mouthSmile_L\" : 0," +
                "\"mouthSmile_R\" : 0}}" +
                "{\"faceData\" :" +
                " {\"jawOpen\" : 0," +
                "\"mouthFunnel\" : 76," +
                "\"mouthPucker\" : 58," +
                "\"mouthSmile_L\" : 0," +
                "\"mouthSmile_R\" : 0}}";
        dataMap.put(text13, json13);

        String text14 = "穷";
        String json14 = "{\"faceData\" :" +
                " {\"jawOpen\" : 0," +
                "\"mouthFunnel\" : 80," +
                "\"mouthPucker\" : 46," +
                "\"mouthSmile_L\" : 0," +
                "\"mouthSmile_R\" : 0}}" +
                "{\"faceData\" :" +
                " {\"jawOpen\" : 8," +
                "\"mouthFunnel\" : 80," +
                "\"mouthPucker\" : 46," +
                "\"mouthSmile_L\" : 0," +
                "\"mouthSmile_R\" : 0}}";
        dataMap.put(text14, json14);

        String text15 = "千";
        String json15 = "{\"faceData\" :" +
                " {\"jawOpen\" : 0," +
                "\"mouthFunnel\" : 0," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 0," +
                "\"mouthSmile_R\" : 0}}" +
                "{\"faceData\" :" +
                " {\"jawOpen\" : 20," +
                "\"mouthFunnel\" : 0," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 30," +
                "\"mouthSmile_R\" : 30}}";
        dataMap.put(text15, json15);

        String text16 = "里";
        String json16 = "{\"faceData\" :" +
                " {\"jawOpen\" : 20," +
                "\"mouthFunnel\" : 0," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 30," +
                "\"mouthSmile_R\" : 30}}" +
                "{\"faceData\" :" +
                " {\"jawOpen\" : 25," +
                "\"mouthFunnel\" : 0," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 60," +
                "\"mouthSmile_R\" : 60}}";
        dataMap.put(text16, json16);

        String text17 = "目";
        String json17 = "{\"faceData\" :" +
                " {\"jawOpen\" : 0," +
                "\"mouthFunnel\" : 54," +
                "\"mouthPucker\" : 77," +
                "\"mouthSmile_L\" : 0," +
                "\"mouthSmile_R\" : 0}}" +
                "{\"faceData\" :" +
                " {\"jawOpen\" : 5," +
                "\"mouthFunnel\" : 54," +
                "\"mouthPucker\" : 77," +
                "\"mouthSmile_L\" : 0," +
                "\"mouthSmile_R\" : 0}}";
        dataMap.put(text17, json17);

        String text18 = "更";
        String json18 = "{\"faceData\" :" +
                " {\"jawOpen\" : 15," +
                "\"mouthFunnel\" : 0," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 27," +
                "\"mouthSmile_R\" : 27}}" +
                "{\"faceData\" :" +
                " {\"jawOpen\" : 20," +
                "\"mouthFunnel\" : 0," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 27," +
                "\"mouthSmile_R\" : 27}}";
        dataMap.put(text18, json18);

        String text19 = "上";
        String json19 = "{\"faceData\" :" +
                " {\"jawOpen\" : 13," +
                "\"mouthFunnel\" : 44," +
                "\"mouthPucker\" : 65," +
                "\"mouthSmile_L\" : 0," +
                "\"mouthSmile_R\" : 0}}" +
                "{\"faceData\" :" +
                " {\"jawOpen\" : 28," +
                "\"mouthFunnel\" : 44," +
                "\"mouthPucker\" : 65," +
                "\"mouthSmile_L\" : 0," +
                "\"mouthSmile_R\" : 0}}";
        dataMap.put(text19, json19);

        String text20 = "一";
        String json20 = "{\"faceData\" :" +
                " {\"jawOpen\" : 0," +
                "\"mouthFunnel\" : 40," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 50," +
                "\"mouthSmile_R\" : 50}}" +
                "{\"faceData\" :" +
                " {\"jawOpen\" : 0," +
                "\"mouthFunnel\" : 40," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 50," +
                "\"mouthSmile_R\" : 50}}";
        dataMap.put(text20, json20);

        String text21 = "层";
        String json21 = "{\"faceData\" :" +
                " {\"jawOpen\" : 9," +
                "\"mouthFunnel\" : 0," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 26," +
                "\"mouthSmile_R\" : 26}}" +
                "{\"faceData\" :" +
                " {\"jawOpen\" : 25," +
                "\"mouthFunnel\" : 0," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 26," +
                "\"mouthSmile_R\" : 26}}";
        dataMap.put(text21, json21);

        String text22 = "楼";
        String json22 = "{\"faceData\" :" +
                " {\"jawOpen\" : 29," +
                "\"mouthFunnel\" : 0," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 26," +
                "\"mouthSmile_R\" : 26}}" +
                "{\"faceData\" :" +
                " {\"jawOpen\" : 0," +
                "\"mouthFunnel\" : 75," +
                "\"mouthPucker\" : 28," +
                "\"mouthSmile_L\" : 0," +
                "\"mouthSmile_R\" : 0}}";
//                "{\"faceData\" :" +
//                " {\"jawOpen\" : 8," +
//                "\"mouthFunnel\" : 58," +
//                "\"mouthPucker\" : 66," +
//                "\"mouthSmile_L\" : 0," +
//                "\"mouthSmile_R\" : 0}}";
        dataMap.put(text22, json22);
    }

    /***初始化数据**/
    public void initPYData() {
        dataMap = new HashMap<>();
        String text0 = ",";
        String json0 = "{\"faceData\" :" +
                " {\"jawOpen\" : 0," +
                "\"mouthFunnel\" : 0," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 0," +
                "\"mouthSmile_R\" : 0}}" +
                "{\"faceData\" :" +
                " {\"jawOpen\" : 0," +
                "\"mouthFunnel\" : 0," +
                "\"mouthPucker\" : 0," +
                "\"mouthSmile_L\" : 0," +
                "\"mouthSmile_R\" : 0}}";
        dataMap.put(text0, json0);

        String json1 = "{\"faceData\" :" +
                " {\"Cheek_Raiser\" : 0," +
                "\"Lip_Corner_Puller\" : 26," +
                "\"Cheek_Puffer_Levator_anguli_ori\" : 0," +
                "\"Lip_Puckerer_Incisivii_labii\" : 0," +
                "\"Lip_Funneler_Orbicularis_oris\" : 0," +
                "\"Lips_part\" : 0," +
                "\"Jaw_Drop\" : 0," +
                "\"Lip_Suck_Orbicularis_oris\" : 0," +
                "\"Mouth_Stretch_Pterygoids__Digastric\" : 0," +
                "\"Lip_Tightener\" : 100," +
                "\"mouthFunnel\" : 0}}";
        dataMap.put("b", json1);
        dataMap.put("p", json1);
        dataMap.put("m", json1);

        String json2 = "{\"faceData\" :" +
                " {\"Cheek_Raiser\" : 22," +
                "\"Lip_Corner_Puller\" : 0," +
                "\"Cheek_Puffer_Levator_anguli_ori\" : 0," +
                "\"Lip_Puckerer_Incisivii_labii\" : 0," +
                "\"Lip_Funneler_Orbicularis_oris\" : 0," +
                "\"Lips_part\" : 0," +
                "\"Jaw_Drop\" : 0," +
                "\"Lip_Suck_Orbicularis_oris\" : 38," +
                "\"Mouth_Stretch_Pterygoids__Digastric\" : 0," +
                "\"Lip_Tightener\" : 0," +
                "\"mouthFunnel\" : 0}}";
        dataMap.put("f", json2);

        String json3 = "{\"faceData\" :" +
                " {\"Cheek_Raiser\" : 0," +
                "\"Lip_Corner_Puller\" : 0," +
                "\"Cheek_Puffer_Levator_anguli_ori\" : 68," +
                "\"Lip_Puckerer_Incisivii_labii\" : 0," +
                "\"Lip_Funneler_Orbicularis_oris\" : 59," +
                "\"Lips_part\" : 0," +
                "\"Jaw_Drop\" : 0," +
                "\"Lip_Suck_Orbicularis_oris\" : 0," +
                "\"Mouth_Stretch_Pterygoids__Digastric\" : 0," +
                "\"Lip_Tightener\" : 0," +
                "\"mouthFunnel\" : 0}}";
        dataMap.put("d", json3);
        dataMap.put("t", json3);
        dataMap.put("n", json3);
        dataMap.put("l", json3);
        dataMap.put("g", json3);
        dataMap.put("k", json3);
        dataMap.put("h", json3);

        String json4 = "{\"faceData\" :" +
                " {\"Cheek_Raiser\" : 0," +
                "\"Lip_Corner_Puller\" : 0," +
                "\"Cheek_Puffer_Levator_anguli_ori\" : 0," +
                "\"Lip_Puckerer_Incisivii_labii\" : 0," +
                "\"Lip_Funneler_Orbicularis_oris\" : 62," +
                "\"Lips_part\" : 52," +
                "\"Jaw_Drop\" : 0," +
                "\"Lip_Suck_Orbicularis_oris\" : 0," +
                "\"Mouth_Stretch_Pterygoids__Digastric\" : 0," +
                "\"Lip_Tightener\" : 55," +
                "\"mouthFunnel\" : 0}}";
        dataMap.put("zh", json4);
        dataMap.put("ch", json4);
        dataMap.put("sh", json4);
        dataMap.put("r", json4);

        String json5 = "{\"faceData\" :" +
                " {\"Cheek_Raiser\" : 0," +
                "\"Lip_Corner_Puller\" : 0," +
                "\"Cheek_Puffer_Levator_anguli_ori\" : 100," +
                "\"Lip_Puckerer_Incisivii_labii\" : 0," +
                "\"Lip_Funneler_Orbicularis_oris\" : 0," +
                "\"Lips_part\" : 100," +
                "\"Jaw_Drop\" : 0," +
                "\"Lip_Suck_Orbicularis_oris\" : 23," +
                "\"Mouth_Stretch_Pterygoids__Digastric\" : 0," +
                "\"Lip_Tightener\" : 0," +
                "\"mouthFunnel\" : 0}}";
        dataMap.put("z", json5);
        dataMap.put("c", json5);
        dataMap.put("s", json5);
        dataMap.put("j", json5);
        dataMap.put("q", json5);
        dataMap.put("x", json5);

        String json6 = "{\"faceData\" :" +
                " {\"Cheek_Raiser\" : 0," +
                "\"Lip_Corner_Puller\" : 0," +
                "\"Cheek_Puffer_Levator_anguli_ori\" : 0," +
                "\"Lip_Puckerer_Incisivii_labii\" : 0," +
                "\"Lip_Funneler_Orbicularis_oris\" : 0," +
                "\"Lips_part\" : 69," +
                "\"Jaw_Drop\" : 0," +
                "\"Lip_Suck_Orbicularis_oris\" : 0," +
                "\"Mouth_Stretch_Pterygoids__Digastric\" : 39," +
                "\"Lip_Tightener\" : 0," +
                "\"mouthFunnel\" : 56}}";
        dataMap.put("a", json6);
        dataMap.put("an", json6);
        dataMap.put("ang", json6);

        String json7 = "{\"faceData\" :" +
                " {\"Cheek_Raiser\" : 0," +
                "\"Lip_Corner_Puller\" : 0," +
                "\"Cheek_Puffer_Levator_anguli_ori\" : 51," +
                "\"Lip_Puckerer_Incisivii_labii\" : 0," +
                "\"Lip_Funneler_Orbicularis_oris\" : 0," +
                "\"Lips_part\" : 0," +
                "\"Jaw_Drop\" : 0," +
                "\"Lip_Suck_Orbicularis_oris\" : 68," +
                "\"Mouth_Stretch_Pterygoids__Digastric\" : 42," +
                "\"Lip_Tightener\" : 0," +
                "\"mouthFunnel\" : 0}}";
        dataMap.put("e", json7);
        dataMap.put("er", json7);
        dataMap.put("en", json7);
        dataMap.put("eng", json7);

        String json8 = "{\"faceData\" :" +
                " {\"Cheek_Raiser\" : 0," +
                "\"Lip_Corner_Puller\" : 0," +
                "\"Cheek_Puffer_Levator_anguli_ori\" : 0," +
                "\"Lip_Puckerer_Incisivii_labii\" : 0," +
                "\"Lip_Funneler_Orbicularis_oris\" : 0," +
                "\"Lips_part\" : 0," +
                "\"Jaw_Drop\" : 14," +
                "\"Lip_Suck_Orbicularis_oris\" : 0," +
                "\"Mouth_Stretch_Pterygoids__Digastric\" : 0," +
                "\"Lip_Tightener\" : 56," +
                "\"mouthFunnel\" : 30}}";
        dataMap.put("i", json8);
        dataMap.put("in", json8);
        dataMap.put("ing", json8);
        dataMap.put("y", json8);

        String json9 = "{\"faceData\" :" +
                " {\"Cheek_Raiser\" : 0," +
                "\"Lip_Corner_Puller\" : 0," +
                "\"Cheek_Puffer_Levator_anguli_ori\" : 0," +
                "\"Lip_Puckerer_Incisivii_labii\" : 34," +
                "\"Lip_Funneler_Orbicularis_oris\" : 79," +
                "\"Lips_part\" : 0," +
                "\"Jaw_Drop\" : 0," +
                "\"Lip_Suck_Orbicularis_oris\" : 0," +
                "\"Mouth_Stretch_Pterygoids__Digastric\" : 0," +
                "\"Lip_Tightener\" : 13," +
                "\"mouthFunnel\" : 44}}";
        dataMap.put("o", json9);
        dataMap.put("ong", json9);

        String json10 = "{\"faceData\" :" +
                " {\"Cheek_Raiser\" : 0," +
                "\"Lip_Corner_Puller\" : 0," +
                "\"Cheek_Puffer_Levator_anguli_ori\" : 0," +
                "\"Lip_Puckerer_Incisivii_labii\" : 0," +
                "\"Lip_Funneler_Orbicularis_oris\" : 0," +
                "\"Lips_part\" : 0," +
                "\"Jaw_Drop\" : 0," +
                "\"Lip_Suck_Orbicularis_oris\" : 0," +
                "\"Mouth_Stretch_Pterygoids__Digastric\" : 0," +
                "\"Lip_Tightener\" : 100," +
                "\"mouthFunnel\" : 44}}";
        dataMap.put("u", json10);
        dataMap.put("v", json10);
        dataMap.put("un", json10);
        dataMap.put("vn", json10);
        dataMap.put("w", json10);

    }

    /***初始化数据**/
    public void initPYPorsonData() {
        dataMap = new HashMap<>();
        String text0 = ",";
        String json0 = "{\"faceData\":" +
                "{\"Explosive\":0," +
                "\"Tight-O\":0," +
                "\"Wide\":0," +
                "\"Affricate\":0," +
                "\"Lip_Open\":0," +
                "\"Mouth_Pucker_Open\":0," +
                "\"Mouth_Widen_Sides\":0," +
                "\"Mouth_Lips_Tuck\":0," +
                "\"Mouth_Bottom_Lip_Down\":0," +
                "\"Mouth_Bottom_Lip_Bite\":0," +
                "\"Mouth_Open\":0}}";
        dataMap.put(text0, json0);

        String json1 = "{\"faceData\":" +
                "{\"Explosive\":0," +
                "\"Tight-O\":0," +
                "\"Wide\":0," +
                "\"Affricate\":0," +
                "\"Lip_Open\":0," +
                "\"Mouth_Pucker_Open\":0," +
                "\"Mouth_Widen_Sides\":0," +
                "\"Mouth_Lips_Tuck\":68," +
                "\"Mouth_Bottom_Lip_Down\":0," +
                "\"Mouth_Bottom_Lip_Bite\":0," +
                "\"Mouth_Open\":0}}";
        dataMap.put("b", json1);
        dataMap.put("p", json1);
        dataMap.put("m", json1);

        String json2 = "{\"faceData\":" +
                "{\"Explosive\":0," +
                "\"Tight-O\":0," +
                "\"Wide\":0," +
                "\"Affricate\":0," +
                "\"Lip_Open\":0," +
                "\"Mouth_Pucker_Open\":0," +
                "\"Mouth_Widen_Sides\":0," +
                "\"Mouth_Lips_Tuck\":0," +
                "\"Mouth_Bottom_Lip_Down\":0," +
                "\"Mouth_Bottom_Lip_Bite\":100," +
                "\"Mouth_Open\":0}}";
        dataMap.put("f", json2);

        String json3 = "{\"faceData\":" +
                "{\"Explosive\":0," +
                "\"Tight-O\":0," +
                "\"Wide\":0," +
                "\"Affricate\":0," +
                "\"Lip_Open\":0," +
                "\"Mouth_Pucker_Open\":0," +
                "\"Mouth_Widen_Sides\":100," +
                "\"Mouth_Lips_Tuck\":0," +
                "\"Mouth_Bottom_Lip_Down\":60," +
                "\"Mouth_Bottom_Lip_Bite\":0," +
                "\"Mouth_Open\":0}}";
        dataMap.put("d", json3);
        dataMap.put("t", json3);
        dataMap.put("n", json3);
        dataMap.put("l", json3);
        dataMap.put("g", json3);
        dataMap.put("k", json3);
        dataMap.put("h", json3);

        String json4 = "{\"faceData\":" +
                "{\"Explosive\":0," +
                "\"Tight-O\":0," +
                "\"Wide\":0," +
                "\"Affricate\":0," +
                "\"Lip_Open\":0," +
                "\"Mouth_Pucker_Open\":80," +
                "\"Mouth_Widen_Sides\":0," +
                "\"Mouth_Lips_Tuck\":0," +
                "\"Mouth_Bottom_Lip_Down\":0," +
                "\"Mouth_Bottom_Lip_Bite\":0," +
                "\"Mouth_Open\":0}}";
        dataMap.put("zh", json4);
        dataMap.put("ch", json4);
        dataMap.put("sh", json4);
        dataMap.put("r", json4);

        String json5 = "{\"faceData\":" +
                "{\"Explosive\":0," +
                "\"Tight-O\":0," +
                "\"Wide\":0," +
                "\"Affricate\":73," +
                "\"Lip_Open\":0," +
                "\"Mouth_Pucker_Open\":0," +
                "\"Mouth_Widen_Sides\":0," +
                "\"Mouth_Lips_Tuck\":0," +
                "\"Mouth_Bottom_Lip_Down\":0," +
                "\"Mouth_Bottom_Lip_Bite\":0," +
                "\"Mouth_Open\":0}}";
        dataMap.put("z", json5);
        dataMap.put("c", json5);
        dataMap.put("s", json5);
        dataMap.put("j", json5);
        dataMap.put("q", json5);
        dataMap.put("x", json5);

        String json6 = "{\"faceData\":" +
                "{\"Explosive\":0," +
                "\"Tight-O\":0," +
                "\"Wide\":0," +
                "\"Affricate\":0," +
                "\"Lip_Open\":0," +
                "\"Mouth_Pucker_Open\":0," +
                "\"Mouth_Widen_Sides\":0," +
                "\"Mouth_Lips_Tuck\":0," +
                "\"Mouth_Bottom_Lip_Down\":0," +
                "\"Mouth_Bottom_Lip_Bite\":0," +
                "\"Mouth_Open\":90}}";
        dataMap.put("a", json6);
        dataMap.put("an", json6);
        dataMap.put("ang", json6);

        String json7 = "{\"faceData\":" +
                "{\"Explosive\":0," +
                "\"Tight-O\":0," +
                "\"Wide\":62," +
                "\"Affricate\":0," +
                "\"Lip_Open\":89," +
                "\"Mouth_Pucker_Open\":0," +
                "\"Mouth_Widen_Sides\":62," +
                "\"Mouth_Lips_Tuck\":0," +
                "\"Mouth_Bottom_Lip_Down\":0," +
                "\"Mouth_Bottom_Lip_Bite\":0," +
                "\"Mouth_Open\":0}}";
        dataMap.put("e", json7);
        dataMap.put("ie", json7);
        dataMap.put("ve", json7);
        dataMap.put("er", json7);
        dataMap.put("en", json7);
        dataMap.put("eng", json7);

        String json8 = "{\"faceData\":" +
                "{\"Explosive\":0," +
                "\"Tight-O\":0," +
                "\"Wide\":0," +
                "\"Affricate\":0," +
                "\"Lip_Open\":0," +
                "\"Mouth_Pucker_Open\":52," +
                "\"Mouth_Widen_Sides\":87," +
                "\"Mouth_Lips_Tuck\":0," +
                "\"Mouth_Bottom_Lip_Down\":0," +
                "\"Mouth_Bottom_Lip_Bite\":0," +
                "\"Mouth_Open\":0}}";
        dataMap.put("i", json8);
        dataMap.put("ai", json8);
        dataMap.put("ei", json8);
        dataMap.put("ui", json8);
        dataMap.put("in", json8);
        dataMap.put("ing", json8);
        dataMap.put("y", json8);

        String json9 = "{\"faceData\":" +
                "{\"Explosive\":0," +
                "\"Tight-O\":88," +
                "\"Wide\":0," +
                "\"Affricate\":0," +
                "\"Lip_Open\":0," +
                "\"Mouth_Pucker_Open\":0," +
                "\"Mouth_Widen_Sides\":0," +
                "\"Mouth_Lips_Tuck\":0," +
                "\"Mouth_Bottom_Lip_Down\":0," +
                "\"Mouth_Bottom_Lip_Bite\":0," +
                "\"Mouth_Open\":0}}";
        dataMap.put("o", json9);
        dataMap.put("ao", json9);
        dataMap.put("ong", json9);

        String json10 = "{\"faceData\":" +
                "{\"Explosive\":100," +
                "\"Tight-O\":100," +
                "\"Wide\":0," +
                "\"Affricate\":0," +
                "\"Lip_Open\":0," +
                "\"Mouth_Pucker_Open\":0," +
                "\"Mouth_Widen_Sides\":0," +
                "\"Mouth_Lips_Tuck\":0," +
                "\"Mouth_Bottom_Lip_Down\":0," +
                "\"Mouth_Bottom_Lip_Bite\":0," +
                "\"Mouth_Open\":0}}";
        dataMap.put("u", json10);
        dataMap.put("ou", json10);
        dataMap.put("iu", json10);
        dataMap.put("v", json10);
        dataMap.put("un", json10);
        dataMap.put("vn", json10);
        dataMap.put("w", json10);

    }

    public HashMap<String, String> getDataMap() {
        return dataMap;
    }

    public void setDataMap(HashMap<String, String> dataMap) {
        this.dataMap = dataMap;
    }
}

package badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import badarkhe.gaurav.andorid.apps.com.malhar2k17.R;

/**
 * Created by Admin on 11-10-2016.
 */
public class Constants {

    public static class Arrays {

        public static String base_url = "http://zipd.in/MALHAR%202K17/";

        public static String[] college = {"Select College", "RGCER", "Others"};

        public static String[] branches = {"Select Branch", "CSE", "ETC", "EE", "IT", "CTECH", "ETX"};

        public static String[] sections = {"Select Section", "Sec-A", "Sec-B", "Others"};

        public static String[] semisters = {"Select Sem", "1st-Sem", "2nd-Sem", "3rd-Sem", "4th-Sem", "5th-Sem", "6th-Sem", "7th-Sem", "8th-Sem"};

        public static String[] colors = {"#30A8FF",
                "#A43DFF", "#41DF91", "#FFA74B", "#FF5E5E", "#E91E63"};

        public static int[] thumnails = {R.drawable.bat, R.drawable.candle, R.drawable.cemetery,
                R.drawable.cobweb, R.drawable.coffin, R.drawable.fence, R.drawable.ghost_castle, R.drawable.guillotine, R.drawable.pumpkin_lamp,
                R.drawable.scarecrow};


        public static class Pics {
            public static String[] dance = {"COLLIGIATE/CULTURAL/DANCE/%20(2).JPG", "COLLIGIATE/CULTURAL/DANCE/%20(3).JPG", "COLLIGIATE/CULTURAL/DANCE/%20(4).JPG", "COLLIGIATE/CULTURAL/DANCE/%20(1).JPG", "COLLIGIATE/CULTURAL/DANCE/%20(5).JPG"};

            public static String[] drama = {"COLLIGIATE/CULTURAL/DRAMA/%20(1).JPG", "COLLIGIATE/CULTURAL/DRAMA/%20(2).JPG", "COLLIGIATE/CULTURAL/DRAMA/%20(3).JPG"};

            public static String[] singing = {"COLLIGIATE/CULTURAL/SINGING/%20(1).JPG", "COLLIGIATE/CULTURAL/SINGING/%20(2).JPG", "COLLIGIATE/CULTURAL/SINGING/%20(3).JPG"
                    , "COLLIGIATE/CULTURAL/SINGING/%20(4).JPG", "COLLIGIATE/CULTURAL/SINGING/%20(5).JPG"};


            public static String[] rock_band = {"INTERCOLLIGIATE/ROCKBAND/%20(1).JPG", "INTERCOLLIGIATE/ROCKBAND/%20(2).JPG", "INTERCOLLIGIATE/ROCKBAND/%20(3).JPG"
                    , "INTERCOLLIGIATE/ROCKBAND/%20(4).JPG", "INTERCOLLIGIATE/ROCKBAND/%20(5).JPG"};

            public static String[] fasion = {"INTERCOLLIGIATE/FASHION%20SHOW/%20(1).JPG", "INTERCOLLIGIATE/FASHION%20SHOW/%20(2).JPG", "INTERCOLLIGIATE/FASHION%20SHOW/%20(3).JPG", "INTERCOLLIGIATE/FASHION%20SHOW/%20(4).JPG"};

            public static String[] comedy = {"INTERCOLLIGIATE/STANDUP%20COMEDY/1%20(1).jpeg", "INTERCOLLIGIATE/STANDUP%20COMEDY/1%20(2).jpeg", "INTERCOLLIGIATE/STANDUP%20COMEDY/1%20(3).jpeg"};

            public static String sports = "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSLBmL1X5TwzWGt_VBvUPnNUbV_3VuLRE0P0Vq801BOvAU6hLu8JtW9sNaEUg";

            public static Integer[] intro_imgs = {

                    R.drawable.w_two_min,
                    R.drawable.w_three_min,
                    R.drawable.w_four_min,
                    R.drawable.w_one_min};
        }

    }

    public static List<String> toarray(String[] array) {
        return java.util.Arrays.asList(array);
    }

    public static List<Integer> toarray(Integer[] array) {
        return java.util.Arrays.asList(array);
    }


}

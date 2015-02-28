package com.hometeam.constant;

public class R {
    public static class MESSAGE {
        public static int MAX_LENGTH_MSG = 100;
        public static int MAX_LENGTH_TITLE = 30;
    }

    public static class LOGIN {
        public static int MAX_LENGTH_LOGIN = 30;
        public static int MIN_LENGTH_LOGIN = 3;

        public static int MAX_LENGTH_PASSWORD = 20;
        public static int MIN_LENGTH_PASSWORD = 4;
    }

    public static class SESSION {
        public static String USER = "user"; //User entity class
    }

    public static class SETTING {
        public static String NAME = "name";
        public static String CRYSTAL = "crystal"; //Количество кристалов
        public static String SLOT = "slot"; //Количество слотов(отображаемых контактов)
    }
}

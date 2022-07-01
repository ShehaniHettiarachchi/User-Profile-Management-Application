package Database;

import android.provider.BaseColumns;

public final class UserProfile {
        // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private.
        private UserProfile() {}

        /* Inner class that defines the table contents */
        public static class Users implements BaseColumns {
            public static final String TABLE_NAME = "Users";
            public static final String COLUMN_1 = "username";
            public static final String COLUMN_2 = "password";
            public static final String COLUMN_3 = "DateOfBirth";
            public static final String COLUMN_4 = "gender";
        }

}

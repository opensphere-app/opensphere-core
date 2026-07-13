package com.anish.opensphere.util;

public class Sql {

    private Sql() { }

    public static final class UserQueries {

        private UserQueries() { }

        public static final String GET_USER_BY_EMAIL = """
                SELECT id, name, email, password, active, created_by, created_at,
                updated_by, updated_at, principal_id FROM user WHERE email = ? AND active = ?
                """;

        public static final String CHECK_EMAIL_EXISTS = """
                SELECT COUNT(*) FROM user WHERE email = ? AND active = ?
                """;

        public static final String GET_USER_BY_PRINCIPAL_ID = """
                SELECT id, name, email, password, active, created_by,
                created_at, updated_by, updated_at, principal_id
                FROM user WHERE principal_id = ? AND active = ?
                """;

        public static final String CREATE_USER = """               
                INSERT INTO user (name, email, password, active, created_by, created_at,
                updated_by, updated_at, principal_id) VALUES
                (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        public static final String DELETE_USER = """
                UPDATE user SET active = ?, deleted_by = ?, deleted_at = ?
                WHERE id = ? RETURNING *
                """;

        public static final String GET_USERS = """
                
                """;
    }
}

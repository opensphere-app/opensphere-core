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
                RETURNING id
                """;

        public static final String DELETE_USER = """
                UPDATE user SET active = ?, deleted_by = ?, deleted_at = ?
                WHERE id = ? RETURNING *
                """;

        public static final String GET_USERS = """
                
                """;
    }

    public static final class PermissionQueries {

        private PermissionQueries() { }

        public static final String GET_PERMISSION_BY_ID = """
                SELECT id, name, description FROM permission
                WHERE id = ?
                """;
    }

    public static final class RoleQueries {

        private RoleQueries() { }

        public static final String GET_ROLE_BY_ID = """
                SELECT id, role_name, description
                FROM role
                WHERE id = ? AND deleted_by IS NULL
                """;

        public static final String GET_ROLES = """
                SELECT id, role_name, description
                FROM role
                WHERE deleted_by IS NULL
                """;

        public static final String GET_ROLE_BY_ROLE_NAME = """
                SELECT id, role_name, description
                FROM role
                WHERE role_name = ? AND deleted_by IS NULL
                """;

        public static final String CHECK_ROLE_EXISTS_BY_NAME = """
                SELECT COUNT(*) FROM role
                WHERE role_name = ? AND deleted_by IS NULL
                """;

        public static final String CHECK_ROLE_EXISTS_BY_ID = """
                SELECT COUNT(*) FROM role
                WHERE id = ? AND deleted_by IS NULL
                """;

        public static final String CREATE_ROLE = """
                INSERT INTO role (role_name, description, created_by, created_at, updated_by, updated_at)
                VALUES (?, ?, ?, ?, ?, ?)
                RETURNING id
                """;

        public static final String UPDATE_ROLE = """
                UPDATE role SET role_name = ?, description = ?, updated_by = ?, updated_at = ?
                WHERE id = ? AND deleted_by IS NULL
                """;

        public static final String DELETE_ROLE = """
                UPDATE role SET deleted_by = ?, deleted_at = ?
                WHERE id = ? AND deleted_by IS NULL
                RETURNING id, role_name, description
                """;
    }
}

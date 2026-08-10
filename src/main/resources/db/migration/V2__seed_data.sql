-- Seed principal

INSERT INTO principal (type, display_name, created_at, updated_at)
VALUES (
           'USER',
           'admin',
           (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT,
           (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT
       );

-- Seed roles

INSERT INTO "role" (role_name, description, created_at, updated_at)
VALUES
    (
         'ROLE_ADMIN',
         'Administrator with full access',
         (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT,
         (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT
    ),
    (
         'ROLE_MANAGER',
         'Manager with extended access',
         (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT,
         (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT
    ),
    (
          'ROLE_USER',
          'Standard user with limited access',
          (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT,
          (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT
    );

-- Seed permissions

INSERT INTO "permission" (permission_name, description)
VALUES
   ('USER_READ',   'Read user data'),
   ('USER_CREATE', 'Create new user'),
   ('USER_UPDATE', 'Update existing user'),
   ('USER_DELETE', 'Delete user'),
   ('ROLE_READ',   'Read role data'),
   ('ROLE_CREATE', 'Create new role'),
   ('ROLE_UPDATE', 'Update existing role'),
   ('ROLE_DELETE', 'Delete role');

-- Seed role-permission mapping

-- Grant all permissions to ROLE_ADMIN
INSERT INTO role_permission (
     role_id,
     permission_id,
     created_at,
     updated_at
)
SELECT
    r.id,
    p.id,
    (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT,
    (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT
FROM "role" r
         CROSS JOIN permission p
WHERE r.role_name = 'ROLE_ADMIN';

-- Grant selected permissions to ROLE_MANAGER
INSERT INTO role_permission (
    role_id,
    permission_id,
    created_at,
    updated_at
)
SELECT
    r.id,
    p.id,
    (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT,
    (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT
FROM "role" r
         JOIN permission p
              ON p.permission_name IN ('USER_READ', 'USER_CREATE', 'USER_UPDATE')
WHERE r.role_name = 'ROLE_MANAGER';

-- Grant USER_READ permission to ROLE_USER
INSERT INTO role_permission (
    role_id,
    permission_id,
    created_at,
    updated_at
)
SELECT
    r.id,
    p.id,
    (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT,
    (EXTRACT(EPOCH FROM CURRENT_TIMESTAMP) * 1000)::BIGINT
FROM "role" r
         JOIN permission p
              ON p.permission_name = 'USER_READ'
WHERE r.role_name = 'ROLE_USER';
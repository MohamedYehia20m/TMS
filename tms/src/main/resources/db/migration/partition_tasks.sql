-- Create the main tasks table
CREATE TABLE tasks (
                       id BIGSERIAL,
                       title VARCHAR(255) NOT NULL,
                       description TEXT,
                       workspace_id BIGINT NOT NULL,
                       status VARCHAR(50) NOT NULL,
                       PRIMARY KEY (id, workspace_id),
                       UNIQUE (title, workspace_id),
                       FOREIGN KEY (workspace_id) REFERENCES workspaces(id)
) PARTITION BY HASH (workspace_id);

-- Create 4 hash partitions
CREATE TABLE tasks_partition_1 PARTITION OF tasks FOR VALUES WITH (MODULUS 4, REMAINDER 0);
CREATE TABLE tasks_partition_2 PARTITION OF tasks FOR VALUES WITH (MODULUS 4, REMAINDER 1);
CREATE TABLE tasks_partition_3 PARTITION OF tasks FOR VALUES WITH (MODULUS 4, REMAINDER 2);
CREATE TABLE tasks_partition_4 PARTITION OF tasks FOR VALUES WITH (MODULUS 4, REMAINDER 3);

-- Insert data into the workspaces table
INSERT INTO workspaces (id, name,department) VALUES (1 ,'Workspace 1' ,'IT');
INSERT INTO workspaces (id, name,department) VALUES (2 ,'Workspace 2' ,'HR');
INSERT INTO workspaces (id, name,department) VALUES (3 ,'Workspace 3' ,'FINANCE');
INSERT INTO workspaces (id, name,department) VALUES (4 ,'Workspace 4' ,'MARKETING');

-- Insert data into the tasks table
INSERT INTO tasks (title, description, workspace_id, status) VALUES ('Task 1', 'Description 1', 1, 'PENDING');
INSERT INTO tasks (title, description, workspace_id, status) VALUES ('Task 2', 'Description 2', 2, 'PENDING');
INSERT INTO tasks (title, description, workspace_id, status) VALUES ('Task 3', 'Description 3', 3, 'PENDING');
INSERT INTO tasks (title, description, workspace_id, status) VALUES ('Task 4', 'Description 4', 4, 'PENDING');
INSERT INTO tasks (title, description, workspace_id, status) VALUES ('Task 5', 'Description 5', 1, 'PENDING');
INSERT INTO tasks (title, description, workspace_id, status) VALUES ('Task 6', 'Description 6', 2, 'PENDING');
INSERT INTO tasks (title, description, workspace_id, status) VALUES ('Task 7', 'Description 7', 3, 'PENDING');
INSERT INTO tasks (title, description, workspace_id, status) VALUES ('Task 8', 'Description 8', 4, 'PENDING');
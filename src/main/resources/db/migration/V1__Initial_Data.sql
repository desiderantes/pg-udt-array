CREATE TYPE CUSTOM_TYPE AS (first_field TEXT, second_field TEXT);

CREATE TABLE custom_table (
  table_name TEXT           NOT NULL,
  table_arr  CUSTOM_TYPE [] NOT NULL
);

CREATE OR REPLACE FUNCTION custom_func()
  RETURNS RECORD AS $$
DECLARE retval RECORD;
BEGIN
  SELECT *
  FROM custom_table
  LIMIT 1
  INTO retval;
  return retval;
end;
$$
language plpgsql;
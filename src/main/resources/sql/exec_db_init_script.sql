CREATE OR REPLACE FUNCTION create_execution_database()
    RETURNS void
    LANGUAGE plpgsql AS
$func$
BEGIN
    IF EXISTS(SELECT
              FROM pg_catalog.pg_tables
              WHERE schemaname = 'public'
                AND tablename = 'execution_table') THEN
        RAISE NOTICE 'Table execution_table already exists.';
    ELSE
        CREATE TABLE public.execution_table
        (
            customer_id   int8,
            customer_name varchar(100),
            contact_name  varchar(100),
            address       varchar(100),
            city          varchar(100),
            postal_code   varchar(100),
            country       varchar(100)
        );
        INSERT INTO public.execution_table
        VALUES (1, 'Alfreds Futterkiste', 'Maria Anders', 'Obere Str. 57', 'Berlin', '12209', 'Germany'),
               (2, 'Ana Trujillo Emparedados y helados', 'Ana Trujillo', 'Avda. de la Constitución 2222', 'México D.F.',
                '05021', 'Mexico'),
               (3, 'Antonio Moreno Taquería', 'Antonio Moreno', 'Mataderos 2312', 'México D.F.', '05023', 'Mexico'),
               (4, 'Around the Horn', 'Thomas Hardy', '120 Hanover Sq.', 'London', 'WA1 1DP', 'UK'),
               (5, 'Berglunds snabbköp', 'Christina Berglund', 'Berguvsvägen 8', 'Luleå', 'S-958 22', 'Sweden');

    END IF;
END
$func$;

SELECT create_execution_database();

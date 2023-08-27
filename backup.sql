--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1
-- Dumped by pg_dump version 15.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- Name: printer_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.printer_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.printer_sequence OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: printers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.printers (
    name character varying(100),
    ip character varying(100),
    id integer NOT NULL
);


ALTER TABLE public.printers OWNER TO postgres;

--
-- Name: printers_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.printers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.printers_id_seq OWNER TO postgres;

--
-- Name: printers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.printers_id_seq OWNED BY public.printers.id;


--
-- Name: user_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_sequence OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    username character varying(255),
    password character varying(100),
    id integer NOT NULL,
    role character varying(50),
    roles text[],
    accountid bigint
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: printers id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.printers ALTER COLUMN id SET DEFAULT nextval('public.printers_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: printers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.printers (name, ip, id) FROM stdin;
p1	h1	1
p2	h2	2
printer3	0.0.1	3
printer88	0.0.1.1	4
printer88	0.0.1.1	5
printer89	0.0.1	6
printer898	0.0.1	7
printadm	0.0.1	8
print_test	0.0.1	9
print_test1	0.0.1	10
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (username, password, id, role, roles, accountid) FROM stdin;
test	$2a$10$AqbCJDbs/yh7QUXa.vzbBucNGZ6FNHc1lfITFC6a8HNfocBZi1CDG	1	\N	{ROLE_USER,PRINT}	\N
test1	$2a$10$W.39hm1FaW8Evr1MENtZZe3oZHm2HZNh4SZyXLazV.uRkATCHyIoi	2	\N	{ROLE_USER,PRINT}	\N
testing	$2a$10$IeEQXH7FEa0ScKNiaVjEY.i1wMofOfJFOUbrOEAB4tSiqasGK1S.i	3	\N	{ROLE_USER,PRINT}	\N
sergi	$2a$10$./2evLFcHnsU.TO.dKNFiOYcj1JhnovPN/pA6Vn/DmmiPveQwOP9C	4	\N	{ROLE_USER,PRINT}	\N
user101	$2a$10$ru23FA3wvGg9yDdsMviMyuJFHDZkM0CyxMTD5wRJFLFDhXKVv/T9S	5	\N	{ROLE_USER,PRINT}	\N
testa	$2a$10$cO0ZqhEDyG1MWZrg67TyE.N6K4bAvzd9xom/YJsZBKgOflLqaF0.O	7	ROLE_SADMIN	{ROLE_USER,PRINT}	\N
sergio	$2a$10$Hla.5ZrM7ZTpUTjqMMrRnuDM607Uioh2JjgoGQHBl2gzengyl/E3W	8	\N	{ROLE_USER,PRINT}	\N
superadm	$2a$10$Yur5maxSRPQ3VBrsHUycQe9oc3IyazsNfvEDnwFFUzsH8Rlc64UgO	11	SUPERADMIN	{ROLE_USER,PRINT}	\N
tuser	$2a$10$iVYwbJi9SoktytHDlgP/JO0feVcy3V0emM1vx2W3ZxqMm.AeTL86m	12	USER	{ROLE_USER,PRINT}	\N
testinguser	$2a$10$b3/Vck/XyT5n0hRKbgnZtuxGVf77uTClHqW0ykvSeOhk1amlDXIr6	10	ROLE_ADMIN	{ROLE_ADMIN}	\N
testadm	$2a$10$1uNvoK9oA3nP2YgJElbV6u35JxL1DDEqDy0vbBqaPNarMruioMAl6	13	ROLE_ADMIN	{ROLE_ADMIN}	\N
testsa	$2a$10$kKsd5EsH2uvYek/.XPB2oeOYJIeLACTUDRxdlz1mXiYDpJhzYdqF2	6	ROLE_SUPERADMIN	{ROLE_SUPERADMIN}	\N
testuser	$2a$10$/NiKXjIv1Rcpp4rU5PypDOWGu8PrgYY4WSoylMVRnjbU4SskuhlMa	9	ROLE_USER	{ROLE_USER,PRINT}	100000009999
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 12, true);


--
-- Name: printer_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.printer_sequence', 1, false);


--
-- Name: printers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.printers_id_seq', 10, true);


--
-- Name: user_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_sequence', 1, false);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 13, true);


--
-- Name: printers printers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.printers
    ADD CONSTRAINT printers_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: pg_database_owner
--

GRANT USAGE ON SCHEMA public TO iprint;


--
-- PostgreSQL database dump complete
--


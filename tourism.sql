PGDMP      '                |            tourism    15.6    16.2 .    ,           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            -           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            .           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            /           1262    16398    tourism    DATABASE     |   CREATE DATABASE tourism WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Turkish_T�rkiye.1254';
    DROP DATABASE tourism;
                postgres    false            �            1259    16400    hotel    TABLE     �  CREATE TABLE public.hotel (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    address text NOT NULL,
    mail character varying(255) NOT NULL,
    phone character varying(255) NOT NULL,
    star character(1) NOT NULL,
    car_park boolean NOT NULL,
    wifi boolean NOT NULL,
    pool boolean NOT NULL,
    fitness boolean NOT NULL,
    concierge boolean NOT NULL,
    spa boolean NOT NULL,
    room_service boolean NOT NULL
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    16399    hotel_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotel_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.hotel_id_seq;
       public          postgres    false    215            0           0    0    hotel_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.hotel_id_seq OWNED BY public.hotel.id;
          public          postgres    false    214            �            1259    16409    hotel_pension    TABLE     �   CREATE TABLE public.hotel_pension (
    id integer NOT NULL,
    hotel_id integer NOT NULL,
    pension_type character varying(255) NOT NULL
);
 !   DROP TABLE public.hotel_pension;
       public         heap    postgres    false            �            1259    16408    hotel_pension_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotel_pension_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.hotel_pension_id_seq;
       public          postgres    false    217            1           0    0    hotel_pension_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.hotel_pension_id_seq OWNED BY public.hotel_pension.id;
          public          postgres    false    216            �            1259    16432    hotel_season    TABLE     �   CREATE TABLE public.hotel_season (
    id integer NOT NULL,
    hotel_id integer NOT NULL,
    start_date date NOT NULL,
    finish_date date NOT NULL
);
     DROP TABLE public.hotel_season;
       public         heap    postgres    false            �            1259    16431    hotel_season_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotel_season_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.hotel_season_id_seq;
       public          postgres    false    223            2           0    0    hotel_season_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.hotel_season_id_seq OWNED BY public.hotel_season.id;
          public          postgres    false    222            �            1259    16416    reservation    TABLE     �  CREATE TABLE public.reservation (
    id integer NOT NULL,
    room_id integer NOT NULL,
    check_in_date date NOT NULL,
    total_price numeric(10,2) NOT NULL,
    guest_count integer NOT NULL,
    guest_name character varying(255) NOT NULL,
    guest_citizen_id character varying(255) NOT NULL,
    guest_mail character varying(255) NOT NULL,
    guest_phone character varying(255) NOT NULL,
    check_out_date date NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    16415    reservation_id_seq    SEQUENCE     �   CREATE SEQUENCE public.reservation_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.reservation_id_seq;
       public          postgres    false    219            3           0    0    reservation_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.reservation_id_seq OWNED BY public.reservation.id;
          public          postgres    false    218            �            1259    16425    room    TABLE     �  CREATE TABLE public.room (
    id integer NOT NULL,
    hotel_id integer NOT NULL,
    pension_id integer NOT NULL,
    season_id integer NOT NULL,
    type character varying(255) NOT NULL,
    stock integer NOT NULL,
    adult_price numeric(10,2) NOT NULL,
    child_price numeric(10,2) NOT NULL,
    bed_capacity integer NOT NULL,
    square_meter numeric(10,2) NOT NULL,
    television boolean NOT NULL,
    minibar boolean NOT NULL,
    game_console boolean NOT NULL,
    projection boolean NOT NULL
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    16424    room_id_seq    SEQUENCE     �   CREATE SEQUENCE public.room_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.room_id_seq;
       public          postgres    false    221            4           0    0    room_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.room_id_seq OWNED BY public.room.id;
          public          postgres    false    220            �            1259    16455    user    TABLE     �   CREATE TABLE public."user" (
    user_id integer NOT NULL,
    user_name character varying(255) NOT NULL,
    user_pass character varying(255) NOT NULL,
    user_role character varying(255) NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    16454    user_user_id_seq    SEQUENCE     �   CREATE SEQUENCE public.user_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.user_user_id_seq;
       public          postgres    false    225            5           0    0    user_user_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.user_user_id_seq OWNED BY public."user".user_id;
          public          postgres    false    224            ~           2604    16403    hotel id    DEFAULT     d   ALTER TABLE ONLY public.hotel ALTER COLUMN id SET DEFAULT nextval('public.hotel_id_seq'::regclass);
 7   ALTER TABLE public.hotel ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    214    215                       2604    16412    hotel_pension id    DEFAULT     t   ALTER TABLE ONLY public.hotel_pension ALTER COLUMN id SET DEFAULT nextval('public.hotel_pension_id_seq'::regclass);
 ?   ALTER TABLE public.hotel_pension ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    216    217            �           2604    16435    hotel_season id    DEFAULT     r   ALTER TABLE ONLY public.hotel_season ALTER COLUMN id SET DEFAULT nextval('public.hotel_season_id_seq'::regclass);
 >   ALTER TABLE public.hotel_season ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    223    222    223            �           2604    16419    reservation id    DEFAULT     p   ALTER TABLE ONLY public.reservation ALTER COLUMN id SET DEFAULT nextval('public.reservation_id_seq'::regclass);
 =   ALTER TABLE public.reservation ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    219    219            �           2604    16428    room id    DEFAULT     b   ALTER TABLE ONLY public.room ALTER COLUMN id SET DEFAULT nextval('public.room_id_seq'::regclass);
 6   ALTER TABLE public.room ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    220    221            �           2604    16458    user user_id    DEFAULT     n   ALTER TABLE ONLY public."user" ALTER COLUMN user_id SET DEFAULT nextval('public.user_user_id_seq'::regclass);
 =   ALTER TABLE public."user" ALTER COLUMN user_id DROP DEFAULT;
       public          postgres    false    225    224    225                      0    16400    hotel 
   TABLE DATA           �   COPY public.hotel (id, name, address, mail, phone, star, car_park, wifi, pool, fitness, concierge, spa, room_service) FROM stdin;
    public          postgres    false    215   �4       !          0    16409    hotel_pension 
   TABLE DATA           C   COPY public.hotel_pension (id, hotel_id, pension_type) FROM stdin;
    public          postgres    false    217   &6       '          0    16432    hotel_season 
   TABLE DATA           M   COPY public.hotel_season (id, hotel_id, start_date, finish_date) FROM stdin;
    public          postgres    false    223   �6       #          0    16416    reservation 
   TABLE DATA           �   COPY public.reservation (id, room_id, check_in_date, total_price, guest_count, guest_name, guest_citizen_id, guest_mail, guest_phone, check_out_date) FROM stdin;
    public          postgres    false    219   �6       %          0    16425    room 
   TABLE DATA           �   COPY public.room (id, hotel_id, pension_id, season_id, type, stock, adult_price, child_price, bed_capacity, square_meter, television, minibar, game_console, projection) FROM stdin;
    public          postgres    false    221   x7       )          0    16455    user 
   TABLE DATA           J   COPY public."user" (user_id, user_name, user_pass, user_role) FROM stdin;
    public          postgres    false    225   8       6           0    0    hotel_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.hotel_id_seq', 19, true);
          public          postgres    false    214            7           0    0    hotel_pension_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.hotel_pension_id_seq', 9, true);
          public          postgres    false    216            8           0    0    hotel_season_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hotel_season_id_seq', 8, true);
          public          postgres    false    222            9           0    0    reservation_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.reservation_id_seq', 5, true);
          public          postgres    false    218            :           0    0    room_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.room_id_seq', 5, true);
          public          postgres    false    220            ;           0    0    user_user_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.user_user_id_seq', 11, true);
          public          postgres    false    224            �           2606    16414     hotel_pension hotel_pension_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.hotel_pension
    ADD CONSTRAINT hotel_pension_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.hotel_pension DROP CONSTRAINT hotel_pension_pkey;
       public            postgres    false    217            �           2606    16407    hotel hotel_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    215            �           2606    16437    hotel_season hotel_season_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.hotel_season
    ADD CONSTRAINT hotel_season_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.hotel_season DROP CONSTRAINT hotel_season_pkey;
       public            postgres    false    223            �           2606    16423    reservation reservation_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    219            �           2606    16430    room room_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    221            �           2606    16462    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    225               +  x����N�0��Ϟ�\y�DqS/�5"����l;@cג�C�4>�����n3����i/�/����0�U��L�g�ic��F��I~��B��CV���L��c��� \�a�v>���`J2�w���i�H$"��c�~T�-�/Gv��R��R*���\�η�E)V�d�T�Ul����AK��XDll�/ڲ�pL��-���MEu����q�2�sݸ�z�����[���ΰ|�5Y�WNٴl�\wS]hN��9�P'&OO��H���0!��
ߌ`Wk�Yo���a;$|�=��m:�I      !   q   x�3�4��))JT�H-R8:?�R�%1#3�ˈӐ�?%Q�;1�,1���F.N4SN#Nǜ���Ģ����Jsr��RS2K��8M��l�iƉ&d�i�� KN���qqq ��>�      '   ;   x�3�4�4202�50"(�L�؀ˌ���9�1�k�14�56��4�ʘ������ �$      #   v   x�M��	�0�s��%�I�7�)/��Zo��:�E�B�  $�����1�C�~I�H1���	ն��/G���=���R�����(�������ե6�A���|�O�Z)u�*�      %   �   x�e��
�0�ϳS6?���x
�������6�d�|;�� �k�����o<��*�V�mщ��$b���q|\os����(�V�*v����ſ��`�4�p�ҠI3�.�-�~+�j�zzjC_���|�ԉ�  :`      )   8   x�3�LL����4426�0��9�R�2K b��9����\f�ɩ�9�yh�1z\\\ ���     
PGDMP                      |            turizmacentesistemi    16.2    16.2 I               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            	           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            
           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    24984    turizmacentesistemi    DATABASE     �   CREATE DATABASE turizmacentesistemi WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
 #   DROP DATABASE turizmacentesistemi;
                postgres    false            �            1259    24985 
   guest_info    TABLE     )  CREATE TABLE public.guest_info (
    guest_id integer NOT NULL,
    reservations_id integer NOT NULL,
    full_name character varying(255) NOT NULL,
    national_number character varying(11) NOT NULL,
    country character varying(255) NOT NULL,
    guest_class character varying(255) NOT NULL
);
    DROP TABLE public.guest_info;
       public         heap    postgres    false            �            1259    24990    guest_info_guest_id_seq    SEQUENCE     �   CREATE SEQUENCE public.guest_info_guest_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.guest_info_guest_id_seq;
       public          postgres    false    215                       0    0    guest_info_guest_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.guest_info_guest_id_seq OWNED BY public.guest_info.guest_id;
          public          postgres    false    216            �            1259    24991    hotel_property    TABLE     �   CREATE TABLE public.hotel_property (
    property_id integer NOT NULL,
    property_names text[] NOT NULL,
    hotel_id integer
);
 "   DROP TABLE public.hotel_property;
       public         heap    postgres    false            �            1259    24996    hotel_property_property_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotel_property_property_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.hotel_property_property_id_seq;
       public          postgres    false    217                       0    0    hotel_property_property_id_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.hotel_property_property_id_seq OWNED BY public.hotel_property.property_id;
          public          postgres    false    218            �            1259    24997    hotel_seasons    TABLE     �   CREATE TABLE public.hotel_seasons (
    season_id integer NOT NULL,
    hotel_id integer NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    season_type character varying(20) NOT NULL
);
 !   DROP TABLE public.hotel_seasons;
       public         heap    postgres    false            �            1259    25000    hotel_seasons_season_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotel_seasons_season_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.hotel_seasons_season_id_seq;
       public          postgres    false    219                       0    0    hotel_seasons_season_id_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.hotel_seasons_season_id_seq OWNED BY public.hotel_seasons.season_id;
          public          postgres    false    220            �            1259    25001    hotels    TABLE     �  CREATE TABLE public.hotels (
    hotel_id integer NOT NULL,
    hotel_name character varying(255) NOT NULL,
    city character varying(100) NOT NULL,
    district character varying(100) NOT NULL,
    full_address text NOT NULL,
    email character varying(100),
    phone_number character varying(20),
    star integer,
    CONSTRAINT hotels_star_rating_check CHECK (((star >= 1) AND (star <= 5)))
);
    DROP TABLE public.hotels;
       public         heap    postgres    false            �            1259    25007    hotels_hotel_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotels_hotel_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.hotels_hotel_id_seq;
       public          postgres    false    221                       0    0    hotels_hotel_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.hotels_hotel_id_seq OWNED BY public.hotels.hotel_id;
          public          postgres    false    222            �            1259    25008    reservations    TABLE       CREATE TABLE public.reservations (
    id integer NOT NULL,
    room_id integer NOT NULL,
    reser_fll_name character varying(255) NOT NULL,
    reser_phone character varying(20) NOT NULL,
    reser_email character varying(255) NOT NULL,
    reser_note text,
    reser_check_in_date character varying(10) NOT NULL,
    reser_check_out_date character varying(10) NOT NULL,
    adult_numb character varying(10) NOT NULL,
    child_numb character varying(10) NOT NULL,
    total_price character varying(20) NOT NULL
);
     DROP TABLE public.reservations;
       public         heap    postgres    false            �            1259    25013    reservations_id_seq    SEQUENCE     �   CREATE SEQUENCE public.reservations_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.reservations_id_seq;
       public          postgres    false    223                       0    0    reservations_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.reservations_id_seq OWNED BY public.reservations.id;
          public          postgres    false    224            �            1259    25014    room    TABLE     1  CREATE TABLE public.room (
    id integer NOT NULL,
    room_type character varying(255) NOT NULL,
    stock integer NOT NULL,
    season_id integer,
    adult_price integer NOT NULL,
    child_price integer NOT NULL,
    type_id integer NOT NULL,
    hotel_id integer NOT NULL,
    room_price integer
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    25017    room_id_seq    SEQUENCE     �   CREATE SEQUENCE public.room_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.room_id_seq;
       public          postgres    false    225                       0    0    room_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.room_id_seq OWNED BY public.room.id;
          public          postgres    false    226            �            1259    25018    room_properties    TABLE     �   CREATE TABLE public.room_properties (
    property_id integer NOT NULL,
    property character varying(255) NOT NULL,
    room_id integer NOT NULL,
    area integer NOT NULL,
    adultd_bed_num integer,
    child_bed_num integer
);
 #   DROP TABLE public.room_properties;
       public         heap    postgres    false            �            1259    25021    room_properties_property_id_seq    SEQUENCE     �   CREATE SEQUENCE public.room_properties_property_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.room_properties_property_id_seq;
       public          postgres    false    227                       0    0    room_properties_property_id_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.room_properties_property_id_seq OWNED BY public.room_properties.property_id;
          public          postgres    false    228            �            1259    25022 
   type_hotel    TABLE     �   CREATE TABLE public.type_hotel (
    type_id integer NOT NULL,
    hotel_id integer NOT NULL,
    type_name character varying(255) NOT NULL
);
    DROP TABLE public.type_hotel;
       public         heap    postgres    false            �            1259    25025    type_hotel_type_id_seq    SEQUENCE     �   CREATE SEQUENCE public.type_hotel_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.type_hotel_type_id_seq;
       public          postgres    false    229                       0    0    type_hotel_type_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.type_hotel_type_id_seq OWNED BY public.type_hotel.type_id;
          public          postgres    false    230            �            1259    25026    user    TABLE     �   CREATE TABLE public."user" (
    user_id integer NOT NULL,
    user_name text NOT NULL,
    user_password text NOT NULL,
    user_role text NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    25031    user_user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    231            B           2604    25032    guest_info guest_id    DEFAULT     z   ALTER TABLE ONLY public.guest_info ALTER COLUMN guest_id SET DEFAULT nextval('public.guest_info_guest_id_seq'::regclass);
 B   ALTER TABLE public.guest_info ALTER COLUMN guest_id DROP DEFAULT;
       public          postgres    false    216    215            C           2604    25033    hotel_property property_id    DEFAULT     �   ALTER TABLE ONLY public.hotel_property ALTER COLUMN property_id SET DEFAULT nextval('public.hotel_property_property_id_seq'::regclass);
 I   ALTER TABLE public.hotel_property ALTER COLUMN property_id DROP DEFAULT;
       public          postgres    false    218    217            D           2604    25034    hotel_seasons season_id    DEFAULT     �   ALTER TABLE ONLY public.hotel_seasons ALTER COLUMN season_id SET DEFAULT nextval('public.hotel_seasons_season_id_seq'::regclass);
 F   ALTER TABLE public.hotel_seasons ALTER COLUMN season_id DROP DEFAULT;
       public          postgres    false    220    219            E           2604    25035    hotels hotel_id    DEFAULT     r   ALTER TABLE ONLY public.hotels ALTER COLUMN hotel_id SET DEFAULT nextval('public.hotels_hotel_id_seq'::regclass);
 >   ALTER TABLE public.hotels ALTER COLUMN hotel_id DROP DEFAULT;
       public          postgres    false    222    221            F           2604    25036    reservations id    DEFAULT     r   ALTER TABLE ONLY public.reservations ALTER COLUMN id SET DEFAULT nextval('public.reservations_id_seq'::regclass);
 >   ALTER TABLE public.reservations ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    224    223            G           2604    25037    room id    DEFAULT     b   ALTER TABLE ONLY public.room ALTER COLUMN id SET DEFAULT nextval('public.room_id_seq'::regclass);
 6   ALTER TABLE public.room ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    226    225            H           2604    25038    room_properties property_id    DEFAULT     �   ALTER TABLE ONLY public.room_properties ALTER COLUMN property_id SET DEFAULT nextval('public.room_properties_property_id_seq'::regclass);
 J   ALTER TABLE public.room_properties ALTER COLUMN property_id DROP DEFAULT;
       public          postgres    false    228    227            I           2604    25039    type_hotel type_id    DEFAULT     x   ALTER TABLE ONLY public.type_hotel ALTER COLUMN type_id SET DEFAULT nextval('public.type_hotel_type_id_seq'::regclass);
 A   ALTER TABLE public.type_hotel ALTER COLUMN type_id DROP DEFAULT;
       public          postgres    false    230    229            �          0    24985 
   guest_info 
   TABLE DATA           q   COPY public.guest_info (guest_id, reservations_id, full_name, national_number, country, guest_class) FROM stdin;
    public          postgres    false    215   Z       �          0    24991    hotel_property 
   TABLE DATA           O   COPY public.hotel_property (property_id, property_names, hotel_id) FROM stdin;
    public          postgres    false    217   [       �          0    24997    hotel_seasons 
   TABLE DATA           _   COPY public.hotel_seasons (season_id, hotel_id, start_date, end_date, season_type) FROM stdin;
    public          postgres    false    219   �[       �          0    25001    hotels 
   TABLE DATA           o   COPY public.hotels (hotel_id, hotel_name, city, district, full_address, email, phone_number, star) FROM stdin;
    public          postgres    false    221   ,\       �          0    25008    reservations 
   TABLE DATA           �   COPY public.reservations (id, room_id, reser_fll_name, reser_phone, reser_email, reser_note, reser_check_in_date, reser_check_out_date, adult_numb, child_numb, total_price) FROM stdin;
    public          postgres    false    223   ]       �          0    25014    room 
   TABLE DATA           x   COPY public.room (id, room_type, stock, season_id, adult_price, child_price, type_id, hotel_id, room_price) FROM stdin;
    public          postgres    false    225   �]                  0    25018    room_properties 
   TABLE DATA           n   COPY public.room_properties (property_id, property, room_id, area, adultd_bed_num, child_bed_num) FROM stdin;
    public          postgres    false    227   j^                 0    25022 
   type_hotel 
   TABLE DATA           B   COPY public.type_hotel (type_id, hotel_id, type_name) FROM stdin;
    public          postgres    false    229   _                 0    25026    user 
   TABLE DATA           N   COPY public."user" (user_id, user_name, user_password, user_role) FROM stdin;
    public          postgres    false    231   �_                  0    0    guest_info_guest_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.guest_info_guest_id_seq', 22, true);
          public          postgres    false    216                       0    0    hotel_property_property_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.hotel_property_property_id_seq', 34, true);
          public          postgres    false    218                       0    0    hotel_seasons_season_id_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.hotel_seasons_season_id_seq', 64, true);
          public          postgres    false    220                       0    0    hotels_hotel_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.hotels_hotel_id_seq', 43, true);
          public          postgres    false    222                       0    0    reservations_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.reservations_id_seq', 17, true);
          public          postgres    false    224                       0    0    room_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.room_id_seq', 21, true);
          public          postgres    false    226                       0    0    room_properties_property_id_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.room_properties_property_id_seq', 19, true);
          public          postgres    false    228                       0    0    type_hotel_type_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.type_hotel_type_id_seq', 291, true);
          public          postgres    false    230                       0    0    user_user_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.user_user_id_seq', 23, true);
          public          postgres    false    232            L           2606    25041    guest_info guest_info_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.guest_info
    ADD CONSTRAINT guest_info_pkey PRIMARY KEY (guest_id);
 D   ALTER TABLE ONLY public.guest_info DROP CONSTRAINT guest_info_pkey;
       public            postgres    false    215            N           2606    25043 "   hotel_property hotel_property_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY public.hotel_property
    ADD CONSTRAINT hotel_property_pkey PRIMARY KEY (property_id);
 L   ALTER TABLE ONLY public.hotel_property DROP CONSTRAINT hotel_property_pkey;
       public            postgres    false    217            P           2606    25045     hotel_seasons hotel_seasons_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY public.hotel_seasons
    ADD CONSTRAINT hotel_seasons_pkey PRIMARY KEY (season_id);
 J   ALTER TABLE ONLY public.hotel_seasons DROP CONSTRAINT hotel_seasons_pkey;
       public            postgres    false    219            R           2606    25047    hotels hotels_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.hotels
    ADD CONSTRAINT hotels_pkey PRIMARY KEY (hotel_id);
 <   ALTER TABLE ONLY public.hotels DROP CONSTRAINT hotels_pkey;
       public            postgres    false    221            T           2606    25049    reservations reservations_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.reservations
    ADD CONSTRAINT reservations_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.reservations DROP CONSTRAINT reservations_pkey;
       public            postgres    false    223            V           2606    25051    room room_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    225            X           2606    25053 $   room_properties room_properties_pkey 
   CONSTRAINT     k   ALTER TABLE ONLY public.room_properties
    ADD CONSTRAINT room_properties_pkey PRIMARY KEY (property_id);
 N   ALTER TABLE ONLY public.room_properties DROP CONSTRAINT room_properties_pkey;
       public            postgres    false    227            Z           2606    25055    type_hotel type_hotel_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.type_hotel
    ADD CONSTRAINT type_hotel_pkey PRIMARY KEY (type_id);
 D   ALTER TABLE ONLY public.type_hotel DROP CONSTRAINT type_hotel_pkey;
       public            postgres    false    229            \           2606    25057    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    231            _           2606    25058 '   hotel_seasons fk_hotel_seasons_hotel_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.hotel_seasons
    ADD CONSTRAINT fk_hotel_seasons_hotel_id FOREIGN KEY (hotel_id) REFERENCES public.hotels(hotel_id) ON DELETE CASCADE;
 Q   ALTER TABLE ONLY public.hotel_seasons DROP CONSTRAINT fk_hotel_seasons_hotel_id;
       public          postgres    false    221    4690    219            ]           2606    25063 *   guest_info guest_info_reservations_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.guest_info
    ADD CONSTRAINT guest_info_reservations_id_fkey FOREIGN KEY (reservations_id) REFERENCES public.reservations(id) ON DELETE CASCADE;
 T   ALTER TABLE ONLY public.guest_info DROP CONSTRAINT guest_info_reservations_id_fkey;
       public          postgres    false    223    215    4692            ^           2606    25068 +   hotel_property hotel_property_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.hotel_property
    ADD CONSTRAINT hotel_property_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotels(hotel_id) ON DELETE CASCADE;
 U   ALTER TABLE ONLY public.hotel_property DROP CONSTRAINT hotel_property_hotel_id_fkey;
       public          postgres    false    221    4690    217            `           2606    25073 &   reservations reservations_room_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.reservations
    ADD CONSTRAINT reservations_room_id_fkey FOREIGN KEY (room_id) REFERENCES public.room(id) ON DELETE CASCADE;
 P   ALTER TABLE ONLY public.reservations DROP CONSTRAINT reservations_room_id_fkey;
       public          postgres    false    223    4694    225            a           2606    25078    room room_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotels(hotel_id) ON DELETE CASCADE;
 A   ALTER TABLE ONLY public.room DROP CONSTRAINT room_hotel_id_fkey;
       public          postgres    false    4690    225    221            c           2606    25083 ,   room_properties room_properties_room_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.room_properties
    ADD CONSTRAINT room_properties_room_id_fkey FOREIGN KEY (room_id) REFERENCES public.room(id) ON DELETE CASCADE;
 V   ALTER TABLE ONLY public.room_properties DROP CONSTRAINT room_properties_room_id_fkey;
       public          postgres    false    227    4694    225            b           2606    25088    room room_season_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_season_id_fkey FOREIGN KEY (season_id) REFERENCES public.hotel_seasons(season_id);
 B   ALTER TABLE ONLY public.room DROP CONSTRAINT room_season_id_fkey;
       public          postgres    false    219    225    4688            d           2606    25093 #   type_hotel type_hotel_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.type_hotel
    ADD CONSTRAINT type_hotel_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotels(hotel_id) ON DELETE CASCADE;
 M   ALTER TABLE ONLY public.type_hotel DROP CONSTRAINT type_hotel_hotel_id_fkey;
       public          postgres    false    221    4690    229            �   �   x���=j�@���S�	B�WV�DƆ�ƨ	�Y��HH��T8g	�
I�ʝt�����	o`����1�c���i�7�]�-�r<wM}r��C���?��Z��c;�
\̺0�dXWG��{�H�H�зU�Z�����4v�x%���������Y�u�7���xqM ��P8nB��\֛�3`�C���P��������
�ο����A�U�����N�
feIP���xE�m�{}      �   �   x�3�V:<'�(��8�J��$� �([��ӄ���T���V9$��L�L�@��=U��
�e�U�@�[fI^jq��sj^IjP�#�$5G�9?/93�(=UI'8�QG�\��D�?%Q!8��,�8h��%��9��b��1ج5����� ��d�      �   c   x�u�!�0Eu{���[�v�@A��l��ͽ���/"(k$Qb�h�J�a=�{�P桱<�6�B�N�4�=b���*�h�����X�G^�&D|,�,      �   �   x�]�K
�@�יS� R���VEAE���h�:����x{03ւȟ�$̟/Ӈ-�\V	.�INC�"��.*"e�����qR�v�{�Q�k=	��C�⢣[��Z�s�9��b{8���`dF�a4��Pq�+��M����:嵹����c��9�/�����
B`��R���
�M�@ܶ��zm�@+&!��b7      �   �   x���1
�@E��Sl-f���n,-,���6C�D�H�y<�w���(e~1���Rh�����*�}�<�U-Ǔ)�@��O,Z��j����Q�k�"�V�N�:�0܊�NC1�K8�\�O��;Aj27qy�n.]/��գ'�ԱOكTe�ް������m��t;J��|��e�PF���]*��7�58I����d�      �   ~   x�]��
BA���)z�PGg�}Op۶
"�jV�J�3w!�|���x�n��2�wTf�(��t�0��&g�K%Mr`��������˸���q�����?�2�&��7VW9�����9��,Z:n����2          �   x�34��I�I-ˬ���S������LJ,���9�9M8�8��p+��,�S���+��)�h��(��J�.���44�b2Ŕ���F ���(���n�)朘�X�-1�4�21���.5��:F��� ��Z         �   x�u�?
�0��9EN &͟v

�Ppy؂���X�^FG��z/�&��Ϗ�i��h:4+-�<ʞ��RrS��� ���L7�g��4��oG���8L�㱕�[C3���E��Q����-��@�%��7�D����<�4�j¥{����
�@5�B&�~�Lт
�D�+C��� �|�#��         +   x�34�L�-�ɯLM�4426��9Sr3� �`&W� | �     
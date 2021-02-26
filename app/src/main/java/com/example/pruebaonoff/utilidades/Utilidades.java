package com.example.pruebaonoff.utilidades;

public class Utilidades {

    public static final String TABLA_BLOG = "Blog";
    public static final String ID_POST = "id";
    public static final String ID_USUARIO = "userId";
    public static final String TITLE = "title";
    public static final String BODY = "body";
    public static final String CREAR_TABLA_BLOG = "CREATE TABLE " + TABLA_BLOG +
            " (" + ID_POST + " INT,  " + ID_USUARIO + " INT," +
            TITLE + " TEXT, " + BODY + " TEXT)";

}

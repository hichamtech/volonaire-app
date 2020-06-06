package com.example.emploiandroid.Models;

public class URLs

{


    public  final String BASE_URL="http://192.168.1.12:8000";
    public  final String URL_LOGIN=BASE_URL+"/api/login_check";
    public  final String URL_LISTEVOLENTAIRE =BASE_URL+"/api/personnes?roles=ROLE_VOLENTAIRE";
    public  final String URL_LISTEDEMANDEUR =BASE_URL+"/api/personnes?roles=ROLE_DEMANDEUR";

    public  final String URL_BASE_PERSONNE=BASE_URL+"/api/personnes/";







}

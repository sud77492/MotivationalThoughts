package actiknow.com.motivationalthoughts.utils;

public class AppConfigURL {
    //public static String BASE_URL = "https://cloud-photo-sudhanshu77492652.c9users.io/";
    public static String BASE_URL = "http://meningococcal-conso.000webhostapp.com/api_motivation/";
 //   public static String BASE_URL = "http://clearsale.com/";
 //   https://cloud-photo-sudhanshu77492652.c9users.io/api_motivation/v1/getlist
    
    public static String URL_ENGLISHQUOTES = BASE_URL + "api_motivation/v1/getlist";
    public static String URL_QUOTES = BASE_URL + "api_motivation/v1/getquote";
    public static String URL_QUOTES2 = "http://10.0.2.2:5000/thoughts";
    public static String URL_UPDATE_STATUS = "http://10.0.2.2:5000/update_status";
    public static String URL_FIREBASE2 = "http://10.0.2.2:5000/firebase_id";
    public static String URL_FIREBASE = BASE_URL + "api_motivation/v1/firebase_id";

    public static String URL_LINKEDIN_AUTH = "https://api.linkedin.com/v1/people/~:(email-address,formatted-name,phone-numbers,picture-urls::(original))";
}
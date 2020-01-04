package actiknow.com.motivationalthoughts.model;

import java.util.ArrayList;

/**
 * Created by actiknow on 6/22/17.
 */

public class QuotesEnglish {
    int id;
    String quotesEnglish;

    public QuotesEnglish(int id, String quotesEnglish){
        this.id = id;
        this.quotesEnglish = quotesEnglish;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuotesEnglish() {
        return quotesEnglish;
    }

    public void setQuotesEnglish(String quotesEnglish) {
        this.quotesEnglish = quotesEnglish;
    }


}

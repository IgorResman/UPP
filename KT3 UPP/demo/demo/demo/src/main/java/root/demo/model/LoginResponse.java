package root.demo.model;

import java.util.List;

import org.camunda.bpm.engine.form.FormField;

public class LoginResponse {

    String uloga;
    String poruka;


    public LoginResponse(String uloga, String poruka) {
        this.uloga = uloga;
        this.poruka = poruka;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }
}
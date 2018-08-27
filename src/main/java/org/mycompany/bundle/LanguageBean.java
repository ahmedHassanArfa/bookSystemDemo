package org.mycompany.bundle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.faces.context.FacesContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value = "languageBean")
@Scope(value = "session")
public class LanguageBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String localeCode;

	private static List<String> languages;

    public List<String> getLanguages() {
        if (languages == null) {
            languages = new ArrayList<String>();
            languages.add("ar");
            languages.add("en");
        }
        return languages;
    }

    public String getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    //value change event listener
    public void localeCodeChanged() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(localeCode));
    }

}

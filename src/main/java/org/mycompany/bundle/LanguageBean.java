package org.mycompany.bundle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="languageBean")
@SessionScoped
public class LanguageBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static List<String> languages;

    public List<String> getLanguages() {
        if (languages == null) {
            languages = new ArrayList<String>();
            languages.add("ar");
            languages.add("en");
        }
        return languages;
    }


    //value change event listener
    public void localeCodeChanged(String locale) {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(locale));
    }

}

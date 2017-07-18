package org.pis.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.*;
//BEGIN
//DECLARE msg VARCHAR(255);
//IF NEW.Rodne_cislo NOT REGEXP '^[[:digit:]]{6}/[[:digit:]]{4}$' THEN
//      set msg = "Toto neni platne rodne cislo";
//      SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
//END IF; 
//END

@FacesValidator("org.pis.util.BirthCodeValidator")
public class BirthCodeValidator implements Validator {

	@Override
	public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {
		Pattern p = Pattern.compile("^[\\d]{6}/[\\d]{4}$");

		String birthCode = value.toString();
		Matcher m = p.matcher(birthCode);
		if (!m.matches()) {
			FacesMessage msg = new FacesMessage("Invalid birth code");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
}

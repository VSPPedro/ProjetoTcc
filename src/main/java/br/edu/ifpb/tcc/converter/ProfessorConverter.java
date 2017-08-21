package br.edu.ifpb.tcc.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import br.edu.ifpb.tcc.entity.Professor;

@FacesConverter("ProfessorConverter")
public class ProfessorConverter  extends SelectItemsBaseConverter{

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof Professor)
			return ((Professor) value).getId().toString();
		else
			return null;
	}

}

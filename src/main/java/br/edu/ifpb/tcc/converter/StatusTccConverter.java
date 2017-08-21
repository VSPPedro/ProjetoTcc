package br.edu.ifpb.tcc.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import br.edu.ifpb.tcc.entity.StatusTcc;

@FacesConverter("StatusTccConverter")
public class StatusTccConverter extends SelectItemsBaseConverter {
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof StatusTcc)
			return ((StatusTcc) value).getNome();
		else
			return null;
	}
}

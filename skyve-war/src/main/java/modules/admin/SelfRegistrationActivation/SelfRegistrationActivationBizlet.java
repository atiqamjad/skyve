package modules.admin.SelfRegistrationActivation;

import org.skyve.EXT;
import org.skyve.domain.Bean;
import org.skyve.impl.web.HttpServletRequestResponse;
import org.skyve.metadata.controller.ImplicitActionName;
import org.skyve.metadata.model.document.Bizlet;
import org.skyve.web.WebContext;

import jakarta.servlet.http.HttpServletRequest;

public class SelfRegistrationActivationBizlet extends Bizlet<SelfRegistrationActivationExtension> {
	@Override
	public SelfRegistrationActivationExtension preExecute(ImplicitActionName actionName, SelfRegistrationActivationExtension bean, Bean parentBean, WebContext webContext)
			throws Exception {
		if (ImplicitActionName.New.equals(actionName)) {
			HttpServletRequestResponse requestResponse = EXT.getHttpServletRequestResponse();
			HttpServletRequest request = null;
			if (requestResponse != null) {
				request = requestResponse.getRequest();
			}
			if (request == null) {
				throw new IllegalStateException("HttpServletRequest is null");
			}
			String activationCode = request.getParameter("code");

			bean.setUser(bean.activateUser(activationCode));
		}
		return bean;
	}
}

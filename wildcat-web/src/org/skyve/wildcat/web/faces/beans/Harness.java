package org.skyve.wildcat.web.faces.beans;

import java.io.Serializable;
import java.util.Set;

import org.skyve.metadata.MetaDataException;
import org.skyve.metadata.customer.Customer;
import org.skyve.metadata.module.Module;
import org.skyve.metadata.module.menu.MenuItem;
import org.skyve.metadata.module.query.DocumentQueryDefinition;
import org.skyve.metadata.view.View.ViewType;
import org.skyve.web.WebAction;
import org.skyve.wildcat.domain.messages.SecurityException;
import org.skyve.wildcat.metadata.user.UserImpl;
import org.skyve.wildcat.util.UtilImpl;

public abstract class Harness implements Serializable {
	private static final long serialVersionUID = 2805839690076647L;

	private String logoRelativeFileNameUrl;
	public final String getLogoRelativeFileNameUrl() {
		return logoRelativeFileNameUrl;
	}

	private String cssRelativeFileNameUrl;
	public String getCssRelativeFileNameUrl() {
		return cssRelativeFileNameUrl;
	}
	
	private String bizModuleParameter;
	public String getBizModuleParameter() {
		return bizModuleParameter;
	}
	public void setBizModuleParameter(String bizModuleParameter) {
		this.bizModuleParameter = bizModuleParameter;
	}

	private String bizDocumentParameter;
	public String getBizDocumentParameter() {
		return bizDocumentParameter;
	}
	public void setBizDocumentParameter(String bizDocumentParameter) {
		this.bizDocumentParameter = bizDocumentParameter;
	}
	
	private String bizIdParameter;
	public String getBizIdParameter() {
		return bizIdParameter;
	}
	public void setBizIdParameter(String bizIdParameter) {
		this.bizIdParameter = bizIdParameter;
	}

	private WebAction webActionParameter;
	public WebAction getWebActionParameter() {
		return webActionParameter;
	}
	public void setWebActionParameter(WebAction webActionParameter) {
		this.webActionParameter = webActionParameter;
	}

	private ViewType viewType;
	public ViewType getViewType() {
		return viewType;
	}

	@SuppressWarnings("static-method")
	public final String getWildcatVersionComment() {
		StringBuilder result = new StringBuilder(64);
		result.append("<!-- WILDCAT FRAMEWORK version is ").append(UtilImpl.WILDCAT_VERSION).append(" -->");
		return result.toString();
	}
	
	@SuppressWarnings("static-method")
	public final String getJavascriptFileVersion() {
		return UtilImpl.JAVASCRIPT_FILE_VERSION;
	}
	
	@SuppressWarnings("static-method")
	public final String getBaseHref() {
		return org.skyve.util.Util.getWildcatContextUrl() + '/';
	}
	
	public final void initialise(Customer customer, UserImpl user)
	throws MetaDataException, SecurityException {
		StringBuilder sb = new StringBuilder(64);
		sb.append("resources?_n=");
		sb.append(customer.getUiResources().getLogoRelativeFileName());
		logoRelativeFileNameUrl = sb.toString();

		if (bizModuleParameter == null) {
			Set<String> moduleNames = user.getAccessibleModuleNames();
			if (moduleNames.size() == 0) {
				throw new SecurityException("any module", customer.getName() + '/' + user.getName());
			}
			Module homeModule = null;
			bizModuleParameter = user.getHomeModuleName();
			if (bizModuleParameter != null) {
				if (moduleNames.contains(bizModuleParameter)) {
					homeModule = customer.getModule(bizModuleParameter);
				}
			}
			if (homeModule == null) {
				homeModule = customer.getHomeModule();
				bizModuleParameter = homeModule.getName();
				if (! moduleNames.contains(bizModuleParameter)) {
					homeModule = null;
				}
			}
			if (homeModule == null) {
				bizModuleParameter = moduleNames.iterator().next();
				homeModule = customer.getModule(bizModuleParameter);
			}
			bizDocumentParameter = homeModule.getHomeDocumentName();

			viewType = homeModule.getHomeRef();
		}
		
		String cssRelativeFileName = customer.getHtmlResources().getCssRelativeFileName();
		if (cssRelativeFileName != null) {
			sb.setLength(0);
			sb.append("resources?_n=").append(cssRelativeFileName);
			cssRelativeFileNameUrl = sb.toString();
		}
		else {
			sb.setLength(0);
			sb.append("css/basic-min.css?v=").append(UtilImpl.JAVASCRIPT_FILE_VERSION);
			cssRelativeFileNameUrl = sb.toString();
		}
	}
	
	public static DocumentQueryDefinition deriveDocumentQuery(Customer customer,
																Module module,
																MenuItem item,
																String queryName,
																String documentName)
	throws MetaDataException {
        DocumentQueryDefinition query = null;
		if (queryName != null) {
            query = module.getDocumentQuery(queryName);
            if ((query == null) || (query.getName() == null)) {
                MetaDataException me = new MetaDataException("The target query " + queryName + " for menu action " +
                                                                item.getName() + " is invalid in module " + module.getName());
                throw me;
            }
        }
        else {
            query = module.getDocumentDefaultQuery(customer, documentName);
            if ((query == null) || (query.getName() == null)) {
                MetaDataException me = new MetaDataException("The target document " + documentName + " for menu action " +
                                                                item.getName() + " has no default query in module " + module.getName());
                throw me;
            }
        }

        return query;
	}
}

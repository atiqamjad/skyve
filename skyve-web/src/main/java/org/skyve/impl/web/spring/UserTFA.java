package org.skyve.impl.web.spring;

import java.util.Collection;

import org.skyve.domain.types.DateTime;
import org.skyve.impl.util.UtilImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserTFA extends User {
	private static final long serialVersionUID = 2127717918658548938L;
	
	private String tfaCode;
	private String tfaToken;
	private DateTime tfaCodeGeneratedDateTime;
	private String customer;
	private String user;
	private String email;

	public UserTFA(String username,
					String password,
					boolean enabled,
					boolean accountNonExpired,
					boolean credentialsNonExpired,
					boolean accountNonLocked,
					Collection<? extends GrantedAuthority> authorities,
					String customer,
					String user,
					String tfaCode,
					String tfaToken,
					DateTime tfaCodeGeneratedDateTime,
					String email ) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.tfaCode = UtilImpl.processStringValue(tfaCode);
		this.tfaToken = UtilImpl.processStringValue(tfaToken);
		this.tfaCodeGeneratedDateTime = tfaCodeGeneratedDateTime;
		this.customer = UtilImpl.processStringValue(customer);
		this.user = UtilImpl.processStringValue(user);
		this.email = UtilImpl.processStringValue(email);
	}

	public String getTfaCode() {
		return tfaCode;
	}

	public void setTfaCode(String tfaCode) {
		this.tfaCode = UtilImpl.processStringValue(tfaCode);
	}

	public String getTfaToken() {
		return tfaToken;
	}

	public void setTfaToken(String tfaToken) {
		this.tfaToken = UtilImpl.processStringValue(tfaToken);
	}

	public DateTime getTfaCodeGeneratedDateTime() {
		return tfaCodeGeneratedDateTime;
	}

	public void setTfaCodeGeneratedDateTime(DateTime tfaCodeGeneratedDateTime) {
		this.tfaCodeGeneratedDateTime = tfaCodeGeneratedDateTime;
	}

	public String getCustomer() {
		return customer;
	}

	public String getUser() {
		return user;
	}

	public String getEmail() {
		return email;
	}
}

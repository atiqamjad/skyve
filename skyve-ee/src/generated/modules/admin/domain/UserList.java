package modules.admin.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.impl.domain.AbstractTransientBean;

/**
 * Users
 * 
 * @navhas n userInvitationGroups 0..n Group
 * @stereotype "transient"
 */
@XmlType
@XmlRootElement
public class UserList extends AbstractTransientBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "admin";
	/** @hidden */
	public static final String DOCUMENT_NAME = "UserList";

	/** @hidden */
	public static final String userInvitationGroupsPropertyName = "userInvitationGroups";
	/** @hidden */
	public static final String userInvitiationEmailListPropertyName = "userInvitiationEmailList";

	/**
	 * User Invitation Groups
	 * <br/>
	 * The collection of groups that invited users are assigned.
	 **/
	private List<Group> userInvitationGroups = new ArrayList<>();
	/**
	 * Invitation email addresses
	 * <br/>
	 * The list of emails for users to invite. 
			<br/>
			Users will be created with the email address as username with the assigned groups.
			<br/>
			Provide a list separated by either comma or semicolon.
	 **/
	private String userInvitiationEmailList;

	@Override
	@XmlTransient
	public String getBizModule() {
		return UserList.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return UserList.DOCUMENT_NAME;
	}

	public static UserList newInstance() {
		try {
			return CORE.getUser().getCustomer().getModule(MODULE_NAME).getDocument(CORE.getUser().getCustomer(), DOCUMENT_NAME).newInstance(CORE.getUser());
		}
		catch (RuntimeException e) {
			throw e;
		}
		catch (Exception e) {
			throw new DomainException(e);
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof UserList) && 
					this.getBizId().equals(((UserList) o).getBizId()));
	}

	/**
	 * {@link #userInvitationGroups} accessor.
	 * @return	The value.
	 **/
	@XmlElement
	public List<Group> getUserInvitationGroups() {
		return userInvitationGroups;
	}

	/**
	 * {@link #userInvitationGroups} accessor.
	 * @param bizId	The bizId of the element in the list.
	 * @return	The value of the element in the list.
	 **/
	public Group getUserInvitationGroupsElementById(String bizId) {
		return getElementById(userInvitationGroups, bizId);
	}

	/**
	 * {@link #userInvitationGroups} mutator.
	 * @param bizId	The bizId of the element in the list.
	 * @param element	The new value of the element in the list.
	 **/
	public void setUserInvitationGroupsElementById(String bizId, Group element) {
		 setElementById(userInvitationGroups, element);
	}

	/**
	 * {@link #userInvitiationEmailList} accessor.
	 * @return	The value.
	 **/
	public String getUserInvitiationEmailList() {
		return userInvitiationEmailList;
	}

	/**
	 * {@link #userInvitiationEmailList} mutator.
	 * @param userInvitiationEmailList	The new value.
	 **/
	@XmlElement
	public void setUserInvitiationEmailList(String userInvitiationEmailList) {
		preset(userInvitiationEmailListPropertyName, userInvitiationEmailList);
		this.userInvitiationEmailList = userInvitiationEmailList;
	}
}

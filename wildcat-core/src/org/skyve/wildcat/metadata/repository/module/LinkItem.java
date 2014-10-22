package org.skyve.wildcat.metadata.repository.module;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.skyve.wildcat.util.UtilImpl;
import org.skyve.wildcat.util.XMLUtil;

@XmlType(name = "link", namespace = XMLUtil.MODULE_NAMESPACE)
@XmlRootElement(name = "link", namespace = XMLUtil.MODULE_NAMESPACE)
public class LinkItem extends Item {
	private String href;

	public String getHref() {
		return href;
	}
	@XmlAttribute(name = "href", required = true)
	public void setHref(String href) {
		this.href = UtilImpl.processStringValue(href);
	}
}

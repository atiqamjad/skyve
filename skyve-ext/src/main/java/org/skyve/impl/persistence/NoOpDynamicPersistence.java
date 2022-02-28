package org.skyve.impl.persistence;

import java.util.Set;

import org.skyve.domain.Bean;
import org.skyve.domain.PersistentBean;
import org.skyve.impl.metadata.customer.ExportedReference;
import org.skyve.metadata.customer.Customer;
import org.skyve.metadata.model.document.Document;
import org.skyve.metadata.module.Module;
import org.skyve.persistence.DynamicPersistence;

public class NoOpDynamicPersistence implements DynamicPersistence {
	private static final long serialVersionUID = 2810341864186864562L;

	@Override
	public void persist(Customer customer, Module module, Document document, PersistentBean bean) {
		// No-op
	}

	@Override
	public void delete(Customer customer, Document document, PersistentBean bean) {
		// No-op
	}

	@Override
	public Bean retrieve(Customer customer, Module module, Document document, String bizId) {
		// No-op
		return null;
	}

	@Override
	public boolean hasReferentialIntegrity(Document documentToDelete,
											PersistentBean beanToDelete,
											ExportedReference exportedReference,
											Document referenceDocument,
											Set<Bean> beansToBeExcluded) {
		// No-op
		return false;
	}
}

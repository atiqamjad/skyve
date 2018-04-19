package modules.admin.ReportDesign.actions;

import org.skyve.impl.generate.jasperreports.*;
import org.skyve.metadata.controller.ServerSideAction;
import org.skyve.metadata.controller.ServerSideActionResult;
import org.skyve.web.WebContext;

import modules.admin.ReportDesign.ReportDesignBizlet;
import modules.admin.domain.ReportDesign;

public class GenerateDefault implements ServerSideAction<ReportDesign> {

	private static final long serialVersionUID = -8203773871581974793L;

	@Override
	public ServerSideActionResult<ReportDesign> execute(ReportDesign bean, WebContext webContext) throws Exception {

		final DesignSpecification designSpecification = ReportDesignBizlet.specificationFromDesignBean(bean);
		final ReportDesignGenerator generator = new ReportDesignGeneratorFactory()
				.getGeneratorForDesign(designSpecification);

		generator.populateDesign(designSpecification);

		final JasperReportRenderer reportRenderer = new JasperReportRenderer(designSpecification);

		bean.setJrxml(reportRenderer.getJrxml());
		if (StringUtils.isNotBlank(bean.getRepositoryPath())) {
			Renderer.saveJrxml(designSpecification, reportRenderer);
		}

		return new ServerSideActionResult<>(bean);
	}

}
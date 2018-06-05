package sail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.UseParametersRunnerFactory;

import util.JUnitMultiTheadedRunnerParameterizedRunnerFactory;

@RunWith(Parameterized.class)
@UseParametersRunnerFactory(JUnitMultiTheadedRunnerParameterizedRunnerFactory.class)
public class AdminSailParameterisedPerformanceTest extends AdminSailTest {
	@Parameters
	public static String[][] params() {
		return new String[][] {{"demo", "admin", "admin"}};
	}

	@Parameter(0)
	public String customer = "demo";

	@Parameter(1)
	public String user = "admin";
	
	@Parameter(2)
	public String password = "admin";
	
	@Test
	public void test() throws Exception {
		login(customer, user, password);
		testMenuPassword();
		testMenuUserDashboard();
		testMenuContacts();
		testMenuCommunications();
		testMenuSecurityAdminGroups();
		testMenuSecurityAdminDataGroups();
		testMenuDevOpsDataMaintenance();
		testMenuDevOpsDocumentCreator();
		testMenuSnapshots();
		testMenuSystemDashboard();
		testMenuDocumentNumbers();
		testMenuJobs();
	}
}

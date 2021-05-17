package modules.admin.Configuration;

import java.util.List;

import org.skyve.job.Job;

import modules.admin.Communication.CommunicationUtil;
import modules.admin.Communication.CommunicationUtil.ResponseMode;
import modules.admin.Communication.CommunicationUtil.RunMode;
import modules.admin.domain.Communication;
import modules.admin.domain.Configuration;
import modules.admin.domain.Generic;

public class AvailableDiskSpaceAlarmJob extends Job {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2168075392920819080L;
	private static final int _DEFAULT_AVAILABLE_DISK_SPACE_ALARM_LEVEL_PERCENTAGE = 10;
	
	private static final String AVAILABLE_DISK_SPACE_ALARM_NOFITICATION = "Available Disk Space Alarm Notification";
	private static final String AVAILABLE_DISK_SPACE_ALARM_DEFAULT_SEND_TO = "{startup.environmentSupportEmail}";
	private static final String AVAILABLE_DISK_SPACE_ALARM_DEFAULT_SUBJECT = "Disk space notification for {#context}";
	private static final String AVAILABLE_DISK_SPACE_ALARM_DEFAULT_BODY = "<p>Available disk space has fallen below the alarm level:</p><p>{markup1}</p>";

	@Override
	public String cancel() {
		return null;
	}

	/**
	 * The AvailableDiskSpaceAlarmJob checks the available disk space and sends a
	 * Communication notification if the available disk space is below the value set
	 * in Configuration.availableDiskSpaceAlarmLevel
	 * 
	 * If no value has been set and the job is configured to run, the default alarm
	 * level will be 10%
	 */
	@Override
	public void execute() throws Exception {

		List<String> log = getLog();


		// evaluate whether alarm should be sent
		ConfigurationExtension configuration = Configuration.newInstance();
		Generic diskSpaceSummary = configuration.diskSpaceSummary();
		log.add(diskSpaceSummary.getMarkup1());

		Integer percentageLevel = configuration.getAvailableDiskSpaceAlarmLevelPercentage();
		Long levelMB = configuration.getAvailableDiskSpaceAlarmLevelMB();

		// DEFAULT if level has not been set
		if (percentageLevel == null) {
			percentageLevel = _DEFAULT_AVAILABLE_DISK_SPACE_ALARM_LEVEL_PERCENTAGE;
		}
		if (diskSpaceSummary.getLongInteger3() <= percentageLevel || (levelMB != null && diskSpaceSummary.getInteger1() <= levelMB)) {
			Communication communication = CommunicationUtil.initialiseSystemCommunication(AVAILABLE_DISK_SPACE_ALARM_NOFITICATION, AVAILABLE_DISK_SPACE_ALARM_DEFAULT_SEND_TO, null, AVAILABLE_DISK_SPACE_ALARM_DEFAULT_SUBJECT, AVAILABLE_DISK_SPACE_ALARM_DEFAULT_BODY);
			CommunicationUtil.send(communication, RunMode.ACTION, ResponseMode.SILENT, null, configuration, diskSpaceSummary);
		}

		setPercentComplete(100);
	}

}

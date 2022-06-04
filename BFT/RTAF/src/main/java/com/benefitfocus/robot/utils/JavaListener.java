package com.benefitfocus.robot.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.robotframework.javalib.annotation.Autowired;

public class JavaListener {

	// Global variables
	public static final int ROBOT_LISTENER_API_VERSION = 2;
	public static final String DEFAULT_FILENAME = "listen_java.txt";
	private BufferedWriter outfile = null;
	public String relPath = getClass().getProtectionDomain().getCodeSource()
			.getLocation().getFile();
	public String folder = relPath + "../../target/robot/screenshots";

	public static Map<String, String> hRobotTestDetails = new HashMap<String, String>();

	public JavaListener() {
		this(DEFAULT_FILENAME);
	}	
	/**
	 * CustomException class constructor
	 * 
	 * @param message
	 *            - String argument to show the custom message
	 */
	public JavaListener(String filename) {
		System.getProperty("java.io.tmpdir");
		System.getProperty("file.separator");
		String outpath = System.getProperty("user.dir")+System.getProperty("file.separator")+"listen_java.txt";
		try {
			this.outfile = new BufferedWriter(new FileWriter(outpath));

			File targetDir = new File(folder);
			if (!targetDir.exists()) {
				targetDir.mkdir();
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startSuite(String name, Map attrs) throws IOException {

		System.out.println("Executing the testSuite : " + name);
		//ReadJsonTestData.getSuiteTestData(name.substring(name.lastIndexOf(".")).replace(" ", ""));
		//if(name.toString().lastIndexOf(".")!= -1)
		hRobotTestDetails.put("currentSuiteName",name.replace(" ", ""));
		//hRobotTestDetails.put("currentSuiteName",name.substring(name.lastIndexOf(".")).replace(" ", ""));

		folder = folder + "/" + name;

		Map metadata = (Map) attrs.get("metadata");
		String metastr = getMetaString(metadata);
		this.outfile.write("START SUITE: " + name + " '" + attrs.get("doc")
				+ "' " + metastr + "\n");

	}

	private String getMetaString(Map metadata) {
		String metastr = "[";
		for (Object obj : metadata.entrySet()) {
			Map.Entry entry = (Map.Entry) obj;
			metastr += entry.getKey() + ": " + entry.getValue();
		}
		metastr += "]";
		return metastr;
	}

	public void startTest(String name, Map attrs) throws IOException {

		/*String[] tests = name.toString().split(":");		
		System.out.println("Length : " + tests.length);
		System.out.println(tests[0]);*/

		System.out.println("Executing the Test : " + name);
		if(name.split("\\.").length >0)

			hRobotTestDetails.put("currentTestName",name.split("\\.")[0]);
		/*String sp = ReadJsonTestData.getTestData("sponsorName").get("sponsorName").toString();
		System.out.println("sp : ================== "+sp);
		hRobotTestDetails.put("sponsorName",sp.trim());	*/	
		String sp = ReadJsonTestData.getTestData("resource").get("resource").toString();
		System.out.println("sp : ================== "+sp);
		hRobotTestDetails.put("resourceName",sp.trim());

		this.outfile.write("START TEST: " + name + " '" + attrs.get("doc")
				+ "' [");
		List tags = (List) attrs.get("tags");
		for (int i = 0; i < tags.size(); i++) {
			this.outfile.write(tags.get(i).toString());
		}
		this.outfile.write("]\n");
	}

	public void startKeyword(String name, Map attrs) throws IOException {
		System.out.println("Executing the Keyword : " + name);
		this.outfile.write("START KW: " + name + " [");
		List args = (List) attrs.get("args");
		for (int i = 0; i < args.size(); i++) {
			this.outfile.write(args.get(i).toString());
		}
		this.outfile.write("]\n");
	}

	public void endKeyword(String name, Map attrs) throws IOException {
		try {	
			String status = attrs.get("status").toString();
			if (status.equals("PASS")) {
				outfile.write("PASS\n");				
			} else {
				outfile.write("FAIL: " + attrs.get("message") + "\n");	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void logMessage(Map message) throws IOException {
		String msg = (String) message.get("message");
		String level = (String) message.get("level");
		String html = (String) message.get("html");
		message.get("timestamp");
		if (!html.equals("yes") && !html.equals("no")) {
			this.outfile.write("Log message has invalid `html` attribute "
					+ html);
		}
		if (!level.equals("TRACE") && msg.indexOf("Traceback") < 0) {
			this.outfile.write("LOG MESSAGE: [" + level + "] " + msg + "\n");
		}
	}

	public void message(Map message) throws IOException {
		String msg = (String) message.get("message");
		String level = (String) message.get("level");
		String html = (String) message.get("html");
		message.get("timestamp");
		if (!html.equals("yes") && !html.equals("no")) {
			this.outfile.write("Log message has invalid `html` attribute "
					+ html);
		}
		if (msg.indexOf("Settings") >= 0) {
			this.outfile.write("Got settings on level: " + level + "\n");
		}
	}

	public void endTest(String name, Map attrs) throws IOException {
		String status = attrs.get("status").toString();
		if (status.equals("PASS")) {
			this.outfile.write("END TEST: " + status + "\n");
		} else {
			this.outfile.write("END TEST: " + status + ": "
					+ attrs.get("message") + "\n");
		}		
	}

	public void endSuite(String name, Map attrs) throws IOException {
		this.outfile.write("END SUITE: " + attrs.get("status") + ": "
				+ attrs.get("statistics") + "\n");
	}

	public void outputFile(String path) throws IOException {
		this.writeOutputFile("Output", path);
	}

	/*public void reportFile(String path) throws IOException {
		this.writeOutputFile("Report", path);
	}*/

	private Properties readActualValues() {

		Properties props = new Properties();
		try {

			String outpath = System.getProperty("user.dir");

			if (outpath.contains("TeamCity")) {
				outpath = System.getProperty("user.dir") + System.getProperty("file.separator") + "src" + System.getProperty("file.separator") + "test" + System.getProperty("file.separator") + "resources" + System.getProperty("file.separator") + "robotframework";
			}
			StringBuffer buff = new StringBuffer(outpath);
			String outpath1 = buff.toString();
			String concate = outpath1+System.getProperty("file.separator")+"eEnrollment1.xml";
			File file = new File(concate);
			FileInputStream configFile = new FileInputStream(file);
			props.loadFromXML(configFile);
		} catch (Exception e1) {
			e1.printStackTrace();
		}	

		return props;
	}

	private static ZipOutputStream addFolder(ZipOutputStream zos,
			String folderName, String baseFolderName) throws Exception {
		File f = new File(folderName);
		if (f.exists()) {

			if (f.isDirectory()) {
				// Thank to peter
				// For pointing out missing entry for empty folder
				if (!folderName.equalsIgnoreCase(baseFolderName)) {
					String entryName = folderName.substring(
							baseFolderName.length() + 1, folderName.length())
							+ File.separatorChar;
					ZipEntry ze = new ZipEntry(entryName);
					zos.putNextEntry(ze);
				}
				File f2[] = f.listFiles();
				for (int i = 0; i < f2.length; i++) {
					addFolder(zos, f2[i].getAbsolutePath(), baseFolderName);
				}
			} else {
				// add file
				// extract the relative name for entry purpose
				String entryName = folderName.substring(
						baseFolderName.length() + 1, folderName.length());
				ZipEntry ze = new ZipEntry(entryName);
				zos.putNextEntry(ze);
				FileInputStream in = new FileInputStream(folderName);
				int len;
				byte buffer[] = new byte[1024];
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}
				in.close();
				zos.closeEntry();
			}
		} else {
			System.out.println("File or directory not found " + folderName);
		}
		return zos;
	}

	@SuppressWarnings("unused")
	private void generateArchive(String outputFileName) throws IOException {

		File file = new File(outputFileName);
		Path filePath = file.toPath();

		BasicFileAttributes attributes = null;
		try {
			attributes = Files.readAttributes(filePath,
					BasicFileAttributes.class);
		} catch (IOException exception) {
			System.out.println("Exception handled when trying to get file "
					+ "attributes: " + exception.getMessage());
		}
		long milliseconds = attributes.creationTime().to(TimeUnit.MILLISECONDS);
		String simpleDateFormat = null;
		if ((milliseconds > Long.MIN_VALUE) && (milliseconds < Long.MAX_VALUE)) {
			Date lastModifiedTime = new Date(attributes.lastModifiedTime().to(
					TimeUnit.MILLISECONDS));

			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH_mm")
			.format(lastModifiedTime.getTime());

		}

		File sourceFile = new File(outputFileName);

		Properties properties = readActualValues();

		String sharedPath = properties.getProperty("sharedPath");

		if(sharedPath!=null&&!(sharedPath.equals(""))){
			File destinationFile = new File(
					sharedPath + System.getProperty("file.separator")+simpleDateFormat);

			destinationFile.mkdir();

			FileUtils.copyDirectoryToDirectory(sourceFile, destinationFile);
		}else if(sharedPath.equals("")){
			StringBuffer buff = new StringBuffer(relPath);
			String destinationDir = buff.substring(0, buff.lastIndexOf("/"));

			File destinationFile = new File(
					destinationDir + System.getProperty("file.separator")+simpleDateFormat);

			destinationFile.mkdir();

			FileUtils.copyDirectoryToDirectory(sourceFile, destinationFile);

		}
	}

	private void mailWithAttachment(String toAddress, String fromAddress,
			String reportPath, String reportDirectory) throws Exception {

		String outpath = System.getProperty("user.dir");

		String outputFileName = outpath+System.getProperty("file.separator")+"EEAutomationReport.zip";

		Properties properties1 = readActualValues();
		String htmlMessage = properties1.getProperty("message");
		String ccAddress = properties1.getProperty("ccAddress");
		String bccAddress = properties1.getProperty("bccAddress");

		// Assuming you are sending email from localhost
		String host = "rdumail.prod1.benefitfocus.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.port", "25");
		properties.setProperty("mail.transport.protocol", "smtp");

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try {

			Transport transport = session.getTransport();
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(fromAddress));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					toAddress));

			message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(ccAddress));
			message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(bccAddress));

			// Set Subject: header field
			message.setSubject("eEnrollment Automation Report");

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			BodyPart messageBodyPart1 = new MimeBodyPart();

			// Fill the message
			//messageBodyPart.setText("Please find the Attachment with this mail");

			messageBodyPart.setContent(htmlMessage, "text/html; charset=utf-8");

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();

			Path file = Paths.get(reportDirectory);
			BasicFileAttributes attr = Files.readAttributes(file,
					BasicFileAttributes.class);
			System.out.println(attr.creationTime().toString());

			DataSource source = new FileDataSource(new File(reportPath));
			DataSource source1 = new FileDataSource(new File(outputFileName));
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart1.setDataHandler(new DataHandler(source1));
			messageBodyPart.setFileName(attr.creationTime() + "_"
					+ "report.html");
			messageBodyPart1.setFileName(attr.creationTime() + "_"
					+ "EEAutomationReport.zip");
			multipart.addBodyPart(messageBodyPart);

			multipart.addBodyPart(messageBodyPart1);

			// Send the complete message parts
			message.setContent(multipart);

			transport.connect();

			// Send message
			Transport.send(message,	message.getAllRecipients());
			System.out.println("Sent message successfully....");
			transport.close();
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

	public void reportFile(String path) throws Exception {



		this.writeOutputFile("Report", path);

		Properties properties1 = readActualValues();

		StringBuffer buff = new StringBuffer(path);


		String baseDirectory = buff.substring(0, buff.lastIndexOf(System.getProperty("file.separator")));

		String outpath = System.getProperty("user.dir");

		String outputFileName = outpath+System.getProperty("file.separator")+"EEAutomationReport.zip";

		FileOutputStream fos = new FileOutputStream(outputFileName);
		ZipOutputStream zos = new ZipOutputStream(fos);
		// level - the compression level (0-9)
		zos.setLevel(9);

		zos = addFolder(zos, baseDirectory, baseDirectory);

		zos.close();
		String archiveEnableFlag = properties1.getProperty("archiveReport");
		if(archiveEnableFlag!=null && archiveEnableFlag.equals("true")){

			generateArchive(baseDirectory);

		}

		String fromAddress = properties1.getProperty("fromAddress");

		String toAddress = properties1.getProperty("toAddress");

		String mailEnableFlag = properties1.getProperty("sendMailReport");

		if(mailEnableFlag!=null && mailEnableFlag.equals("true")){

			mailWithAttachment(toAddress, fromAddress, path, baseDirectory);
		}

	}

	public void logFile(String path) throws IOException {
		this.writeOutputFile("Log", path);
	}

	public void debugFile(String path) throws IOException {
		this.writeOutputFile("Debug", path);
	}

	public void close() throws IOException {
		this.outfile.write("The End\n");
		this.outfile.close();
	}

	private void writeOutputFile(String name, String path) throws IOException {
		File f = new File(path);
		this.outfile.write(name + " (java): " + f.getName() + "\n");
	}

}
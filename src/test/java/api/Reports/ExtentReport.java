package api.Reports;

	import java.text.SimpleDateFormat;
	import java.util.Date;

	import org.testng.ITestContext;
	import org.testng.ITestListener;
	import org.testng.ITestResult;

	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.Status;
	import com.aventstack.extentreports.reporter.ExtentSparkReporter;
	import com.aventstack.extentreports.reporter.configuration.Theme;

	public class ExtentReport implements ITestListener{
		public ExtentSparkReporter sparkReporter;
		public ExtentReports extent;
		public ExtentTest test;
		public void onTestSuccess(ITestResult result) {
			
			test = extent.createTest(result.getMethod().getMethodName());
			test.log(Status.PASS, "Test Passed");
		}

		public void onTestFailure(ITestResult result) {
			
			test = extent.createTest(result.getMethod().getMethodName());
			test.log(Status.FAIL, "Test Failed");
			test.log(Status.FAIL, result.getThrowable().getMessage());
			
		}

		public void onTestSkipped(ITestResult result) {
			
			test = extent.createTest(result.getMethod().getMethodName());
			test.log(Status.SKIP, "Test Skipped");
			test.log(Status.SKIP, result.getThrowable().getMessage());
		}

		public void onStart(ITestContext context) {
			String timeStemp = new SimpleDateFormat("yyyy.MM.dd.HH.mm").format(new Date());
			String repName = "Test-Report-" + timeStemp + ".html";

			sparkReporter = new ExtentSparkReporter(".\\Reports\\" + repName);

			sparkReporter.config().setDocumentTitle("API Testing Using Rest Assured");
			sparkReporter.config().setReportName("User Local Host API");
			sparkReporter.config().setTheme(Theme.DARK);

			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);

			extent.setSystemInfo("Application", "Local Host 3000");
			extent.setSystemInfo("Tester", "S.Manoj");
			extent.setSystemInfo("Operating System", System.getProperty("os.name"));
			extent.setSystemInfo("User Name", System.getProperty("user.name"));
		}

		public void onFinish(ITestContext context) {
			extent.flush();
		}

		public void onTestStart(ITestResult result) {
			// TODO Auto-generated method stub
			
		}

		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			// TODO Auto-generated method stub
			
		}
	}
	

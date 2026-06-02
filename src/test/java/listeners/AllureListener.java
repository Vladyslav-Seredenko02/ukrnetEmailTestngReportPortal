package listeners;

import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.service.ReportPortal;

import java.io.File;
import java.nio.file.Files;
import java.util.Date;

import drivers.DriverFactory;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.ByteArrayInputStream;

public class AllureListener implements ITestListener {
    private static final Logger log = LogManager.getLogger(AllureListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Test failed: {}", result.getName());

        byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
                .getScreenshotAs(OutputType.BYTES);

        Allure.addAttachment("Screenshot", "image/png", new ByteArrayInputStream(screenshot), ".png");
        log.info("Screenshot attached to Allure report");

        try {
            File screenshotFile = File.createTempFile("screenshot_" + result.getName(), ".png");
            Files.write(screenshotFile.toPath(), screenshot);

            ReportPortal.emitLog(
                    "Screenshot on failure: " + result.getName(),
                    LogLevel.ERROR.name(),
                    new Date(),
                    screenshotFile
            );

            log.info("Screenshot attached to ReportPortal");
        } catch (Exception e) {
            log.error("Failed to attach screenshot to ReportPortal", e);
        }
    }
}
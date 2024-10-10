package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Base {

	public WebDriver driver;
	public Properties prop;
	
	public WebDriver initializeDriver() throws IOException {
		
		prop = new Properties();
		String propPath = System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties";
		FileInputStream fl = new FileInputStream(propPath);
		prop.load(fl);
		
		String bName = prop.getProperty("browser");
		
		if(bName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}else if(bName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}else if(bName.equalsIgnoreCase("IE")) {
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();
		return driver;
		
	}
	
	public String takeScreenshot(String testname, WebDriver driver) throws IOException {
		File SourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationFilePath = System.getProperty("user.dir")+"\\screenshots\\"+testname+".png";
		FileUtils.copyFile(SourceFile,new File(destinationFilePath));
		
		return destinationFilePath;
	}
	
}

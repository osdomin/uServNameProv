package com.serenity.alm.nameprovider;
/*
 * 
 * (C) Copyright 2015 
 * mailTo: TBD
 * 
 * Licensed under the Apache License, Version 2.0 - the "License";
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     artudf
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;


import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class NameProviderTest {
	private WebDriver driver;
	private String baseUrl;

	private StringBuffer verificationErrors = new StringBuffer();
	protected static String seleniumGridURL = "http://srnalmjpre201.eng.gsnetcloud.corp:4444/wd/hub";
	protected static String seleniumBaseURL = "";

	@Before
	public void setUp() throws Exception {
		if (System.getProperty("seleniumGridURL") != null && !System.getProperty("seleniumGridURL").isEmpty())
			seleniumGridURL = System.getProperty("seleniumGridURL");
		if (System.getProperty("OSE3_END_POINT_URL") != null && !System.getProperty("OSE3_END_POINT_URL").isEmpty())
			seleniumBaseURL = System.getProperty("OSE3_END_POINT_URL");
		
		try {
			DesiredCapabilities capability = DesiredCapabilities.firefox();
			driver = new RemoteWebDriver(new URL(seleniumGridURL), capability);
			baseUrl = seleniumBaseURL;
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			baseUrl="";
		}
	}

	@Test
	public void nameProvider_testcase_id_24() throws Exception {
		System.out.println("baseUrl:" + baseUrl);
		org.junit.Assume.assumeTrue(!baseUrl.isEmpty());
		if (!baseUrl.isEmpty()) {
			driver.get(baseUrl + "/name");
			try {
                		//checks that application return Serenity ALM test
                		assertTrue(driver.getPageSource().contains("Serenity ALM"));
			} catch (Error e) {
				verificationErrors.append(e.toString());
			}
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("target/surefire-reports/screenshot-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + ".png"));
		}
	}

	@After
	public void tearDown() throws Exception {
		if(driver != null)
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}

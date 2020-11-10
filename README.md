# java_selenium_cucumber_automationdemo
1. Automation framework is developed using Cucumber, Selenium and Java
2. Cucumber Feature file is present in src/test/resources/features folder
3. Chrome Driver binary is present in src/test/resources/drivers
4. Configuration file is present in /src/test/java
5. Pages, Stepdefinitions, testrunner and utilities are present in corresponding folders in /src/test/java
6. Included few sample keywords for validating the pages corresponging to search results to verify fake or genuine site
7. Cucumber reports ->HTML, JSON are included
8. Steps are as follows:
	1. Open given URL 
	2. Pick any news from the site
	3. Search the news in google.com
	4. Collect all the URLs retured as search results
        5. Open each URL and verify if the news is legitimate or fake based on few keywords
        
NOTE: This framework can be improved further by including scenarios, if any API service is provided to verify the genuintiy of the news.


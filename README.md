# currency_rate
Microservice that shows gif depending on currency rates.
<br>If the rate against the dollar is higher, a gif is displayed from https://giphy.com/search/rich. 
<br>Else: https://giphy.com/search/broke

<H2>Instructions for starting the project.</H2>

<H3>For Docker:</H3>
docker pull dwarfsobaka/currency-rate-v2:latest
<H3>Running jar file:</H3>
<ol><li>Download this project from github.</li>
<li>On windows: 
  <br>in the command line write:  java -jar <Your_directory_Name>\currency-rate2\build\libs\currency-rate-0.0.1-SNAPSHOT.jar</li> 
<li>Go to http://localhost:8080/rate?currency=RUB</li>
<li>If you want to know the other rate of currency, you should change it in application.properties.</li>
  </ol>

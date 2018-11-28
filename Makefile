.PHONY: all
all: package
	
.PHONY: package
package:
	mvn package
	
.PHONY: test
test:
	mvn test
	
.PHONY: coverage
coverage:
	mvn clean \
	 org.jacoco:jacoco-maven-plugin:prepare-agent \
	 package \
	 org.jacoco:jacoco-maven-plugin:report
	mvn com.gavinmogan:codacy-maven-plugin:coverage \
	 -DcoverageReportFile=target/site/jacoco/jacoco.xml \
	 -DprojectToken=$(PROJECT_TOKEN) \
	 -DapiToken=$(API_TOKEN)
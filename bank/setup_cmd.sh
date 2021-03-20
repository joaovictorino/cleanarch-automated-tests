# jacoco
mvn jacoco:prepare-agent install jacoco:report

# pit mutation tests
mvn test org.pitest:pitest-maven:mutationCoverage
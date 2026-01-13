$env:JAVA_HOME='C:\Program Files\Java\jdk17.0.17_10'
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
Set-Location -Path 'C:\work\order\backend'
Write-Host "Using Java:"; java -version
Write-Host "Starting backend with Maven..."
& '.\.mvn\apache-maven\apache-maven-3.8.8\bin\mvn.cmd' -DskipTests spring-boot:run
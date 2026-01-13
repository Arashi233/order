# PowerShell helper to download the Maven Wrapper jar
# Run this once in the backend folder to populate .mvn/wrapper/maven-wrapper.jar

$uri = "https://repo1.maven.org/maven2/io/takari/maven-wrapper/0.5.6/maven-wrapper-0.5.6.jar"
$targetDir = Join-Path -Path $PSScriptRoot -ChildPath ".mvn\wrapper"
$targetFile = Join-Path -Path $targetDir -ChildPath "maven-wrapper.jar"

if (-not (Test-Path $targetDir)) {
    New-Item -ItemType Directory -Path $targetDir -Force | Out-Null
}

Write-Host "Downloading maven-wrapper.jar to $targetFile ..."
Invoke-WebRequest -Uri $uri -OutFile $targetFile -UseBasicParsing
Write-Host "Done. You can now run .\mvnw.cmd or ./mvnw"

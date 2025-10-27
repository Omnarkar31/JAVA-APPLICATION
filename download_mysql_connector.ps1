# Download MySQL Connector/J
Write-Host "Downloading MySQL Connector/J..." -ForegroundColor Green

$url = "https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.0.33/mysql-connector-j-8.0.33.jar"
$output = "lib\mysql-connector-j-8.0.33.jar"

try {
    Invoke-WebRequest -Uri $url -OutFile $output
    Write-Host "Download completed successfully!" -ForegroundColor Green
    Write-Host "MySQL Connector saved to: $output" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "You can now compile and run your application with:" -ForegroundColor Yellow
    Write-Host "javac -cp `".;lib\mysql-connector-j-8.0.33.jar`" -d . *.java" -ForegroundColor White
    Write-Host "java -cp `".;lib\mysql-connector-j-8.0.33.jar`" tapNpay.TapNpay" -ForegroundColor White
} catch {
    Write-Host "Error downloading file: $_" -ForegroundColor Red
    Write-Host ""
    Write-Host "Please download manually from:" -ForegroundColor Yellow
    Write-Host "https://dev.mysql.com/downloads/connector/j/" -ForegroundColor Cyan
}


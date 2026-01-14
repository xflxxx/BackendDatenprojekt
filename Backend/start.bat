@echo off
start /min cmd /c mvnw.cmd spring-boot:run
echo ==================
echo starte Backend...
echo ==================
timeout /t 10 /nobreak >nul
start http://localhost:8080

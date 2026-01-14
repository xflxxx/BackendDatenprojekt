@echo off
if not "%1"=="hidden" (
    echo CreateObject("Wscript.Shell").Run "cmd /c ""%~f0"" hidden", 0, False > "%temp%\start_hidden.vbs"
    wscript "%temp%\start_hidden.vbs"
    del "%temp%\start_hidden.vbs"
    exit
)

start /B mvnw.cmd spring-boot:run
start http://localhost:8080

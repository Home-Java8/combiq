@echo off
java -Denv=%1 -cp "data-1.0-SNAPSHOT.jar;dependency/*" org.springframework.shell.Bootstrap --disableInternalCommands
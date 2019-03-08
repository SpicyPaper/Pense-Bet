#!/bin/bash

Xvfb :99 &
export local_addr="127.0.0.1:8080"


/Katalon_Studio_Windows_64/katalon -noSplash  -runMode=console -projectPath="$(pwd)/Katalon/Pense-Bet/Pense-Bet.prj" -retry=1 -testSuitePath="Test Suites/Pense-Bet" -executionProfile="default" -browserType="Chrome (headless)" -Djava.awt.headless
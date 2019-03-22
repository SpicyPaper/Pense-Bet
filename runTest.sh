#!/bin/bash

Xvfb :99 &
export local_addr="127.0.0.1:8080"


/Katalon_Studio_Linux_64-5.7.1/katalon -noSplash  -runMode=console -consoleLog -projectPath="$(pwd)/Katalon/Pense-Bet/Pense-Bet.prj" -retry=0 -testSuitePath="Test Suites/Pense-Bet" -executionProfile="default" -browserType="Chrome (headless)" -Djava.awt.headless

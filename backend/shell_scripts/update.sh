#!/bin/sh

echo **********************************************************************************
echo ==================================================================================
echo ---------------------------starting script by M@X---------------------------------
echo ==================================================================================
echo **********************************************************************************
echo
#access server
ssh -p 10922 user@gfapp.eu '
cd Foxbook;
git pull;
cd frontend;
rm -rf ../backend/src/main/resources/static;
ng build;
cd ../backend;
sh gradlew;
sh gradlew build;
'
# systemctl restart Foxbook
# exit



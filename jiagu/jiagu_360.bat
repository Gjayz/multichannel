::@echo off

echo ----------- jiagu bat start ----------

set APP_NAME=%1
set PACKAGE_NAME=%2
set VERSION_NAME=%3
set SDK_PATH=%4

set KEY_PATH=%5
set KEY_PASSWORD=%6
set ALIAS=%7
set ALIAS_PASSWORD=%8

set BASE=../jiagu/jiagu.jar
set NAME=
set PASSWORD=

set CHANNEL=.\channel.txt
set APK=.\build\outputs\apk\release\%APP_NAME%-release.apk
set DEST=.\apkJiaguOutput\%VERSION_NAME%\

set WALLE_PATH=..\jiagu\walle
set ZIP_PATH=%DEST%%PACKAGE_NAME%_v%VERSION_NAME%
set JIAGU_PATH=.\apkJiaguOutput\%VERSION_NAME%\
set JIAGU_APK_PATH=

md %DEST%
md %JIAGU_PATH%

echo "------ jiagu start!------"

java -jar %BASE% -version
java -jar %BASE% -login %NAME% %PASSWORD%
::@#java -jar ${BASE} -importsign ${KEY_PATH} ${KEY_PASSWORD} ${ALIAS} ${ALIAS_PASSWORD}
::@#java -jar ${BASE} -showsign
::@#java -jar ${BASE} -importmulpkg ${CHANNEL} #根据自身情况使用
::@#java -jar ${BASE} -showmulpkg
::@#java -jar ${BASE} -showconfig
java -jar %BASE% -jiagu %APK% %DEST% #-autosign

echo "------ jiagu finished!------"

echo "------ jiagu find jiagu apk path start ------"
setlocal ENABLEDELAYEDEXPANSION
set houzui=jiagu.apk
for /R "%JIAGU_PATH%" %%i in (*) do (
set filename=%%~nxi
call :checkFileName %%~xi
)
pause

:checkFileName
for %%i in (%houzui%) do (
    if "%1"=="%%i" echo !filename!
    set JIAGU_APK_PATH=%JIAGU_PATH%%filename%
    call :channelApk
)
pause

:channelApk
echo "------ jiagu find jiagu apk path finish ------"

echo "------ zipalign running! ------"
%SDK_PATH%\zipalign -v 4 %JIAGU_APK_PATH% %ZIP_PATH%.apk
echo "------ zipalign finished! ------"

echo "------ signer running! ------"
java -jar %SDK_PATH%\lib\apksigner.jar sign --ks %KEY_PATH% --ks-key-alias %ALIAS% --ks-pass pass:%KEY_PASSWORD% --key-pass pass:%ALIAS_PASSWORD% --out %ZIP_PATH%_sign.apk %ZIP_PATH%.apk
echo "------ signer finished! ------"

echo "------ check android v2 signature start ------"
java -jar %WALLE_PATH%/CheckAndroidV2Signature.jar %ZIP_PATH%_sign.apk %DEST%
echo "------ check android v2 signature finish ------"

echo "------ walle channel start ------"
java -jar %WALLE_PATH%/walle.jar batch -f %WALLE_PATH%/channel.txt %ZIP_PATH%_sign.apk %DEST%
echo "------ walle channel finished ------"

echo ----------- jiagu bat finish ----------
EXIT
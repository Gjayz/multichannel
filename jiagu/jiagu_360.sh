#!/bin/bash
APP_NAME=$1
PACKAGE_NAME=$2
VERSION_NAME=$3
SDK_PATH=$4

KEY_PATH=$5
KEY_PASSWORD=$6
ALIAS=$7
ALIAS_PASSWORD=$8

echo $APP_NAME
echo $PACKAGE_NAME
echo $VERSION_NAME
echo $SDK_PATH
echo $KEY_PATH
echo $KEY_PASSWORD
echo $ALIAS
echo $ALIAS_PASSWORD

BASE=../jiagu/jiagu.jar
NAME=
PASSWORD=

CHANNEL=./channel.txt
APK=./build/outputs/apk/${APP_NAME}-release.apk
DEST=./apkJiaguOutput/${VERSION_NAME}/

WALLE_PATH=../jiagu_mac/walle/
ZIP_PATH=${DEST}${PACKAGE_NAME}_v${VERSION_NAME}.apk
JIAGU_PATH=./apkJiaguOutput/${VERSION_NAME}/

mkdir -p ${DEST}
mkdir -p ${JIAGU_PATH}

echo "------ jiagu start!------"

echo "------ signer running! ------"
bat ${SDK_PATH}\apksigner.bat sign --ks ${KEY_PATH} ${APK} <<EOF
${KEY_PASSWORD}
EOF
echo "------ signer finished! ------"

java -jar ${BASE} -version
java -jar ${BASE} -login ${NAME} ${PASSWORD}
#java -jar ${BASE} -importsign ${KEY_PATH} ${KEY_PASSWORD} ${ALIAS} ${ALIAS_PASSWORD}
#java -jar ${BASE} -showsign
#java -jar ${BASE} -importmulpkg ${CHANNEL} #根据自身情况使用
#java -jar ${BASE} -showmulpkg
#java -jar ${BASE} -showconfig
echo $APK
java -jar ${BASE} -jiagu ${APK} ${DEST} #-autosign

echo "------ output file start------"
for file_a in ${DEST}/*; do
    temp_file=`basename $file_a`
    if [[ $temp_file == *"jiagu"*.apk ]];then
        JIAGU_APK_PATH=${DEST}$temp_file
        echo "find it "${JIAGU_APK_PATH}
        echo $temp_file
        break
    fi
    echo $temp_file
done
echo "------ output file finish ------"

echo "------ zipalign running! ------"
${SDK_PATH}/zipalign -v 4 ${JIAGU_APK_PATH} ${ZIP_PATH}
echo "------ zipalign finished! ------"

echo "------ signer running! ------"
${SDK_PATH}/apksigner sign --ks ${KEY_PATH} ${ZIP_PATH} <<EOF
${KEY_PASSWORD}
EOF
echo "------ signer finished! ------"

echo "------ check android v2 signature start ------"
java -jar ${WALLE_PATH}/CheckAndroidV2Signature.jar ${ZIP_PATH}
echo "------ check android v2 signature finish ------"

echo "------ walle channel start ------"
java -jar ${WALLE_PATH}/walle.jar batch -f ${WALLE_PATH}/channel.txt ${ZIP_PATH} ${DEST}
echo "------ walle channel finished ------"

#!/usr/bin/env bash

VERSION_FILE_PATH=../version.properties
if [[ -z "$LIB_VERSION" ]]; then

    export LIB_VERSION=`cat ${VERSION_FILE_PATH}  | grep 'version=' | cut -d '=' -f 2`

    if [[ -z "$VERSION_FILE_PATH" ]]; then
        echo "Error: Unable to determine BazarBooks Version Using: ${VERSION_FILE_PATH}"
        exit 1
    fi

fi

echo "Building with Version: ${LIB_VERSION}..."
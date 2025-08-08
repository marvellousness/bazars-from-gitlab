#!/usr/bin/env bash

set +x

COMMON_CWD=`pwd`

COMMON_SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

set -x

cd "${COMMON_SCRIPT_DIR}"

set +x

VERSION_FILE_PATH=../version.properties
if [[ -z "$LIB_VERSION" ]]; then

    export LIB_VERSION=`cat ${VERSION_FILE_PATH}  | grep 'version=' | cut -d '=' -f 2`

    if [[ -z "$VERSION_FILE_PATH" ]]; then
        echo "Error: Unable to determine BazarBooks Version Using: ${VERSION_FILE_PATH}"
        exit 1
    fi

fi

echo "Building with Version: ${LIB_VERSION}..."
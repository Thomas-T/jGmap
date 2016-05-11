#!/usr/bin/env bash
if [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then
    gpg --import cd/sign-key.gpg
    mvn deploy -P sign,build-extras -DskipTests=true --settings cd/mvnsettings.xml
fi

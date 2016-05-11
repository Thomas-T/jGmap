#!/usr/bin/env bash
#mvn -DcoberturaFormat=xml cobertura:cobertura coveralls:report
if [ "$TRAVIS_PULL_REQUEST" == 'false' ] && ([ "$TRAVIS_BRANCH" == 'master' ] || [ "$TRAVIS_BRANCH" = 'develop' ]); then
    gpg --import cd/sign-key.gpg
    mvn deploy -P sign,build-extras -DskipTests=true --settings cd/mvnsettings.xml
fi

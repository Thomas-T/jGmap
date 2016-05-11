#!/usr/bin/env bash
if [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then
    mvn -X deploy -P sign --settings cd/mvnsettings.xml
fi

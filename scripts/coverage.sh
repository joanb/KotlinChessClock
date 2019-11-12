#!/usr/bin/env bash

set -e

./gradlew clean jacocoTestReportDebug jacocoTestReport &&
 ./gradlew mergeJacocoReports jacocoTestReportMerged &&
  open build/reports/jacoco/index.html

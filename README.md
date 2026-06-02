## What Was Implemented

1. ReportPortal integration - DONE.
   COMMENT: ReportPortal was integrated into the project using the ReportPortal TestNG listener.

2. Separate TestNG suite for ReportPortal execution - DONE.
   COMMENT: A separate suite was created specifically for this task.

3. Screenshot attachments in ReportPortal - DONE.
   COMMENT: Screenshot attachment was added to the existing test failure listener.
   When a test fails, a screenshot is attached to the failed test logs in ReportPortal.

4. ReportPortal execution results - DONE.
   COMMENT: Screenshots that confirm successful task execution and ReportPortal
   results are stored in the following folder: `rp-results/`.

## How to run

Default run:
`mvn clean test`

With parameters:
`mvn clean test -Dsuite=reportPortal -Denv=qa2`

Available parameters:

| Parameter |                    Values                    |   Default    |
|:---------:|:--------------------------------------------:|:------------:|
|   suite   | smoke, regression, crossBrowser,reportPortal | reportPortal |
|  browser  |            chrome, firefox, edge             |    chrome    |
|    env    |                   qa1, qa2                   |     qa1      |

Allure Report:
`mvn allure:serve`
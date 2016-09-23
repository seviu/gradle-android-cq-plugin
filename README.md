gradle-android-cq-plugin
===============================

Gradle plugin for running tasks PMD, CPD, Findbugs, Checkstyle with android project

It uses the latest version of checkstyle with the latest rules. It also has the latest version of checkstyle from google and square.

The motivation of this fork is to have this plugin working with the latest version of Android.

`findbugs,
pmd,
cpd,
checkstyle
checkstyle_google
checkstyle_square`


Usage:
------

Add the plugin to your `buildscript`'s `dependencies` section:
```groovy
classpath 'com.github.seviu:gradle-android-cq-plugin:0.1.28'
```

Apply the `android-cq` plugin:
```groovy
apply plugin: 'android-cq'
```

Run:
`./gradlew clean findbugs pmd cpd checkstyle checkstyle_google checkstyle_square`

results will be placed in `build/reports dir`

Config:
-------
For configs tasks use folder `cq-config` which will be created in project root dir after first tasks runing.
In `cq-config` will be placed main configs(like ruleset, exclude etc.) and xsl files.
For customizing tasks just replace appropriate config in task's folder.

package com.github.nrudenko.gradle.cq

import org.gradle.api.Plugin
import org.gradle.api.Project

public class AndroidCQPlugin implements Plugin<Project> {


    @Override
    void apply(Project project) {
        project.repositories.add(project.repositories.mavenCentral())

        project.configurations.create('findbugs')
        project.configurations.create('findbugsPlugins')
        project.configurations.create('codequality')

        project.dependencies.add('compile', 'com.google.code.findbugs:annotations:3.0.0')
        project.dependencies.add('findbugs', 'com.google.code.findbugs:findbugs-ant:3.0.0')
        project.dependencies.add('codequality', 'com.puppycrawl.tools:checkstyle:7.1.1')
        project.dependencies.add('codequality', 'pmd:pmd:4.3')

        project.task('findbugs', type: AndroidFindBugsTask)
        project.task('checkstyle', type: AndroidCheckstyleDefaultTask)
        project.task('google_checkstyle', type: AndroidCheckstyleGoogleTask)
        project.task('square_checkstyle', type: AndroidCheckstyleSquareTask)
        project.task('pmd', type: AndroidPmdTask)
        project.task('cpd', type: AndroidCpdTask)
    }

}

package com.github.nrudenko.gradle.cq

import org.gradle.api.file.FileCollection
import org.gradle.api.internal.project.IsolatedAntBuilder
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
/**
 * See parameters at http://checkstyle.sourceforge.net/anttask.html
 */
abstract class AndroidCheckstyleTask extends BaseStatisticTask {
    public static final String checkstyleClassname = 'com.puppycrawl.tools.checkstyle.ant.CheckstyleAntTask'

    @InputFile
    @Optional
    File configFile

    FileCollection checkstyleClasspath = project.configurations.codequality
    Boolean ignoreFailures = true
    Boolean showViolations = false

    @Override
    String getXslFilePath() {
        return "checkstyle/checkstyle-html.xsl"
    }

    abstract String getConfigPath();

    def AndroidCheckstyleTask() {
        description = 'Runs checkstyle against Android sourcesets.'
        group = 'Code Quality'
    }

    @TaskAction
    def runCheckstyle() {
        prepareTaskFiles()
        configFile = getFileFromConfigCache(getConfigPath())

        def antBuilder = services.get(IsolatedAntBuilder)
        antBuilder.withClasspath(checkstyleClasspath).execute {
            ant.taskdef(name: 'checkstyle', classname: checkstyleClassname)
            // see also, maxWarnings and failureProperty arguments
            ant.checkstyle(config: configFile, failOnViolation: !ignoreFailures) {
                fileset(dir: gradleProject.projectDir.getPath()) {
                    applyToFileSet({file -> include(name: gradleProject.relativePath(file))})
                }
                if (showViolations) {
                    formatter(type: 'plain', useFile: false)
                }
                formatter(type: 'xml', toFile: outputFile)
            }

            makeHtml(ant)
        }
    }
}
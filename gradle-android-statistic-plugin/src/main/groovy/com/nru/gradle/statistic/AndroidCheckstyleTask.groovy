package com.nru.gradle.statistic

import org.gradle.api.file.FileCollection
import org.gradle.api.internal.project.IsolatedAntBuilder
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
/**
 * See parameters at http://checkstyle.sourceforge.net/anttask.html
 */
class AndroidCheckstyleTask extends BaseStatisticTask {
    public static
    final String checkstyleClassname = 'com.puppycrawl.tools.checkstyle.CheckStyleTask'
    public static final String configFilePath = 'checkstyle/checkstyle.xml'

    @InputFile
    @Optional
    File configFile

    FileCollection checkstyleClasspath = project.configurations.codequality
    Boolean ignoreFailures = true
    Boolean showViolations = false

    @Override
    String getXslFilePath() {
        return "checkstyle/checkstyle-noframes-sorted.xsl"
    }

    @Override
    String getOutputPath() {
        return "$project.buildDir/reports/checkstyle/checkstyle-${project.name}.xml"
    }

    def AndroidCheckstyleTask() {
        description = 'Runs checkstyle against Android sourcesets.'
        group = 'Code Quality'
    }

    @TaskAction
    def runCheckstyle() {
        project.dependencies.add('codequality', 'com.puppycrawl.tools:checkstyle:5.6')
        createOutputFileIfNeeded()
        getXlsFile();

        configFile = getDefaultFileIfNeeded(configFile, configFilePath)

        def antBuilder = services.get(IsolatedAntBuilder)
        antBuilder.withClasspath(checkstyleClasspath).execute {
            ant.taskdef(name: 'checkstyle', classname: checkstyleClassname)
            // see also, maxWarnings and failureProperty arguments
            ant.checkstyle(config: configFile, failOnViolation: !ignoreFailures) {
                fileset(dir: gradleProject.projectDir.getPath()) {
                    gradleProject.android.sourceSets.each { sourceSet ->
                        sourceSet.java.each { file ->
                            include(name: gradleProject.relativePath(file))
                        }
                    }
                }
                if (showViolations) {
                    formatter(type: 'plain', useFile: false)
                }
                formatter(type: 'xml', toFile: outputFile)
            }

            if (outputFile.exists() && xslFile != null && xslFile.exists()) {
                ant.xslt(in: outputFile,
                        style: xslFile,
                        out: outputFile.absolutePath.replaceFirst(~/\.[^\.]+$/, ".html")
                )
            }
        }
    }
}
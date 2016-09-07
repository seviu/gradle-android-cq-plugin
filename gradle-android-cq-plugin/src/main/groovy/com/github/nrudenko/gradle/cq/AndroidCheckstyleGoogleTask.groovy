package com.github.nrudenko.gradle.cq

import org.gradle.api.file.FileCollection
import org.gradle.api.internal.project.IsolatedAntBuilder
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
/**
 * See parameters at http://checkstyle.sourceforge.net/anttask.html
 */
class AndroidCheckstyleGoogleTask extends AndroidCheckstyleTask {
    @Override
    String getOutputPath() {
        return "$project.buildDir/reports/checkstyle/google_checkstyle-result.xml"
    }
    @Override
    String getConfigPath() {
        return "checkstyle/google_checkstyle.xml";
    }
    def AndroidCheckstyleGoogleTask() {
        description = 'Runs google checkstyle against Android sourcesets.'
        group = 'Code Quality'
    }
}
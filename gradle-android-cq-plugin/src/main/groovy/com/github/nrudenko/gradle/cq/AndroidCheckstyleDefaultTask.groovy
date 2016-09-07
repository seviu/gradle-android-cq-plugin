package com.github.nrudenko.gradle.cq
/**
 * See parameters at http://checkstyle.sourceforge.net/anttask.html
 */
class AndroidCheckstyleDefaultTask extends AndroidCheckstyleTask {
    @Override
    String getOutputPath() {
        return "$project.buildDir/reports/checkstyle/checkstyle-result.xml"
    }
    @Override
    String getConfigPath() {
        return "checkstyle/checkstyle.xml";
    }
    def AndroidCheckstyleDefaultTask() {
        description = 'Runs default checkstyle against Android sourcesets.'
        group = 'Code Quality'
    }
}
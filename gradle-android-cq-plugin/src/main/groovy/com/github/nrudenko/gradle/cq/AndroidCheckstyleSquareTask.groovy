package com.github.nrudenko.gradle.cq
/**
 * See parameters at http://checkstyle.sourceforge.net/anttask.html
 */
class AndroidCheckstyleSquareTask extends AndroidCheckstyleTask {
    @Override
    String getOutputPath() {
        return "$project.buildDir/reports/checkstyle/square_checkstyle-result.xml"
    }
    @Override
    String getConfigPath() {
        return "checkstyle/square_checkstyle.xml";
    }
    def AndroidCheckstyleSquareTask() {
        description = 'Runs square checkstyle against Android sourcesets.'
        group = 'Code Quality'
    }
}
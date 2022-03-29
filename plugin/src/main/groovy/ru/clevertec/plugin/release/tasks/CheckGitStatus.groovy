package ru.clevertec.plugin.release.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.GradleScriptException
import org.gradle.api.tasks.TaskAction
import ru.clevertec.plugin.release.GitUtils

class CheckGitStatus extends DefaultTask {


    @TaskAction
    def checkStatus() {
        def statusResult = GitUtils.gitStatusResult
        if(statusResult.isEmpty()){
            throw new GradleScriptException("Git is missing in the project", null)
        }
        if (statusResult.contains("new file") || statusResult.contains("modified")) {
            throw new GradleScriptException("Uncommitted changes was found", null)
        }
    }

}
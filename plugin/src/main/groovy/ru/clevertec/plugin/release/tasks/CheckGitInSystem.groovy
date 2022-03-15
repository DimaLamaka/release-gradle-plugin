package ru.clevertec.plugin.release.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.GradleScriptException
import org.gradle.api.tasks.TaskAction
import ru.clevertec.plugin.release.GitUtils

class CheckGitInSystem extends DefaultTask{

    @TaskAction
    def checkGitInSystem() {
        def systemResult = GitUtils.getGitInSystemResult()
        if (!systemResult.contains("git version")) {
            throw new GradleScriptException("Git is not installed on the system", null);
        }
    }
}

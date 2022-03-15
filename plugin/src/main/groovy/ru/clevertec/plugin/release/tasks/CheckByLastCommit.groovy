package ru.clevertec.plugin.release.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.GradleScriptException
import org.gradle.api.tasks.TaskAction
import ru.clevertec.plugin.release.GitUtils

class CheckByLastCommit extends DefaultTask{
    @TaskAction
    def checkByLastCommit() {
        def lastCommit = GitUtils.getLastCommit().split("\n")[0].split(" ")[1]
        def tags = GitUtils.getGitTagsResult.split("\n")

        def lastTag = tags[tags.size()-1]
        def showInfoByLastTag = ("git show ".concat(lastTag)).execute().text.split("\n")
        def commitByLastTag = showInfoByLastTag[6].split(" ")[1]
        if(commitByLastTag == lastCommit){
            throw new GradleScriptException("Tag was created in the last commit", null);
        }
    }
}

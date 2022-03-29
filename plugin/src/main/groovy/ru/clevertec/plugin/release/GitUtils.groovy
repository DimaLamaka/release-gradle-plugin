package ru.clevertec.plugin.release

import org.gradle.api.GradleScriptException

class GitUtils {

    static def getGitTagsResult() {
        ("git tag").execute().text
    }

    static def createTag(String version) {
        def result = ("git tag -a $version -m \"Created\"").execute().text

        if (!result.isEmpty()) {
            throw new GradleScriptException("Tag was not created", null)
        }
    }

    static def getGitStatusResult() {
        return ("git status").execute().text
    }

    static def getCurrentBranch() {
        return ("git branch --show-current").execute().text
    }

    static def getGitInSystemResult() {
        return ("git version").execute().text
    }

    static def getLastCommit() {
        return ("git log -1").execute().text
    }
}